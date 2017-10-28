package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Stepan Granat
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    public void create(Category category) {
        System.out.println(em);
    }

    @Override
    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    @Override
    public Category update(Category category) {
        return em.merge(category);
    }

    @Override
    public void delete(Category category) {
        em.remove(category);
    }
}
