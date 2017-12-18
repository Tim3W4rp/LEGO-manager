package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.KitBrick;

import java.util.List;

/**
 * @author Martin Jordán
 */
public interface KitBrickDao {
    /**
     * creates a new kit in DB
     *
     * @param kitBrick to be added to DB
     */
    public KitBrick create(KitBrick kitBrick);

    /**
     * @param id of a kit to be found
     * @return kit found with specified ID
     */
    public KitBrick findById(Long id);

    /**
     * updates a kit in DB
     *
     * @param kitBrick to be updated in DB
     */
    public void update(KitBrick kitBrick);

    /**
     * deletes a kit from DB
     *
     * @param kitBrick to be deleted from DB
     */
    public void delete(KitBrick kitBrick);

    /**
     * returns a list of all existing kits in DB
     *
     * @return list of all kits in DB
     */
    public List<KitBrick> findAll();

}
