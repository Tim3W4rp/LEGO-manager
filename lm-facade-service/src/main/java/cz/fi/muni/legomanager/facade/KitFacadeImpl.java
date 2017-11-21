package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.dto.CategoryDTO;
import cz.fi.muni.legomanager.dto.KitCreateDTO;
import cz.fi.muni.legomanager.dto.KitDTO;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.services.DozerService;
import cz.fi.muni.legomanager.services.KitService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Martin Jordán
 */
public class KitFacadeImpl implements KitFacade {

    @Autowired
    private KitService kitService;

    @Autowired
    private DozerService dozerService;

    @Override
    public Long createKit(KitCreateDTO kitCreateDTO) {
        Kit mappedKit = dozerService.mapTo(kitCreateDTO, Kit.class);
        kitService.createKit(mappedKit);
        return mappedKit.getId();
    }

    @Override
    public void addCategory(Long kitId, Long categoryId) {

    }

    @Override
    public void removeCategory(Long kitId, Long categoryId) {

    }

    @Override
    public void changeDescription(String newDescription) {

    }

    @Override
    public void changePrice(Integer newPrice) {

    }

    @Override
    public void changeAgeLimit(Integer newAgeLimit) {

    }

    @Override
    public void deleteKitById(Long kitId) {

    }

    @Override
    public void deleteKit(KitDTO kitDTO) {

    }

    @Override
    public List<KitDTO> getAllKits() {
        return null;
    }

    @Override
    public List<KitDTO> getKitsByCategory(Long categoryId) {
        return null;
    }

    @Override
    public List<CategoryDTO> getKitCategories() {
        return null;
    }

    @Override
    public KitDTO getKitById(Long kitId) {
        return null;
    }

    @Override
    public void addBrickToKit(Long kitId, Long brickId) {

    }

    @Override
    public void removeOneBrickFromKitById(Long kitId, Long brickId) {

    }

    @Override
    public void removeAllBricksOfThisTypeFromKitById(Long kitId, Long brickId) {

    }

    @Override
    public KitDTO findOneRandomSimilarKit(KitDTO kitDTO) {
        return null;
    }

    @Override
    public List<KitDTO> findSimilarKits(KitDTO kitDTO) {
        return null;
    }
}
