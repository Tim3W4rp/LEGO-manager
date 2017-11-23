package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.dto.SetOfKitsCreateDTO;
import cz.fi.muni.legomanager.dto.SetOfKitsDTO;

import java.util.List;

/**
 * @author Michal Peška
 */
public interface SetOfKitsFacade {
    public Long createSet(SetOfKitsCreateDTO setCreateDTO);
    public SetOfKitsDTO findSetById(Long kitId);
    public void updateSet(SetOfKitsDTO kitDTO);
    public void deleteSetById(Long kitId);
    public void changeDescription(Long kitId, String newDescription);
    public void changePrice(Long kitId, Integer newPrice);
    public void changeAgeLimit(Long kitId, Integer newAgeLimit);
    public List<SetOfKitsDTO> getAllSets();
    public List<SetOfKitsDTO> getSetsByCategoryId(Long categoryId);
    public void addKitToSet(Long kitId, Long brickId);
    public void removeOneKitFromSetById(Long kitId, Long brickId);

}
