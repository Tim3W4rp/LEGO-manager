package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.dao.SetOfKitsDao;
import cz.fi.muni.legomanager.dao.KitDao;
import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.SetOfKits;
import cz.fi.muni.legomanager.entity.Kit;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author Michal Peška
 */
@Service
public class SetOfKitsServiceImpl implements SetOfKitsService {

    @Inject
    private SetOfKitsDao setDao;

    @Inject
    private KitDao kitDao;

    @Override
    public SetOfKits getSet(long id) {
        return setDao.findById(id);
    }

    @Override
    public List<SetOfKits> getAllSets() {
        return setDao.findAll();
    }

    @Override
    public void create(SetOfKits set) {
        setDao.create(set);
    }

    @Override
    public void delete(long id) {
        SetOfKits set = setDao.findById(id);
        if (set == null) {
            throw new RuntimeException("This set of kits does not exist");
        }
        setDao.delete(set);
    }

    @Override
    public void update(SetOfKits set) {
        setDao.update(set);
    }

    @Override
    public void removeKitFromSet(long setId, long kitId) {
        SetOfKits set = setDao.findById(setId);
        Kit kit = kitDao.findById(kitId);
        set.removeKit(kit);
    }

    @Override
    public void addKitToSet(long setId, long kitId) {
        setDao.findById(setId).addKit(kitDao.findById(kitId));
    }

    @Override
    public List<Category> getSetCategories(long setId) {
        List<Category> categories = new ArrayList<>();

        SetOfKits set = setDao.findById(setId);
        Set<Kit> kitsInSet = set.getKits();

        for (Kit kit: kitsInSet) {
            if (!categories.contains(kit.getCategory()))
                categories.add(kit.getCategory());
        }
        return categories;
    }

}
