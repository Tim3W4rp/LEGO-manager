package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.dto.ShapeCreateDTO;
import cz.fi.muni.legomanager.dto.ShapeDTO;
import cz.fi.muni.legomanager.entity.Shape;
import cz.fi.muni.legomanager.services.DozerService;
import cz.fi.muni.legomanager.services.ShapeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * ShapeFacadeImpl implements {@link ShapeFacade}.
 *
 * @author Lukáš Dvořák
 */
@Service
@Transactional
public class ShapeFacadeImpl implements ShapeFacade {

    @Inject
    private ShapeService shapeService;

    @Inject
    private DozerService dozerService;

    @Override
    public Long create(ShapeCreateDTO shapeDTO) {
        Shape shape = dozerService.mapTo(shapeDTO, Shape.class);
        shapeService.create(shape);
        return shape.getId();
    }

    @Override
    public void update(ShapeDTO shapeDTO) {
        Shape shape = dozerService.mapTo(shapeDTO, Shape.class);
        shapeService.update(shape);
    }

    @Override
    public void delete(Long shapeId) {
        shapeService.delete(shapeService.findById(shapeId));
    }

    @Override
    public ShapeDTO findById(Long shapeId) {
        return dozerService.mapTo(shapeService.findById(shapeId), ShapeDTO.class);
    }

    @Override
    public List<ShapeDTO> findAll() {
        return dozerService.mapTo(shapeService.findAll(), ShapeDTO.class);
    }
    
}
