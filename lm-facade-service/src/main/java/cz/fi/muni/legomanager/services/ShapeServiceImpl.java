package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.dao.ShapeDao;
import cz.fi.muni.legomanager.entity.Shape;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the {@link ShapeService}.
 *
 * @author Lukáš Dvořák
 */
@Service
public class ShapeServiceImpl implements ShapeService {

    @Inject
    private ShapeDao shapeDao;

    @Override
    public void create(Shape shape) {
        shapeDao.create(shape);
    }

    @Override
    public void update(Shape shape) {
        shapeDao.update(shape);
    }

    @Override
    public void delete(Shape shape) {
        shapeDao.delete(shape);
    }

    @Override
    public Shape findById(Long id) {
        return shapeDao.findById(id);
    }

    @Override
    public List<Shape> findAll() {
        return shapeDao.findAll();
    }
}
