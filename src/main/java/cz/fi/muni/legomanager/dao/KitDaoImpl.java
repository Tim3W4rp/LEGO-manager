package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.Kit;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class KitDaoImpl implements KitDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Kit kit) {
        em.persist(kit);
    }

    @Override
    public Kit findById(Long id) {
        return em.find(Kit.class, id);
    }

    @Override
    public void update(Long id, String description, Integer price, Integer ageLimit) {
        Kit kit = em.find(Kit. class, id);
        kit.setDescription(description);
        kit.setPrice(price);
        kit.setAgeLimit(ageLimit);
        em.persist(kit);
    }

    @Override
    public void delete(Kit kit) {
        em.remove(kit);
    }

}
