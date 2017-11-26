package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.Brick;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * BrickDaoImpl implements {@link BrickDao}.
 *
 * @author Lukáš Dvořák
 */
@Repository
public class BrickDaoImpl implements BrickDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Brick brick) {
        if (brick == null) {
            throw new InvalidDataAccessApiUsageException("Argument cannot be null.");
        }

        if (em.contains(brick)) {
            throw new InvalidDataAccessApiUsageException("Such brick already exists.");
        }

        em.persist(brick);
    }

    @Override
    public void update(Brick brick) {
        if (brick == null) {
            throw new InvalidDataAccessApiUsageException("Argument cannot be null.");
        }

        if (!em.contains(brick)) {
            throw new InvalidDataAccessApiUsageException("Such brick does not exist.");
        }

        em.merge(brick);
        em.flush();
    }

    @Override
    public void delete(Brick brick) {
        if (brick == null) {
            throw new InvalidDataAccessApiUsageException("Argument cannot be null.");
        }

        if (!em.contains(brick)) {
            throw new InvalidDataAccessApiUsageException("Such brick does not exist.");
        }

        em.remove(brick);
    }

    @Override
    public Brick findById(Long id) {
        if (id == null) {
            throw new InvalidDataAccessApiUsageException("Argument cannot be null.");
        }

        if (em.find(Brick.class, id) == null) {
            throw new InvalidDataAccessApiUsageException("Brick with such ID does not exist.");
        }

        return em.find(Brick.class, id);
    }

    @Override
    public List<Brick> findAll() {
        return em.createQuery("select b from Brick b", Brick.class)
                .getResultList();
    }

}
