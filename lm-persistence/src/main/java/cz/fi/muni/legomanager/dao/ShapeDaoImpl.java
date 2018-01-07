package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.Shape;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        if (shape == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }

        if (em.contains(shape)) {
            throw new IllegalArgumentException("Such shape already exists.");
        }

        em.persist(shape);
    }

    @Override
    public void update(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }

        em.merge(shape);
        em.flush();
    }

    @Override
    public void delete(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }

        if (!em.contains(shape)) {
            throw new IllegalArgumentException("Such shape does not exist.");
        }

        em.remove(shape);
    }

    @Override
    public Shape findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }

        if (em.find(Shape.class, id) == null) {
            throw new IllegalArgumentException("Shape with such ID does not exist.");
        }

        return em.find(Shape.class, id);
    }

    @Override
    public Shape findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }

        return em.createQuery("SELECT s FROM Shape s WHERE s.name = :shapeName", Shape.class)
                .setParameter("shapeName", name).getSingleResult();

    }

    @Override
    public List<Shape> findAll() {
        return em.createQuery("select s from Shape s", Shape.class)
                .getResultList();
    }

}
