package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.Kit;

public interface KitDao {
    public void create(Kit kit);
    public Kit findById(Long id);
    public void update(Kit kit, String description, Integer price, Integer ageLimit);
    public void delete(Kit kit);

}
