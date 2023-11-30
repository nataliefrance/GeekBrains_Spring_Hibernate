package ru.shipova.hibernate.lesson_3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainClass {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        //CRUD
        Session session = null;
        //try {
            //запрашиваем у Factory сессию для работы с базой
            session = sessionFactory.getCurrentSession();

            //подтверждаем транзакцию
            session.getTransaction().commit();

            //отвязываем java бъект от контекста постоянства hibernate
            //session.detach(book);
            //привязываем обратно
            //session.refresh(book);

        //}
    }
}
