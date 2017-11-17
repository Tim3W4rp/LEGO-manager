package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.Shape;

import java.util.List;

/**
 * CRUD operations for entity Shape.
 *
 * @author Lukáš Dvořák
 */
public interface ShapeDao {

    /**
     * Creates a new shape in DB.
     *
     * @param shape shape to be added to DB
     */
    public void create(Shape shape);

    /**
     * Updates an already existing shape in DB.
     *
     * @param shape shape to be updated in DB
     */
    public void update(Shape shape);

    /**
     * Deletes a shape from DB.
     *
     * @param shape shape to be deleted from DB
     */
    public void delete(Shape shape);

    /**
     * Returns shape with corresponding id.
     *
     * @param id id of the shape
     * @return shape with corresponding id
     */
    public Shape findById(Long id);

    /**
     * Returns list of all existing shapes in DB.
     *
     * @return list of all existing shapes
     */
    public List<Shape> findAll();

}
