package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.dao.SetOfKitsDao;
import cz.fi.muni.legomanager.entity.SetOfKits;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Michal Peška
 */
@Service
public class SetOfKitsServiceImpl implements SetOfKitsService {
    private SetOfKitsDao setDao;

    @Inject
    public SetOfKitsServiceImpl(SetOfKitsDao setDao) {
        this.setDao = setDao;
    }

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

}
