package ru.shipova.hibernate.lesson_3.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            //запрашиваем у Factory сессию для работы с базой
            session = factory.getCurrentSession();

            Customer customer = new Customer();
            customer.setName("Angelina Jolie");
            session.beginTransaction();
            session.save(customer);

            //подтверждаем транзакцию
            session.getTransaction().commit();

            //отвязываем java бъект от контекста постоянства hibernate
            //session.detach(book);
            //привязываем обратно
            //session.refresh(book);

        } finally {
            factory.close();
            session.close();
        }
    }
}
