package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.Kit;

import java.util.List;

/**
 * @author Martin Jordán
 */
public interface KitDao {
    /**
     * creates a new kit in DB
     *
     * @param kit to be added to DB
     */
    public Kit create(Kit kit);

    /**
     * @param id of a kit to be found
     * @return kit found with specified ID
     */
    public Kit findById(Long id);

    /**
     * updates a kit in DB
     *
     * @param kit to be updated in DB
     */
    public void update(Kit kit);

    /**
     * deletes a kit from DB
     *
     * @param kit to be deleted from DB
     */
    public void delete(Kit kit);

    /**
     * returns a list of all existing kits in DB
     *
     * @return list of all kits in DB
     */
    public List<Kit> findAll();
}
