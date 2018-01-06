package cz.muni.fi.legomanager.controllers;

import cz.fi.muni.legomanager.dto.*;
import cz.fi.muni.legomanager.facade.BrickFacade;
import cz.muni.fi.legomanager.ApiUris;
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
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @author Michal Peška
 */

@RestController
@ExposesResourceFor(BrickDTO.class)
@RequestMapping(ApiUris.ROOT_URI_BRICKS)
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
        log.debug("rest bricks()");

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
        log.debug("rest brick({})", id);
        BrickDTO foundDTO = facade.findById(id);
        if (foundDTO == null) throw new ResourceNotFoundException("brick " + id + " not found");
        BrickResource resource = resourceAssembler.toResource(foundDTO);
        return new HttpEntity<>(resource);
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<BrickResource> createBrick(@RequestBody @Valid BrickCreateDTO paramDTOCreate, BindingResult bindingResult) throws Exception {
        log.debug("rest createBrick()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException(bindingResult.toString());
        }

        Long id;
        try {
            id = facade.create(paramDTOCreate);
        } catch (JpaSystemException ex) {
            log.error("Cannot create such brick.");
            throw new InvalidRequestException("Cannot create such brick.");
        }

        BrickDTO foundDTO = facade.findById(id);
        BrickResource resource = resourceAssembler.toResource(foundDTO);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteBrick(@PathVariable("id") long id) throws Exception {
        log.debug("rest deleteBrick({})", id);
        try {
            facade.delete(id);
        } catch (IllegalArgumentException ex) {
            log.error("brick " + id + " not found");
            throw new ResourceNotFoundException("brick " + id + " not found");
        } catch (JpaSystemException ex) {
            log.error("cannot delete brick " + id + " :" + ex.getMessage());
            throw new ResourceNotFoundException("Brick is being used in some kit.");
        }
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final BrickDTO changeBrick(@PathVariable("id") long id, @RequestBody @Valid BrickDTO updatedDTO, BindingResult bindingResult) throws Exception {
        log.debug("rest change Brick({})", id);
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException(bindingResult.toString());
        }

        try {
            updatedDTO.setId(id);
            facade.update(updatedDTO);
        } catch (JpaSystemException ex) {
            log.error("Unable to update brick.");
            throw new InvalidRequestException("Unable to update brick.");
        }

        return facade.findById(id);
    }
}





