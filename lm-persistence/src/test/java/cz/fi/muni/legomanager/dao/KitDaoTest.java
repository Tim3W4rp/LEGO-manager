package cz.fi.muni.legomanager.dao;

import cz.fi.muni.legomanager.PersistenceSampleApplicationContext;
import cz.fi.muni.legomanager.entity.Kit;
import org.hibernate.Session;
import org.junit.Assert;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Test of {@link KitDao} methods.
 *
 * @author Stepan Granat
 */
@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class KitDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private KitDao kitDao;

    @PersistenceContext
    private EntityManager em;

    private Kit kit;

    @BeforeMethod
    public void setUp() {
        kit = new Kit();
        kit.setPrice(10);
        kit.setDescription("Test");
        kit.setAgeLimit(12);

        kitDao.create(kit);
    }

    @Test
    public void create() {
        Session session = (Session) em.getDelegate();
        Kit kit = (Kit) session.createQuery("FROM Kit").list().get(0);
        Assert.assertEquals("Test", kit.getDescription());
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void createWithNull() {
        kitDao.create(null);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void createDuplicate() {
        kitDao.create(kit);
    }

    @Test
    public void update() {
        Session session = (Session) em.getDelegate();
        Kit kit = (Kit) session.createQuery("FROM Kit").list().get(0);

        kit.setDescription("Change");

        kitDao.update(kit);
        Kit b_db = (Kit) session.createQuery("FROM Kit").list().get(0);

        Assert.assertEquals("Change", b_db.getDescription());
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void updateWithNull() {
        kitDao.update(null);
    }

    @Test
    public void delete() {
        kitDao.delete(kit);

        Session session = (Session) em.getDelegate();
        int tableSize = session.createQuery("FROM Kit").list().size();

        Assert.assertEquals(0, tableSize);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void deleteWithNull() {
        kitDao.delete(null);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void deleteNotExists() {
        Kit k = new Kit();
        kitDao.delete(k);
    }

    @Test
    public void findById() {
        Kit kit = kitDao.findById(this.kit.getIdKit());
        Assert.assertEquals("Test", kit.getDescription());
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void findByIdNull() {
        kitDao.findById(null);
    }

}
