/**
 * @author Martin Jordán
 */

package cz.fi.muni.legomanager.Dao;

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
    public void update(Kit kit) {
        Kit updatedKit = findById(kit.getId());
        updatedKit.setDescription(kit.getDescription());
        updatedKit.setPrice(kit.getPrice());
        updatedKit.setAgeLimit(kit.getAgeLimit());
        em.persist(updatedKit);
    }

    @Override
    public void delete(Kit kit) {
        em.remove(kit);
    }

}
