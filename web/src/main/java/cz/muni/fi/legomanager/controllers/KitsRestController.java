package cz.muni.fi.legomanager.controllers;

import cz.fi.muni.legomanager.dto.*;
import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.facade.CategoryFacade;
import cz.fi.muni.legomanager.facade.KitFacade;
import cz.muni.fi.legomanager.ApiUris;
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
    private KitResourceAssembler resourceAssembler;
    private EntityLinks entityLinks;


    @Autowired
    public KitsRestController(
            KitResourceAssembler resourceAssembler,
            @SuppressWarnings("SpringJavaAutowiringInspection")
                    EntityLinks entityLinks,
            KitFacade facade,
            CategoryFacade categoryFacade) {

        this.resourceAssembler = resourceAssembler;
        this.entityLinks = entityLinks;
        this.facade = facade;
        this.categoryFacade = categoryFacade;

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
            throw new InvalidRequestException("Failed validation");
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
            throw new ResourceNotFoundException("Unable to delete non existing item");
        }
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final KitDTO changeKit(@PathVariable("id") long id, @RequestBody @Valid KitDTO updatedDTO) throws Exception {
        log.debug("rest change kit({})", id);

        try {
            updatedDTO.setId(id);
            facade.updateKit(updatedDTO);
            return facade.findKitById(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Unable to update kit");
        }
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.EMPLOYEE)
    @RequestMapping(value = "/{id}/{priceRange}/{ageLimitRange}/{categoryId}", method = RequestMethod.GET)
    public final List<KitDTO> findSimilarKits(@PathVariable("id") long kitId,
                                              @PathVariable("priceRange") int priceRange,
                                              @PathVariable("ageLimitRange") int ageLimitRange,
                                              @PathVariable("categoryId") Long categoryId) throws Exception {
        log.debug("rest findSimilarKits({})", kitId);

        try {
            return facade.findSimilarKits(facade.findKitById(kitId), priceRange, ageLimitRange, categoryFacade.getCategoryById(categoryId));
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Unable to find similar kits");
        }
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/createrandom", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<KitResource> createRandomKit(@RequestBody @Valid RandomBricksDTO paramDTOCreate, BindingResult bindingResult) throws Exception {
        log.debug("rest create random Kit()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        KitDTO foundDTO = facade.createRandomKitByRules(paramDTOCreate.getMin(), paramDTOCreate.getMax(), paramDTOCreate.getBricks());

        KitResource resource = resourceAssembler.toResource(foundDTO);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}





