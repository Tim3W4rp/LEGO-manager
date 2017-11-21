package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.dto.KitCreateDTO;
import cz.fi.muni.legomanager.dto.KitDTO;

import java.util.List;

/**
 * @author Martin Jordán
 */
public interface KitFacade {
    public Long createKit(KitCreateDTO kitCreateDTO);
    public void addCategory(Long kitId, Long categoryId);
    public void removeCategory(Long kitId, Long categoryId);
    public void changePrice(Integer newPrice);
    public void deleteKit();
    public List<KitDTO> getAllKits();
    public List<KitDTO> getKitsByCategory();
    public KitDTO getKitById();

}
