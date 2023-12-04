package ru.shipova.hibernate.criteria_api;

import ru.shipova.hibernate.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CriteriaApiMain {
    public static void main(String[] args) {
        PrepareDataApp.forcePrepareData();

        SessionFactory factory = new Configuration()
                .configure("configs/criteria_api/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        CriteriaBuilder criteriaBuilder = null;
        CriteriaQuery<Item> criteriaQuery = null;
        Root<Item> root = null;
        Query<Item> query = null;
        List<Item> results = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            // CriteriaBuilder - построитель выражений, частей запросов, выражений, предикатов и т.д.
            criteriaBuilder = session.getCriteriaBuilder(); // запрос CriteriaBuilder у сессии
            //Мы хотим сформировать запрос и в результате него получить Item.class
            criteriaQuery = criteriaBuilder.createQuery(Item.class); // построитель запроса, <T> - тип возвращаемых объектов
            root = criteriaQuery.from(Item.class); // Ссылка на сущность, указываем с какой сущностью мы работаем
            criteriaQuery.select(root);
            query = session.createQuery(criteriaQuery);
            results = query.getResultList();
            session.getTransaction().commit();
            System.out.println("All results:\n" + results);
            System.out.println("=====================================================================");


            session = factory.getCurrentSession();
            session.beginTransaction();
            criteriaBuilder = session.getCriteriaBuilder();
            criteriaQuery = criteriaBuilder.createQuery(Item.class); //говорим, что на выходе мы получим Item.class
            root = criteriaQuery.from(Item.class); //и говорим, что мыработаем с Item.class

            BigDecimal minPrice = new BigDecimal(50.0);
            String startsWith = "c";

            //список специальных условий, по которым мы будем выбирать эти item
            List<Predicate> predicates = new ArrayList<>();
            if (minPrice != null) {
                //поле item.class должено быть больше чем minPrice
                predicates.add(criteriaBuilder.greaterThan(root.get("price"), minPrice));
            }
            if (startsWith != null) {
                //делаем предикат, что название должно начинаться со startwith
                predicates.add(criteriaBuilder.like(root.get("title"), startsWith + "%"));
            }
            //теперь хотим сделать выборку item с вот таким списком предикатов
            criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
            query = session.createQuery(criteriaQuery);
            results = query.getResultList();
            session.getTransaction().commit();
            System.out.println("Filtered results:\n" + results);
            System.out.println("=====================================================================");


            session = factory.getCurrentSession();
            session.beginTransaction();
            criteriaBuilder = session.getCriteriaBuilder();
            criteriaQuery = criteriaBuilder.createQuery(Item.class);
            root = criteriaQuery.from(Item.class);

            Predicate priceGreaterThan = criteriaBuilder.greaterThan(root.get("price"), new BigDecimal(200.0));
            Predicate titleStartsWithB = criteriaBuilder.like(root.get("title"), "b%");
            criteriaQuery.select(root).where(
                    criteriaBuilder.or(priceGreaterThan, titleStartsWithB)
            ).orderBy(criteriaBuilder.desc(root.get("price")));

            query = session.createQuery(criteriaQuery);
            results = query.getResultList();
            session.getTransaction().commit();
            System.out.println("Filtered results v2:\n" + results);
            System.out.println("=====================================================================");


            session = factory.getCurrentSession();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            //в качестве результата будет itemDto.class
            CriteriaQuery<ItemDto> criteria = builder.createQuery(ItemDto.class);
            //в качестве входных данных будет item.class
            root = criteria.from(Item.class);

            //у item запрашиваем ссылку на поле title
            Path<String> titlePath = root.get("title");
            Path<BigDecimal> pricePath = root.get("price");

            //говорим, каким образом мы сформируем iteomDto (в itemDto есть соответствующий конструктор)
            criteria.select(builder.construct(ItemDto.class, titlePath, pricePath));
            //делаем выборку item у кого стоимость больше 80
            criteria.where(builder.greaterThan(root.get("price"), new BigDecimal(80.0)));

            List<ItemDto> dtos = session.createQuery(criteria).getResultList();
            session.getTransaction().commit();
            System.out.println("Dtos: \n" + dtos);
            System.out.println("=====================================================================");
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}