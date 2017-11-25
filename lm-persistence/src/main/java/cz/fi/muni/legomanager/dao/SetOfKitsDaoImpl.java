package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.entity.SetOfKits;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Michal Peška
 */
@Repository
public class SetOfKitsDaoImpl implements SetOfKitsDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(SetOfKits set) {
        if (set == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }

        if (em.contains(set)) {
            throw new IllegalArgumentException("Such set already exists.");
        }

        em.persist(set);
    }

    @Override
    public SetOfKits findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }

        if (em.find(SetOfKits.class, id) == null) {
            throw new IllegalArgumentException("Set with such ID does not exist.");
        }

        return em.find(SetOfKits.class, id);
    }

    @Override
    public void update(SetOfKits set) {
        if (set == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }

        if (!em.contains(set)) {
            throw new IllegalArgumentException("Such set does not exist.");
        }

        em.merge(set);
        em.flush();
    }

    @Override
    public List<SetOfKits> findAll() {
        return em.createQuery("select s from SetOfKits s", SetOfKits.class)
                .getResultList();
    }

    @Override
    public void delete(SetOfKits set) {
        if (set == null) {
            throw new IllegalArgumentException("Argument cannot be null.");
        }

        if (!em.contains(set)) {
            throw new IllegalArgumentException("Such set does not exist.");
        }

        em.remove(set);
    }

}
