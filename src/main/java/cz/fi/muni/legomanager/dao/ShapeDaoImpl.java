package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.Shape;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ShapeDaoImpl implements {@link ShapeDao}.
 *
 * @author Lukáš Dvořák
 */
@Repository
public class ShapeDaoImpl implements ShapeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Shape shape) {
        em.persist(shape);
    }

    @Override
    public void update(Shape shape) {
        em.merge(shape);
    }

    @Override
    public void delete(Shape shape) {
        em.remove(shape);
    }

    @Override
    public Shape findById(Long id) {
        return em.find(Shape.class, id);
    }

    @Override
    public List<Shape> findAll() {
        return em.createQuery("select s from Shape s", Shape.class)
                .getResultList();
    }

}
