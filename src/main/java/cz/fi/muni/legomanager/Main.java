package cz.fi.muni.legomanager;

import cz.fi.muni.legomanager.entity.Category;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {


    public static void main(String args[]) {
        new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");


        EntityManager emWork = emf.createEntityManager();
        emWork.getTransaction().begin();
        Category c1 = new Category();
        c1.setName("Electronics");
        Category c2=new Category();
        c2.setName("Musical");
        emWork.persist(c1);
        emWork.persist(c2);

        emWork.getTransaction().commit();    }
}
