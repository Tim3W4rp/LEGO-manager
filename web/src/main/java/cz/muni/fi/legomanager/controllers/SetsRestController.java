package cz.muni.fi.legomanager.controllers;

import cz.fi.muni.legomanager.dto.SetOfKitsDTO;
import cz.fi.muni.legomanager.facade.SetOfKitsFacade;
import cz.muni.fi.legomanager.exceptions.InvalidRequestException;
import cz.muni.fi.legomanager.exceptions.ResourceNotFoundException;
import cz.muni.fi.legomanager.hateoas.SetResource;
import cz.muni.fi.legomanager.hateoas.SetResourceAssembler;
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
 *
 * @author Michal Peška, partly
 */
@RestController
@ExposesResourceFor(SetOfKitsDTO.class)
@RequestMapping("/sets")
@CrossOrigin(origins = "http://localhost:3000") // for development mode
public class SetsRestController {

    private final static Logger log = LoggerFactory.getLogger(SetsRestController.class);

    public SetsRestController(
            @Autowired SetResourceAssembler setResourceAssembler,
            @SuppressWarnings("SpringJavaAutowiringInspection")
            @Autowired EntityLinks entityLinks
            //@Autowired CategoryFacade categoryFacade
    ) {

        this.setResourceAssembler = setResourceAssembler;
        this.entityLinks = entityLinks;

        SetOfKitsDTO exmpl1 = new SetOfKitsDTO();
        exmpl1.setId(1L);
        exmpl1.setPrice(BigDecimal.valueOf(100));
        exmpl1.setDescription("Set 1");
        this.allSets.add(exmpl1);

        SetOfKitsDTO exmpl2 = new SetOfKitsDTO();
        exmpl2.setId(2L);
        exmpl2.setPrice(BigDecimal.valueOf(300));
        exmpl2.setDescription("Set 2");
        this.allSets.add(exmpl2);
    }

    private List<SetOfKitsDTO> allSets = new ArrayList<>();

    @Autowired
    private SetOfKitsFacade setFacade;

    private SetResourceAssembler setResourceAssembler;

    private EntityLinks entityLinks;

    /**
     * Produces list of all categories in JSON.
     *
     * @return list of categories
     */
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Resources<SetResource>> sets() {
        log.debug("rest sets()");

        Resources<SetResource> setsResources = new Resources<>(
                setResourceAssembler.toResources(allSets),
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
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<SetResource> set(@PathVariable("id") long id) throws Exception {
        log.debug("rest set({})", id);
        SetOfKitsDTO setDTO = allSets.get((int)(id - 1));
        if (setDTO == null) throw new ResourceNotFoundException("category " + id + " not found");
        SetResource setResource = setResourceAssembler.toResource(setDTO);
        return new HttpEntity<>(setResource);
    }

    @RequestMapping(method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<SetResource> createCategory(@RequestBody @Valid SetOfKitsDTO setDTO, BindingResult bindingResult) throws Exception {
        log.debug("rest createCategory()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        setDTO.setId(allSets.get(allSets.size() - 1).getId() + 1);
        this.allSets.add(setDTO);
        SetResource resource = setResourceAssembler.toResource(setDTO);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}


