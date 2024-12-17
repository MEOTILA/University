package org.example;

import org.example.entity.Lesson;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryInstance {


    public static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Teacher.class)
                .addAnnotatedClass(Lesson.class)
                .buildSessionFactory();

        /*var registry = new StandardServiceRegistryBuilder()
                .configure() // goes and fetches configuration from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry)
                .addPackage("ir.maktab.entity")
                .buildMetadata()
                .buildSessionFactory();*/
    }

}
