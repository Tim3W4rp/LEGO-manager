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
    void create(Shape shape);

    /**
     * Updates an already existing shape in DB.
     *
     * @param shape shape to be updated in DB
     */
    void update(Shape shape);

    /**
     * Deletes a shape from DB.
     *
     * @param shape shape to be deleted from DB
     */
    void delete(Shape shape);

    /**
     * Returns shape with corresponding id.
     *
     * @param id id of the shape
     * @return shape with corresponding id
     */
    Shape findById(Long id);

    /**
     * Returns shape with corresponding name.
     *
     * @param name name of the shape
     * @return shape with corresponding name
     */
    Shape findByName(String name);

    /**
     * Returns list of all existing shapes in DB.
     *
     * @return list of all existing shapes
     */
    List<Shape> findAll();

}
