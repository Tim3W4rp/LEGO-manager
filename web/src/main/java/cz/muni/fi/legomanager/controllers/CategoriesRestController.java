package cz.muni.fi.legomanager.controllers;

import cz.fi.muni.legomanager.dto.*;
import cz.fi.muni.legomanager.facade.CategoryFacade;
import cz.muni.fi.legomanager.exceptions.InvalidRequestException;
import cz.muni.fi.legomanager.exceptions.ResourceNotFoundException;
import cz.muni.fi.legomanager.hateoas.CategoryResource;
import cz.muni.fi.legomanager.hateoas.CategoryResourceAssembler;
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
@ExposesResourceFor(CategoryDTO.class)
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:3000") // for development mode
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
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<CategoryResource> category(@PathVariable("id") long id) throws Exception {
        log.debug("rest cat({})", id);
        CategoryDTO foundDTO = facade.getCategoryById((id - 1));
        if (foundDTO == null) throw new ResourceNotFoundException("category " + id + " not found");
        CategoryResource resource = resourceAssembler.toResource(foundDTO);
        return new HttpEntity<>(resource);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CategoryResource> createCategory(@RequestBody @Valid CategoryDTO paramDTOCreate, BindingResult bindingResult) throws Exception {
        log.debug("rest createCategory()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        Long id = facade.createCategory(paramDTOCreate);
        CategoryDTO foundDTO = facade.getCategoryById(id);

        CategoryResource resource = resourceAssembler.toResource(foundDTO);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteCategory(@PathVariable("id") long id) throws Exception {
        log.debug("rest deleteCat({})", id);
        try {
            facade.removeCategory(id);
        } catch (IllegalArgumentException ex) {
            log.error("cat " + id + " not found");
            throw new ResourceNotFoundException("cat " + id + " not found");
        } catch (Throwable ex) {
            log.error("cannot delete cat " + id + " :" + ex.getMessage());
            throw new ResourceNotFoundException("Unable to delete non existing item");
        }
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    public final CategoryDTO changeCategory(@PathVariable("id") long id, @RequestBody @Valid CategoryDTO updatedDTO) throws Exception {
        log.debug("rest change Cat({})", id);

        try {
            updatedDTO.setId(id);
            facade.updateCategory(updatedDTO);
            return facade.getCategoryById(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Unable to update cat");
        }
    }


}





