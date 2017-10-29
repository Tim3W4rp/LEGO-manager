package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.SetOfKits;

import java.util.List;

/**
 * @author Michal Peska
 */
public interface SetOfKitsDao {
    public void create(SetOfKits set);
    public SetOfKits findById(Long id);
    public void update(SetOfKits set);
    public void delete(SetOfKits set);
    public List<SetOfKits> findAll();
}
