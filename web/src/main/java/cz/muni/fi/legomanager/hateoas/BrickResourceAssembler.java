package cz.muni.fi.legomanager.hateoas;

import cz.fi.muni.legomanager.dto.*;
import cz.muni.fi.legomanager.controllers.BricksRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

/**
 * Assembles a HATEOS-compliant representation of a category from a CategoryDTO.
 *
 * @author Michal Peška, partly
 */
@Component
public class BrickResourceAssembler extends ResourceAssemblerSupport<BrickDTO, BrickResource> {


    private EntityLinks entityLinks;

    private final static Logger log = LoggerFactory.getLogger(BrickResourceAssembler.class);

    public BrickResourceAssembler(@SuppressWarnings("SpringJavaAutowiringInspection")
                                     @Autowired EntityLinks entityLinks) {
        super(BricksRestController.class, BrickResource.class);
        this.entityLinks = entityLinks;
    }

    @Override
    public BrickResource toResource(BrickDTO brickDTO) {
        long id = brickDTO.getId();
        BrickResource brickResource = new BrickResource(brickDTO);
        try {
            Link setLink = entityLinks.linkForSingleResource(BrickDTO.class, id).withSelfRel();
            brickResource.add(setLink);
        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }
        return brickResource;
    }
}
