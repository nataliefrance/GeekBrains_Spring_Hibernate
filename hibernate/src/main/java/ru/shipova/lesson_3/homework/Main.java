package ru.shipova.lesson_3.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PrepareData.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("lesson_3/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            //запрашиваем у Factory сессию для работы с базой
            session = factory.getCurrentSession();
            session.beginTransaction();

            Customer customer = new Customer();
            customer.setName("Angelina Jolie");
            Product product = new Product("milk", 89L);
            session.beginTransaction();
            session.save(customer);
            session.save(product);

            List<Customer> customers = session.createNamedQuery("Customer.findAll", Customer.class).getResultList();
            Customer customer1 = session
                    .createNamedQuery("Customer.findById", Customer.class)
                    .setParameter("id", 1L)
                    .getSingleResult();

            System.out.println(customers);
            System.out.println(customer1);

            //отвязываем java бъект от контекста постоянства hibernate
            //session.detach(book);
            //привязываем обратно
            //session.refresh(book);

            //подтверждаем транзакцию
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
    }
}
