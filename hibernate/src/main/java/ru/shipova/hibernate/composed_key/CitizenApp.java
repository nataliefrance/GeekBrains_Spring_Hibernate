package ru.shipova.hibernate.composed_key;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.shipova.hibernate.PrepareDataApp;

public class CitizenApp {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/composed_key/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Citizen citizen = session.get(Citizen.class, 1L);
            System.out.println(citizen.getPassport());
            session.getTransaction().commit();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
