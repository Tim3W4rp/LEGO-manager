package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.Kit;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Martin Jordán
 */
@Repository
public class KitDaoImpl implements KitDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Kit kit) {
        if (kit == null) {
            throw new InvalidDataAccessApiUsageException("Argument cannot be null");
        }

        if (em.contains(kit)) {
            throw new InvalidDataAccessApiUsageException("Such kit already exists");
        }

        if (kit.getAgeLimit() == null) {
            throw new InvalidDataAccessApiUsageException("Age limit cannot be null");
        }

        if (kit.getDescription() == null) {
            throw new InvalidDataAccessApiUsageException("Description cannot be null");
        }

        if (kit.getPrice() == null) {
            throw new InvalidDataAccessApiUsageException("Price cannot be null");
        }

        em.persist(kit);
    }

    @Override
    public Kit findById(Long id) {
        if (id == null) {
            throw new InvalidDataAccessApiUsageException("Argument cannot be null");
        }

        if (em.find(Kit.class, id) == null) {
            throw new InvalidDataAccessApiUsageException("Kit with such ID does not exist");
        }

        return em.find(Kit.class, id);
    }

    @Override
    public void update(Kit kit) {
        if (kit == null) {
            throw new InvalidDataAccessApiUsageException("Argument cannot be null");
        }
        Kit updatedKit = findById(kit.getId());
        updatedKit.setDescription(kit.getDescription());
        updatedKit.setPrice(kit.getPrice());
        updatedKit.setAgeLimit(kit.getAgeLimit());
        em.persist(updatedKit);
    }

    @Override
    public void delete(Kit kit) {
        if (kit == null) {
            throw new InvalidDataAccessApiUsageException("Argument cannot be null");
        }

        if (!em.contains(kit)){
            throw new InvalidDataAccessApiUsageException("Such kit does not exist");
        }

        em.remove(kit);
    }

    @Override
    public List<Kit> findAll() {
        return em.createQuery("select k from Kit k", Kit.class)
                .getResultList();
    }
}
