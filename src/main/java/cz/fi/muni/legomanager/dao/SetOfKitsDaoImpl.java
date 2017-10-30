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
        em.persist(set);
    }

    @Override
    public SetOfKits findById(Long id) {
        return em.find(SetOfKits.class, id);
    }

    @Override
    public void update(SetOfKits set) {
        em.merge(set);
    }

    @Override
    public List<SetOfKits> findAll() {
        return em.createQuery("select s from SetOfKits s", SetOfKits.class)
                .getResultList();
    }

    @Override
    public void delete(SetOfKits set) {
        em.remove(set);
    }

}
