package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.Kit;

/**
 * @author Martin Jordán
 */
public interface KitDao {
    public void create(Kit kit);
    public Kit findById(Long id);
    public void update(Kit kit);
    public void delete(Kit kit);
}
