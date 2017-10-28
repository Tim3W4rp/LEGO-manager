package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        em.persist(category);
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
