package cz.muni.fi.legomanager.controllers;

import cz.fi.muni.legomanager.dto.*;
import cz.fi.muni.legomanager.facade.BrickFacade;
import cz.muni.fi.legomanager.exceptions.InvalidRequestException;
import cz.muni.fi.legomanager.exceptions.ResourceNotFoundException;
import cz.muni.fi.legomanager.hateoas.BrickResource;
import cz.muni.fi.legomanager.hateoas.BrickResourceAssembler;
import cz.muni.fi.legomanager.security.ApplyAuthorizeFilter;
import cz.muni.fi.legomanager.security.SecurityLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 *
 * @author Michal Peška
 */

@RestController
@ExposesResourceFor(BrickDTO.class)
@RequestMapping("/bricks")
public class BricksRestController {

    private final static Logger log = LoggerFactory.getLogger(BricksRestController.class);
    private BrickFacade facade;
    private BrickResourceAssembler resourceAssembler;
    private EntityLinks entityLinks;


    @Autowired
    public BricksRestController(
             BrickResourceAssembler resourceAssembler,
            @SuppressWarnings("SpringJavaAutowiringInspection")
            EntityLinks entityLinks,
            BrickFacade facade) {

        this.resourceAssembler = resourceAssembler;
        this.entityLinks = entityLinks;
        this.facade = facade;

    }


    /**
     * Produces list of all in JSON.
     *
     * @return list of bricks
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.EMPLOYEE)
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Resources<BrickResource>> bricks() {
        log.debug("rest sets()");

        Resources<BrickResource> resources = new Resources<>(
                resourceAssembler.toResources(facade.findAll()),
                linkTo(BricksRestController.class).withSelfRel(),
                linkTo(BricksRestController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    /**
     * Produces detail.
     *
     * @param id identifier
     * @return detail
     * @throws Exception if not found
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.EMPLOYEE)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<BrickResource> brick(@PathVariable("id") long id) throws Exception {
        log.debug("rest set({})", id);
        BrickDTO foundDTO = facade.findById(id);
        if (foundDTO == null) throw new ResourceNotFoundException("brick " + id + " not found");
        BrickResource resource = resourceAssembler.toResource(foundDTO);
        return new HttpEntity<>(resource);
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<BrickResource> createBrick(@RequestBody @Valid BrickCreateDTO paramDTOCreate, BindingResult bindingResult) throws Exception {
        log.debug("rest createBrick()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        Long id = facade.create(paramDTOCreate);
        BrickDTO foundDTO = facade.findById(id);

        BrickResource resource = resourceAssembler.toResource(foundDTO);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteBrick(@PathVariable("id") long id) throws Exception {
        log.debug("rest deleteSet({})", id);
        try {
            facade.delete(id);
        } catch (IllegalArgumentException ex) {
            log.error("set " + id + " not found");
            throw new ResourceNotFoundException("set " + id + " not found");
        } catch (Throwable ex) {
            log.error("cannot delete set " + id + " :" + ex.getMessage());
            throw new ResourceNotFoundException("Unable to delete non existing item");
        }
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    public final BrickDTO changeBrick(@PathVariable("id") long id, @RequestBody @Valid BrickDTO updatedDTO) throws Exception {
        log.debug("rest change Set({})", id);

        try {
            updatedDTO.setId(id);
            facade.update(updatedDTO);
            return facade.findById(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Unable to update set");
        }
    }


}





