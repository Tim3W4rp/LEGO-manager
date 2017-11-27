package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.dto.BrickDTO;
import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.services.BrickService;
import cz.fi.muni.legomanager.services.DozerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * BrickFacadeImpl implements {@link BrickFacade}.
 *
 * @author Lukáš Dvořák
 */
@Service
@Transactional
public class BrickFacadeImpl implements BrickFacade {

    @Inject
    private BrickService brickService;

    @Inject
    private DozerService dozerService;

    @Override
    public Long create(BrickDTO brickDTO) {
        Brick brick = dozerService.mapTo(brickDTO, Brick.class);
        brickService.create(brick);
        return brick.getId();
    }

    @Override
    public void update(BrickDTO brickDTO) {
        Brick brick = dozerService.mapTo(brickDTO, Brick.class);
        brickService.update(brick);
    }

    @Override
    public void delete(Long brickId) {
        brickService.delete(brickService.findById(brickId));
    }

    @Override
    public BrickDTO findById(Long brickId) {
        return dozerService.mapTo(brickService.findById(brickId), BrickDTO.class);
    }

    @Override
    public List<BrickDTO> findAll() {
        return dozerService.mapTo(brickService.findAll(), BrickDTO.class);
    }

}
