package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.Kit;
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
    public Kit create(Kit kit) {
        if (kit == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        if (em.contains(kit)) {
            throw new IllegalArgumentException("Such kit already exists");
        }

        if (kit.getAgeLimit() == null) {
            throw new IllegalArgumentException("Age limit cannot be null");
        }

        if (kit.getDescription() == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }

        if (kit.getPrice() == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }

        em.persist(kit);
        return kit;
    }

    @Override
    public Kit findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        if (em.find(Kit.class, id) == null) {
            throw new IllegalArgumentException("Kit with such ID does not exist");
        }

        return em.find(Kit.class, id);
    }

    @Override
    public void update(Kit kit) {
        if (kit == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        em.merge(kit);
        em.flush();
    }

    @Override
    public void delete(Kit kit) {
        if (kit == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        if (!em.contains(kit)) {
            throw new IllegalArgumentException("Such kit does not exist");
        }

        em.remove(kit);
    }

    @Override
    public List<Kit> findAll() {
        return em.createQuery("select k from Kit k", Kit.class)
                .getResultList();
    }
}
