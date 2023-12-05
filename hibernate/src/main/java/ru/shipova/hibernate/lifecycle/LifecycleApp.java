package ru.shipova.hibernate.lifecycle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.shipova.hibernate.PrepareDataApp;

import java.util.Scanner;

public class LifecycleApp {
    public static void main(String[] args) {
        //нужен, чтобы можно было приостановить работы программы
        Scanner scanner = new Scanner(System.in);
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/lifecycle/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            //бин в состоянии transient
            AliveBean aliveBean = new AliveBean("Bean Jack");

            session = factory.getCurrentSession();
            session.beginTransaction();
            //проверяем, находится ли объект в контексте постоянства
            System.out.println("contains: " + session.contains(aliveBean));

            //persist подвязывает объект в контекст постоянства
            session.persist(aliveBean);
            System.out.println("[persist] contains: " + session.contains(aliveBean) + "\nCheck database table and press any button...");
            scanner.next();

            //save вместо persist вернёт новое состояние объекта, потому база присвоит ему свой id
            /*
            session.save(aliveBean); // return true
            System.out.println("[save] contains: " + session.contains(aliveBean) + "\nCheck database table and press any button...");
            scanner.next();
            */

            //выталкивает все изменения в базу, они там применяются, но ещё не коммитятся
            //session.flush();

            System.out.println(aliveBean);
            session.remove(aliveBean);

            session.getTransaction().commit();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
