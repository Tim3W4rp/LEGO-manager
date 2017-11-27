package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.dto.BrickDTO;

import java.util.List;

/**
 * @author Lukáš Dvořák
 */
public interface BrickFacade {

    /**
     * Create the given brick.
     *
     * @param brickDTO brick to be created
     * @return created brick id
     */
    Long create(BrickDTO brickDTO);

    /**
     * Update the given brick.
     *
     * @param brickDTO brick to be updated
     */
    void update(BrickDTO brickDTO);

    /**
     * Delete the given brick.
     *
     * @param brickId id of the brick
     */
    void delete(Long brickId);

    /**
     * Get brick with the given id.
     *
     * @param brickId id of the brick
     * @return existing brick with given id
     */
    BrickDTO findById(Long brickId);

    /**
     * Get all existing bricks.
     *
     * @return list of existing bricks
     */
    List<BrickDTO> findAll();

}
