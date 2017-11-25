package cz.fi.muni.legomanager.services;

import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.SetOfKits;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Michal Peška
 */
@Service
public interface SetOfKitsService {
    SetOfKits getSet(long id);
    List<SetOfKits> getAllSets();
    void create(SetOfKits set);
    void delete(long id);
    void update(SetOfKits set);
    void removeKitFromSet(long setId, long kitId);
    void addKitToSet(long setId, long kitId);
    List<Category> getSetCategories(long setId);

}
