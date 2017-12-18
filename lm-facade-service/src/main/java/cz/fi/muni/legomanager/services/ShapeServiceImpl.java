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
        if (shape == null) {
            throw new IllegalArgumentException("Shape cannot be null.");
        }

        shapeDao.create(shape);
    }

    @Override
    public void update(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Shape cannot be null.");
        }

        shapeDao.update(shape);
    }

    @Override
    public void delete(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Shape cannot be null.");
        }

        shapeDao.delete(shape);
    }

    @Override
    public Shape findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Shape id cannot be null.");
        }

        return shapeDao.findById(id);
    }

    @Override
    public Shape findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Shape name cannot be null.");
        }
        return shapeDao.findByName(name);
    }

    @Override
    public List<Shape> findAll() {
        return shapeDao.findAll();
    }
}
