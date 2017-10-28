/**
 * @author Martin Jordán
 */

package cz.fi.muni.legomanager.Dao;

import cz.fi.muni.legomanager.entity.Kit;

public interface KitDao {
    public void create(Kit kit);
    public Kit findById(Long id);
    public void update(Kit kit);
    public void delete(Kit kit);
}
