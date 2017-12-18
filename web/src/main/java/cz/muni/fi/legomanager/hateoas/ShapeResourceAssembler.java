package cz.muni.fi.legomanager.hateoas;

import cz.fi.muni.legomanager.dto.ShapeDTO;
import cz.muni.fi.legomanager.controllers.ShapesRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Assembles a HATEOS-compliant representation of a shape from a ShapeDTO.
 *
 * @author Lukáš Dvořák
 */
@Component
public class ShapeResourceAssembler extends ResourceAssemblerSupport<ShapeDTO, ShapeResource> {

    private EntityLinks entityLinks;

    private final static Logger log = LoggerFactory.getLogger(ShapeResourceAssembler.class);

    public ShapeResourceAssembler(@SuppressWarnings("SpringJavaAutowiringInspection")
                                  @Autowired EntityLinks entityLinks) {
        super(ShapesRestController.class, ShapeResource.class);
        this.entityLinks = entityLinks;
    }

    @Override
    public ShapeResource toResource(ShapeDTO shapeDTO) {
        long id = shapeDTO.getId();
        ShapeResource shapeResource = new ShapeResource(shapeDTO);
        try {
            Link catLink = entityLinks.linkForSingleResource(ShapeDTO.class, id).withSelfRel();
            shapeResource.add(catLink);
        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }
        return shapeResource;
    }
}
