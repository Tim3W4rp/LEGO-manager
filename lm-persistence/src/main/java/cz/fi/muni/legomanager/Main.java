package cz.fi.muni.legomanager;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String args[]) {
        new AnnotationConfigApplicationContext(PersistenceSampleApplicationContext.class);
    }
}
