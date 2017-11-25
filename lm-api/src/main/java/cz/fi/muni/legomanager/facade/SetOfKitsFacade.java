package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.dto.SetOfKitsCreateDTO;
import cz.fi.muni.legomanager.dto.SetOfKitsDTO;
import cz.fi.muni.legomanager.dto.CategoryDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Michal Peška
 */
public interface SetOfKitsFacade {
    public Long createSet(SetOfKitsCreateDTO setCreateDTO);
    public SetOfKitsDTO findSetById(Long setId);
    public void updateSet(SetOfKitsDTO setDTO);
    public void deleteSetById(Long setId);
    public void changeDescription(Long setId, String newDescription);
    public void changePrice(Long setId, BigDecimal newPrice);
    public List<SetOfKitsDTO> getAllSets();
    public void addKitToSet(Long setId, Long kitId);
    public void removeKitFromSet(Long setId, Long kitId);
    public List<CategoryDTO> getSetCategories(long setId);


}
