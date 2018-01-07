package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.KitBrick;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Martin Jordán
 */
@Repository
public class KitBrickDaoImpl implements KitBrickDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public KitBrick create(KitBrick kitBrick) {
        if (kitBrick == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        if (em.contains(kitBrick)) {
            throw new IllegalArgumentException("Such kitBrick already exists");
        }

        if (kitBrick.getKit() == null || kitBrick.getBrick() == null) {
            throw new IllegalArgumentException("Kit and Brick must be set");
        }

        em.persist(kitBrick);
        return kitBrick;
    }

    @Override
    public KitBrick findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        if (em.find(KitBrick.class, id) == null) {
            throw new IllegalArgumentException("KitBrick with such ID does not exist");
        }

        return null;
    }

    @Override
    public void update(KitBrick kitBrick) {
        if (kitBrick == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        em.merge(kitBrick);
        em.flush();
    }

    @Override
    public void delete(KitBrick kitBrick) {
        if (kitBrick == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        if (!em.contains(kitBrick)) {
            throw new IllegalArgumentException("Such kitBrick does not exist");
        }

        em.remove(kitBrick);
    }

    @Override
    public List<KitBrick> findAll() {
        return em.createQuery("select k from KitBrick k", KitBrick.class)
                .getResultList();
    }
}
