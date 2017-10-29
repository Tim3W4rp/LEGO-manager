package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.Brick;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

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
        em.persist(brick);
    }

    @Override
    public void update(Brick brick) {
        em.merge(brick);
    }

    @Override
    public void delete(Brick brick) {
        em.remove(brick);
    }

    @Override
    public Brick findById(Long id) {
        return em.find(Brick.class, id);
    }

    @Override
    public List<Brick> findAll() {
        return em.createQuery("select b from Brick b", Brick.class)
                .getResultList();
    }

}
