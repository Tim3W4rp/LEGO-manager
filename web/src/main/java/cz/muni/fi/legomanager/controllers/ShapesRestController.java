package cz.muni.fi.legomanager.controllers;

import cz.fi.muni.legomanager.dto.ShapeCreateDTO;
import cz.fi.muni.legomanager.dto.ShapeDTO;
import cz.fi.muni.legomanager.facade.ShapeFacade;
import cz.muni.fi.legomanager.ApiUris;
import cz.muni.fi.legomanager.exceptions.InvalidRequestException;
import cz.muni.fi.legomanager.exceptions.ResourceNotFoundException;
import cz.muni.fi.legomanager.hateoas.ShapeResource;
import cz.muni.fi.legomanager.hateoas.ShapeResourceAssembler;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @author Lukáš Dvořák
 */
@RestController
@ExposesResourceFor(ShapeDTO.class)
@RequestMapping(ApiUris.ROOT_URI_SHAPES)
public class ShapesRestController {

    private final static Logger log = LoggerFactory.getLogger(ShapesRestController.class);
    private ShapeFacade shapeFacade;
    private ShapeResourceAssembler shapeResourceAssembler;
    private EntityLinks entityLinks;
    private List<ShapeDTO> allShapes = new ArrayList<>();

    public ShapesRestController(
            ShapeResourceAssembler shapeResourceAssembler,
            @SuppressWarnings("SpringJavaAutowiringInspection")
                    EntityLinks entityLinks,
            ShapeFacade shapeFacade
    ) {
        this.shapeFacade = shapeFacade;
        this.shapeResourceAssembler = shapeResourceAssembler;
        this.entityLinks = entityLinks;

        ShapeDTO shape1 = new ShapeDTO();
        shape1.setId(1L);
        shape1.setName("Cube");
        this.allShapes.add(shape1);

        ShapeDTO shape2 = new ShapeDTO();
        shape2.setId(2L);
        shape2.setName("Figure");
        this.allShapes.add(shape2);
    }

    /**
     * Produces list of all shapes in JSON.
     *
     * @return list of shapes
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.EMPLOYEE)
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Resources<ShapeResource>> shapes() {
        log.debug("rest shapes()");

        Resources<ShapeResource> productsResources = new Resources<>(
                shapeResourceAssembler.toResources(shapeFacade.findAll()),
                linkTo(ShapesRestController.class).withSelfRel(),
                linkTo(ShapesRestController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(productsResources, HttpStatus.OK);
    }

    /**
     * Produces shape detail.
     *
     * @param id shape identifier
     * @return shape detail
     * @throws ResourceNotFoundException if shape not found
     */
    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.EMPLOYEE)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<ShapeResource> shape(@PathVariable("id") long id) throws ResourceNotFoundException {
        log.debug("rest shape({})", id);
        ShapeDTO shapeDTO = shapeFacade.findById(id);
        if (shapeDTO == null) throw new ResourceNotFoundException("shape " + id + " not found");
        ShapeResource shapeResource = shapeResourceAssembler.toResource(shapeDTO);
        return new HttpEntity<>(shapeResource);
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<ShapeResource> createShape(@RequestBody @Valid ShapeCreateDTO shapeCreateDTO, BindingResult bindingResult) throws ResourceNotFoundException {
        log.debug("rest createShape()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }

        Long id = shapeFacade.create(shapeCreateDTO);
        ShapeDTO shapeDTO = shapeFacade.findById(id);

        ShapeResource resource = shapeResourceAssembler.toResource(shapeDTO);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteShape(@PathVariable("id") long id) throws Exception {
        log.debug("rest deleteShape({})", id);
        try {
            shapeFacade.delete(id);
        } catch (IllegalArgumentException ex) {
            log.error("shape " + id + " not found");
            throw new ResourceNotFoundException("shape " + id + " not found");
        } catch (Throwable ex) {
            log.error("cannot delete shape " + id + " :" + ex.getMessage());
            throw new ResourceNotFoundException("Unable to delete non existing item.");
        }
    }

    @ApplyAuthorizeFilter(securityLevel = SecurityLevel.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final ShapeDTO changeShape(@PathVariable("id") long id, @RequestBody @Valid ShapeDTO updatedShape) throws Exception {
        log.debug("rest change Shape({})", id);

        try {
            updatedShape.setId(id);
            shapeFacade.update(updatedShape);
            return shapeFacade.findById(id);
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Unable to update shape");
        }
    }
}
