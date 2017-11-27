package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.dto.CategoryDTO;
import cz.fi.muni.legomanager.dto.KitCreateDTO;
import cz.fi.muni.legomanager.dto.KitDTO;
import cz.fi.muni.legomanager.entity.Category;

import cz.fi.muni.legomanager.dto.BrickDTO;
import cz.fi.muni.legomanager.dto.CategoryDTO;
import cz.fi.muni.legomanager.dto.KitCreateDTO;
import cz.fi.muni.legomanager.dto.KitDTO;
import cz.fi.muni.legomanager.entity.Brick;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.services.DozerService;
import cz.fi.muni.legomanager.services.KitService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Martin Jordán
 */
@Service
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
        return dozerService.mapTo(kitService.getKitsByCategoryId(categoryId), KitDTO.class);
    }

    @Override
    public void addBrickToKit(Long kitId, Long brickId) {
        kitService.addBrickToKitById(kitId, brickId);
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
    public List<KitDTO> findSimilarKits(KitDTO kitDTO, int priceRange, int ageLimitRange, CategoryDTO categoryDTO) {
        Kit mappedKit = dozerService.mapTo(kitDTO, Kit.class);
        Category mappedCategory = dozerService.mapTo(categoryDTO, Category.class);
        return dozerService.mapTo(kitService.findSimilarKits(mappedKit, priceRange, ageLimitRange, mappedCategory), KitDTO.class);
    }

    @Override
    public long createRandomKitByRules(long minBrickCount, long maxBrickCount, Map<BrickDTO, Long> bricksCounts) {
        Map<Brick, Long> brickEntitiesCounts = new HashMap<Brick, Long>();

        for (Map.Entry<BrickDTO, Long> entry : bricksCounts.entrySet()) {
            Brick mappedBrick = dozerService.mapTo(entry.getKey(), Brick.class);
            brickEntitiesCounts.put(mappedBrick, entry.getValue());
        }
        
        return kitService.createRandomKitByRules(minBrickCount, maxBrickCount, brickEntitiesCounts);
    }
}
