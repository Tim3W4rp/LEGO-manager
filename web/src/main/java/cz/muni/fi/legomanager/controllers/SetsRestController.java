package cz.muni.fi.legomanager.controllers;

import cz.fi.muni.legomanager.dto.*;

import cz.fi.muni.legomanager.facade.SetOfKitsFacade;
import cz.muni.fi.legomanager.ApiUris;
import cz.muni.fi.legomanager.exceptions.ErrorResource;
import cz.muni.fi.legomanager.exceptions.FormException;
import cz.muni.fi.legomanager.exceptions.InvalidRequestException;
import cz.muni.fi.legomanager.exceptions.ResourceNotFoundException;
import cz.muni.fi.legomanager.hateoas.SetResource;
import cz.muni.fi.legomanager.hateoas.SetResourceAssembler;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @author Michal Peška, partly
 */

@RestController
@ExposesResourceFor(SetOfKitsDTO.class)
@RequestMapping(ApiUris.ROOT_URI_SETS)
public class SetsRestController {

    private final static Logger log = LoggerFactory.getLogger(SetsRestController.class);
    private SetOfKitsFacade setFacade;
    private SetResourceAssembler setResourceAssembler;
    private EntityLinks entityLinks;


    @Autowired
    public SetsRestController(
            SetResourceAssembler setResourceAssembler,
            @SuppressWarnings("SpringJavaAutowiringInspection")
                    EntityLinks entityLinks,
            SetOfKitsFacade setFacade) {

        this.setResourceAssembler = setResourceAssembler;
        this.entityLinks = entityLinks;
        this.setFacade = setFacade;


    }


    /**
     * Produces list of all categories in JSON.
     *
     * @return list of categories
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.EMPLOYEE)
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Resources<SetResource>> sets() {
        log.debug("rest sets()");

        Resources<SetResource> setsResources = new Resources<>(
                setResourceAssembler.toResources(setFacade.getAllSets()),
                linkTo(SetsRestController.class).withSelfRel(),
                linkTo(SetsRestController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(setsResources, HttpStatus.OK);
    }

    /**
     * Produces category detail.
     *
     * @param id category identifier
     * @return category detail
     * @throws Exception if category not found
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.EMPLOYEE)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<SetResource> set(@PathVariable("id") long id) throws Exception {
        log.debug("rest set({})", id);
        SetOfKitsDTO setDTO = setFacade.findSetById(id);
        if (setDTO == null) throw new ResourceNotFoundException("category " + id + " not found");
        SetResource setResource = setResourceAssembler.toResource(setDTO);
        return new HttpEntity<>(setResource);
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<SetResource> createSet(@RequestBody @Valid SetOfKitsCreateDTO setDTOCreate, BindingResult bindingResult) throws Exception {
        log.debug("rest createSet)");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new FormException("Validation failed", bindingResult);
        }
        Long id = setFacade.createSet(setDTOCreate);
        SetOfKitsDTO setDTO = setFacade.findSetById(id);

        SetResource resource = setResourceAssembler.toResource(setDTO);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteSet(@PathVariable("id") long id) throws Exception {
        log.debug("rest deleteSet({})", id);
        try {
            setFacade.deleteSetById(id);
        } catch (IllegalArgumentException ex) {
            log.error("set " + id + " not found");
            throw new ResourceNotFoundException("set " + id + " not found");
        } catch (Throwable ex) {
            log.error("cannot delete set " + id + " :" + ex.getMessage());
            throw new ResourceNotFoundException("Set is being used is some category.");
        }
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final SetOfKitsDTO changeSet(@PathVariable("id") long id, @RequestBody @Valid SetOfKitsDTO updatedSet, BindingResult bindingResult) throws Exception {
        log.debug("rest change Set({})", id);
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new FormException("Validation failed", bindingResult);
        }


        try {
            updatedSet.setId(id);
            setFacade.updateSet(updatedSet);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Unable to update set");
        }

        return setFacade.findSetById(id);
    }


}





