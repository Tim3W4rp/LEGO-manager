package cz.muni.fi.legomanager.hateoas;

import cz.fi.muni.legomanager.dto.*;
import cz.muni.fi.legomanager.controllers.KitsRestController;
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
 * @author Michal Peška
 */
@Component
public class KitResourceAssembler extends ResourceAssemblerSupport<KitDTO, KitResource> {


    private EntityLinks entityLinks;

    private final static Logger log = LoggerFactory.getLogger(KitResourceAssembler.class);

    public KitResourceAssembler(@SuppressWarnings("SpringJavaAutowiringInspection")
                                     @Autowired EntityLinks entityLinks) {
        super(KitsRestController.class, KitResource.class);
        this.entityLinks = entityLinks;
    }

    @Override
    public KitResource toResource(KitDTO kitDTO) {
        long id = kitDTO.getId();
        KitResource kitResource = new KitResource(kitDTO);
        try {
            Link setLink = entityLinks.linkForSingleResource(KitDTO.class, id).withSelfRel();
            kitResource.add(setLink);
        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }
        return kitResource;
    }
}
