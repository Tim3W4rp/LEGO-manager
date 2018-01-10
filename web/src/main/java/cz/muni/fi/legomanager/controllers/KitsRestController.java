package cz.muni.fi.legomanager.controllers;

import cz.fi.muni.legomanager.dto.*;
import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.facade.BrickFacade;
import cz.fi.muni.legomanager.facade.CategoryFacade;
import cz.fi.muni.legomanager.facade.KitFacade;
import cz.muni.fi.legomanager.ApiUris;
import cz.muni.fi.legomanager.exceptions.FormException;
import cz.muni.fi.legomanager.exceptions.InvalidRequestException;
import cz.muni.fi.legomanager.exceptions.ResourceNotFoundException;
import cz.muni.fi.legomanager.hateoas.KitResource;
import cz.muni.fi.legomanager.hateoas.KitResourceAssembler;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @author Michal Peška
 */

@RestController
@ExposesResourceFor(KitDTO.class)
@Transactional
@RequestMapping(ApiUris.ROOT_URI_KITS)
public class KitsRestController {

    private final static Logger log = LoggerFactory.getLogger(KitsRestController.class);
    private KitFacade facade;
    private CategoryFacade categoryFacade;
    private BrickFacade brickFacade;
    private KitResourceAssembler resourceAssembler;
    private EntityLinks entityLinks;


    @Autowired
    public KitsRestController(
            KitResourceAssembler resourceAssembler,
            @SuppressWarnings("SpringJavaAutowiringInspection")
                    EntityLinks entityLinks,
            KitFacade facade,
            CategoryFacade categoryFacade,
            BrickFacade brickFacade) {

        this.resourceAssembler = resourceAssembler;
        this.entityLinks = entityLinks;
        this.facade = facade;
        this.categoryFacade = categoryFacade;
        this.brickFacade = brickFacade;
    }


    /**
     * Produces list of all in JSON.
     *
     * @return list of kits
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.EMPLOYEE)
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Resources<KitResource>> kits() {
        log.debug("rest kits()");

        Resources<KitResource> resources = new Resources<>(
                resourceAssembler.toResources(facade.findAllKits()),
                linkTo(KitsRestController.class).withSelfRel(),
                linkTo(KitsRestController.class).slash("/create").withRel("create"));
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
    public HttpEntity<KitResource> kit(@PathVariable("id") long id) throws Exception {
        log.debug("rest kit({})", id);
        KitDTO foundDTO = facade.findKitById(id);
        if (foundDTO == null) throw new ResourceNotFoundException("kit " + id + " not found");
        KitResource resource = resourceAssembler.toResource(foundDTO);
        return new HttpEntity<>(resource);
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<KitResource> createKit(@RequestBody @Valid KitCreateDTO paramDTOCreate, BindingResult bindingResult) throws Exception {
        log.debug("rest createKit()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new FormException("Validation failed", bindingResult);
        }
        Long id = facade.createKit(paramDTOCreate);
        KitDTO foundDTO = facade.findKitById(id);

        KitResource resource = resourceAssembler.toResource(foundDTO);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteKit(@PathVariable("id") long id) throws Exception {
        log.debug("rest deleteKit({})", id);
        try {
            facade.deleteKitById(id);
        } catch (IllegalArgumentException ex) {
            log.error("kit " + id + " not found");
            throw new ResourceNotFoundException("kit " + id + " not found");
        } catch (Throwable ex) {
            log.error("cannot delete kit " + id + " :" + ex.getMessage());
            throw new ResourceNotFoundException("Kit is being used in some set.");
        }
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final KitDTO changeKit(@PathVariable("id") long id, @RequestBody @Valid KitDTO updatedDTO, BindingResult bindingResult) throws Exception {
        log.debug("rest change kit({})", id);
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException(bindingResult.toString());
        }

        try {
            updatedDTO.setId(id);
            facade.updateKit(updatedDTO);

        } catch (Exception ex) {
            throw new ResourceNotFoundException("Unable to update kit");
        }
        return facade.findKitById(id);
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.EMPLOYEE)
    @RequestMapping(value = "/similar/{id}", method = RequestMethod.GET)
    public HttpEntity<Resources<KitResource>> similarKits(@PathVariable("id") long id) throws Exception {
        log.debug("rest findSimilarKits({})", id);

        Resources<KitResource> resources = new Resources<>(
                resourceAssembler.toResources(facade.findSimilarKits(id, 100, 100)),
                linkTo(KitsRestController.class).withSelfRel(),
                linkTo(KitsRestController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/createrandom", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<KitResource> createRandomKit(@RequestBody @Valid RandomBricksDTO paramDTOCreate, BindingResult bindingResult) throws Exception {
        log.debug("rest create random Kit()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new FormException("Validation failed", bindingResult);
        }


        List<BrickDTO> bricksReal = new ArrayList<>();
        for (BrickDTO brick :paramDTOCreate.getBricks()) {
            bricksReal.add(brickFacade.findById(brick.getId()));
        }

        System.out.println("RMUHAHAHA");
        KitDTO foundDTO = facade.createRandomKitByRules(paramDTOCreate.getMin(), paramDTOCreate.getMax(), bricksReal);
        System.out.println("RAAMUHAHAHA");

        KitResource resource = resourceAssembler.toResource(foundDTO);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}





