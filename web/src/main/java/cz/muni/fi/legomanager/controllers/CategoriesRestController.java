package cz.muni.fi.legomanager.controllers;

import cz.fi.muni.legomanager.dto.*;
import cz.fi.muni.legomanager.facade.CategoryFacade;
import cz.muni.fi.legomanager.ApiUris;
import cz.muni.fi.legomanager.exceptions.FormException;
import cz.muni.fi.legomanager.exceptions.InvalidRequestException;
import cz.muni.fi.legomanager.exceptions.ResourceNotFoundException;
import cz.muni.fi.legomanager.hateoas.CategoryResource;
import cz.muni.fi.legomanager.hateoas.CategoryResourceAssembler;
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
 *
 * @author Michal Peška
 */

@RestController
@ExposesResourceFor(CategoryDTO.class)
@RequestMapping(ApiUris.ROOT_URI_CATEGORIES)
public class CategoriesRestController {

    private final static Logger log = LoggerFactory.getLogger(CategoriesRestController.class);
    private CategoryFacade facade;
    private CategoryResourceAssembler resourceAssembler;
    private EntityLinks entityLinks;


    @Autowired
    public CategoriesRestController(
            CategoryResourceAssembler resourceAssembler,
            @SuppressWarnings("SpringJavaAutowiringInspection")
            EntityLinks entityLinks,
            CategoryFacade facade) {

        this.resourceAssembler = resourceAssembler;
        this.entityLinks = entityLinks;
        this.facade = facade;

    }


    /**
     * Produces list of all categories in JSON.
     *
     * @return list of categories
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.EMPLOYEE)
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Resources<CategoryResource>> categories() {
        log.debug("rest cats()");

        Resources<CategoryResource> resources = new Resources<>(
                resourceAssembler.toResources(facade.getAllCategories()),
                linkTo(CategoriesRestController.class).withSelfRel(),
                linkTo(CategoriesRestController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(resources, HttpStatus.OK);
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
    public HttpEntity<CategoryResource> category(@PathVariable("id") long id) throws Exception {
        log.debug("rest cat({})", id);
        CategoryDTO foundDTO = facade.getCategoryById(id);
        if (foundDTO == null) throw new ResourceNotFoundException("category " + id + " not found");
        CategoryResource resource = resourceAssembler.toResource(foundDTO);
        return new HttpEntity<>(resource);
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CategoryResource> createCategory(@RequestBody @Valid CategoryDTO paramDTOCreate, BindingResult bindingResult) throws Exception {
        log.debug("rest createCategory()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new FormException("Validation failed", bindingResult);
        }
        Long id;
        try {
            id = facade.createCategory(paramDTOCreate);
        } catch (JpaSystemException ex) {
            log.error("Category with this name already exists");
            throw new InvalidRequestException("Category with this name already exists");
        }

        CategoryDTO foundDTO = facade.getCategoryById(id);
        CategoryResource resource = resourceAssembler.toResource(foundDTO);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteCategory(@PathVariable("id") long id) throws Exception {
        log.debug("rest deleteCat({})", id);
        try {
            facade.removeCategory(id);
        } catch (IllegalArgumentException ex) {
            log.error("category " + id + " not found");
            throw new ResourceNotFoundException("category " + id + " not found");
        } catch(JpaSystemException ex) {
            log.error("Category is not empty");
            throw new InvalidRequestException("Category is not empty");
        }
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    public final CategoryDTO changeCategory(@PathVariable("id") long id, @RequestBody @Valid CategoryDTO updatedDTO, BindingResult bindingResult) throws Exception {
        log.debug("rest change Cat({})", id);
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new FormException("Validation failed", bindingResult);
        }

        try {
            updatedDTO.setId(id);
            facade.updateCategory(updatedDTO);
        } catch (JpaSystemException ex) {
            log.error("Category with this name already exists");
            throw new InvalidRequestException("Category with this name already exists");
        }
        return facade.getCategoryById(id);

    }


}





