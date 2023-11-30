package ru.shipova.hibernate.lesson_3.homework;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "cost")
    Long cost;

    @ManyToMany
    @JoinTable (
            name = "customers_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    List<Customer> customers;
}
