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
    public KitDTO findKitById(Long kitId) {
        return dozerService.mapTo(kitService.findKitById(kitId), KitDTO.class);
    }

    @Override
    public void updateKit(KitDTO kitDTO) {
        Kit mappedKit = dozerService.mapTo(kitDTO, Kit.class);
        kitService.updateKit(mappedKit);
    }

    @Override
    public void deleteKitById(Long kitId) {
        kitService.deleteKitById(kitId);
    }

    @Override
    public void changeDescription(Long kitId, String newDescription) {
        kitService.findKitById(kitId).setDescription(newDescription);
    }

    @Override
    public void changePrice(Long kitId, Integer newPrice) {
        kitService.findKitById(kitId).setPrice(newPrice);
    }

    @Override
    public void changeAgeLimit(Long kitId, Integer newAgeLimit) {
        kitService.findKitById(kitId).setAgeLimit(newAgeLimit);
    }

    @Override
    public List<KitDTO> findAllKits() {
        return dozerService.mapTo(kitService.findAllKits(), KitDTO.class);
    }

    @Override
    public List<KitDTO> getKitsByCategoryId(Long categoryId) {
        return dozerService.mapTo(kitService.getKitsByCategory(categoryId), KitDTO.class);
    }

    @Override
    public void addCategory(Long kitId, Long categoryId) {
        kitService.addCategory(kitId, categoryId);
    }

    @Override
    public void removeCategory(Long kitId, Long categoryId) {
        kitService.removeCategory(kitId, categoryId);
    }

    @Override
    public List<CategoryDTO> getKitCategories(Long kitId) {
        return dozerService.mapTo(kitService.getKitCategories(kitId), CategoryDTO.class);
    }

    @Override
    public void addBrickToKit(Long kitId, Long brickId) {
        kitService.addBrickToKit(kitId, brickId);
    }

    @Override
    public void removeOneBrickFromKitById(Long kitId, Long brickId) {
        kitService.removeOneBrickFromKitById(kitId, brickId);
    }

    @Override
    public void removeAllBricksOfThisTypeFromKitById(Long kitId, Long brickId) {
        kitService.removeAllBricksOfThisTypeFromKitById(kitId, brickId);
    }

    @Override
    public KitDTO findOneRandomSimilarKit(KitDTO kitDTO) {
        Kit mappedKit = dozerService.mapTo(kitDTO, Kit.class);
        return dozerService.mapTo(kitService.findOneRandomSimilarKit(mappedKit), KitDTO.class);
    }

    @Override
    public List<KitDTO> findSimilarKits(KitDTO kitDTO) {
        Kit mappedKit = dozerService.mapTo(kitDTO, Kit.class);
        return dozerService.mapTo(kitService.findSimilarKits(mappedKit), KitDTO.class);
    }
}
