package ru.shipova.lesson_3.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class PrepareData {

     static void forcePrepareData() {
        SessionFactory sessionFactory = new Configuration()
                .configure("lesson_3/hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("hibernate/lesson3_homework.sql")).collect(Collectors.joining(" "));
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
          sessionFactory.close();
          if (session != null) {
              session.close();
          }
        }
    }

    public static void main(String[] args) {
        forcePrepareData();
    }

}
