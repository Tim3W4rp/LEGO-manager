package cz.muni.fi.legomanager.hateoas;

import cz.fi.muni.legomanager.dto.SetOfKitsDTO;
import cz.muni.fi.legomanager.controllers.SetsRestController;
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
 * @author Štěpán Granát
 */
@Component
public class BrickResourceAssembler extends ResourceAssemblerSupport<SetOfKitsDTO, SetResource> {


    private EntityLinks entityLinks;

    private final static Logger log = LoggerFactory.getLogger(BrickResourceAssembler.class);

    public BrickResourceAssembler(@SuppressWarnings("SpringJavaAutowiringInspection")
                                     @Autowired EntityLinks entityLinks) {
        super(SetsRestController.class, SetResource.class);
        this.entityLinks = entityLinks;
    }

    @Override
    public SetResource toResource(SetOfKitsDTO setDTO) {
        long id = setDTO.getId();
        SetResource setResource = new SetResource(setDTO);
        try {
            Link setLink = entityLinks.linkForSingleResource(SetOfKitsDTO.class, id).withSelfRel();
            setResource.add(setLink);
        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }
        return setResource;
    }
}
