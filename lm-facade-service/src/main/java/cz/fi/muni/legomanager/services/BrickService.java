package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.entity.Brick;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for entity {@link Brick}.
 *
 * @author Lukáš Dvořák
 */
@Service
public interface BrickService {

    /**
     * Create the given Brick entity.
     *
     * @param brick entity
     */
    public void create(Brick brick);

    /**
     * Update the given existing Brick entity.
     *
     * @param brick entity
     */
    public void update(Brick brick);

    /**
     * Delete the given existing Brick entity.
     *
     * @param brick entity
     */
    public void delete(Brick brick);

    /**
     * Get Brick entity with the given ID.
     *
     * @param id id of the brick
     * @return existing Brick with given id
     */
    public Brick findById(Long id);

    /**
     * Get all existing Brick entities.
     *
     * @return list of existing Brick entities
     */
    public List<Brick> findAll();
}
