package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.entity.Shape;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for entity {@link Shape}.
 *
 * @author Lukáš Dvořák
 */
@Service
public interface ShapeService {

    /**
     * Create the given Shape entity.
     *
     * @param shape entity
     */
    void create(Shape shape);

    /**
     * Update the given existing Shape entity.
     *
     * @param shape entity
     */
    void update(Shape shape);

    /**
     * Delete the given existing Shape entity.
     *
     * @param shape entity
     */
    void delete(Shape shape);

    /**
     * Get Shape entity with the given ID.
     *
     * @param id id of the shape
     * @return existing Shape with given id
     */
    Shape findById(Long id);

    /**
     * Get Shape entity with the given name.
     *
     * @param name name of the shape
     * @return existing Shape with given name
     */
    Shape findByName(String name);

    /**
     * Get all existing Shape entities.
     *
     * @return list of existing Shape entities
     */
    List<Shape> findAll();
}
