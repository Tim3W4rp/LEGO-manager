package cz.muni.fi.legomanager.controllers;

import cz.fi.muni.legomanager.dto.CategoryDTO;
import cz.fi.muni.legomanager.facade.CategoryFacade;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * SpringMVC controller for managing REST requests for the category resources. Conforms to HATEOAS principles.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@RestController
@ExposesResourceFor(CategoryDTO.class)
@RequestMapping("/categories")
public class CategoriesRestController {

    private final static Logger log = LoggerFactory.getLogger(CategoriesRestController.class);

    public CategoriesRestController(
            @Autowired CategoryResourceAssembler categoryResourceAssembler,
            @SuppressWarnings("SpringJavaAutowiringInspection")
            @Autowired EntityLinks entityLinks
    ) {
        this.categoryFacade = categoryFacade;
        this.categoryResourceAssembler = categoryResourceAssembler;
        this.entityLinks = entityLinks;
    }

    private CategoryFacade categoryFacade;

    private CategoryResourceAssembler categoryResourceAssembler;

    private EntityLinks entityLinks;

    /**
     * Produces list of all categories in JSON.
     *
     * @return list of categories
     */
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Resources<CategoryResource>> categories() {
        log.debug("rest categories()");
        List<CategoryDTO> allCategories = categoryFacade.getAllCategories();
        Resources<CategoryResource> productsResources = new Resources<>(
                categoryResourceAssembler.toResources(allCategories),
                linkTo(CategoriesRestController.class).withSelfRel(),
                linkTo(CategoriesRestController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(productsResources, HttpStatus.OK);
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
        log.debug("rest category({})", id);
        CategoryDTO categoryDTO = categoryFacade.getCategoryById(id);
        if (categoryDTO == null) throw new ResourceNotFoundException("category " + id + " not found");
        CategoryResource categoryResource = categoryResourceAssembler.toResource(categoryDTO);
        return new HttpEntity<>(categoryResource);
    }
}


