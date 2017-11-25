package cz.fi.muni.legomanager.facade;

import cz.fi.muni.legomanager.dto.SetOfKitsCreateDTO;
import cz.fi.muni.legomanager.entity.Category;
import cz.fi.muni.legomanager.entity.Kit;
import cz.fi.muni.legomanager.entity.SetOfKits;
import cz.fi.muni.legomanager.services.DozerService;
import cz.fi.muni.legomanager.services.KitService;
import cz.fi.muni.legomanager.services.SetOfKitsService;
import org.dozer.Mapper;

import cz.fi.muni.legomanager.dto.CategoryDTO;
import cz.fi.muni.legomanager.dto.KitDTO;
import cz.fi.muni.legomanager.dto.SetOfKitsDTO;
import cz.fi.muni.legomanager.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class SetOfKitsFacadeImpl implements SetOfKitsFacade {
    @Autowired
    private SetOfKitsService setService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DozerService dozerService;


    @Override
    public Long createSet(SetOfKitsCreateDTO setCreateDTO) {
        SetOfKits mappedSet = dozerService.mapTo(setCreateDTO, SetOfKits.class);
        setService.create(mappedSet);
        return mappedSet.getId();
    }

    @Override
    public SetOfKitsDTO findSetById(Long setId) {
        return dozerService.mapTo(setService.getSet(setId), SetOfKitsDTO.class);
    }

    @Override
    public void updateSet(SetOfKitsDTO setDTO) {
        SetOfKits mappedSet = dozerService.mapTo(setDTO, SetOfKits.class);
        setService.update(mappedSet);
    }

    @Override
    public void deleteSetById(Long setId) {
        setService.delete(setId);
    }

    @Override
    public void changeDescription(Long setId, String newDescription) {
        setService.getSet(setId).setDescription(newDescription);
    }

    @Override
    public void changePrice(Long setId, BigDecimal newPrice) {
        setService.getSet(setId).setPrice(newPrice);
    }

    @Override
    public List<SetOfKitsDTO> getAllSets() {
        return dozerService.mapTo(setService.getAllSets(), SetOfKitsDTO.class);
    }


    @Override
    public void addKitToSet(Long setId, Long kitId) {
        setService.addKitToSet(setId, kitId);
    }

    @Override
    public void removeKitFromSet(Long setId, Long kitId) {
        setService.removeKitFromSet(setId, kitId);
    }


    public List<CategoryDTO> getSetCategories(long setId) {
        return dozerService.mapTo(setService.getSetCategories(setId), CategoryDTO.class);
    }

}
