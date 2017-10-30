package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.Category;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Stepan Granat
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }

        if (em.contains(category)) {
            throw new InvalidDataAccessApiUsageException("Such kit already exists");
        }

        if (category.getName() == null) {
            throw new InvalidDataAccessApiUsageException("Name cannot be null");
        }

        if (category.getDescription() == null) {
            throw new InvalidDataAccessApiUsageException("Description cannot be null");
        }

        em.persist(category);
    }

    @Override
    public Category update(Category category) {
        if (category == null) {
            throw new InvalidDataAccessApiUsageException("Argument cannot be null");
        }
        return em.merge(category);
    }

    @Override
    public void delete(Category category) {
        if (category == null) {
            throw new InvalidDataAccessApiUsageException("Argument cannot be null");
        }

        if (!em.contains(category)){
            throw new InvalidDataAccessApiUsageException("Such kit does not exist");
        }
        em.remove(category);
    }

    @Override
    public Category findById(Long id) {
        if (id == null) {
            throw new InvalidDataAccessApiUsageException("Argument cannot be null");
        }

        if (em.find(Category.class, id) == null) {
            throw new InvalidDataAccessApiUsageException("Kit with such ID does not exist");
        }
        return em.find(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class)
                .getResultList();
    }
}
