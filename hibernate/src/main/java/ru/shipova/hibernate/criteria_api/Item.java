package ru.shipova.hibernate.criteria_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item implements Serializable {
    private static final long serialVersionUID = -151808382655699231L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private BigDecimal price;

    @Override
    public String toString() {
        return String.format("Item [id = %d, title = %s, price = %s]", id, title, price);
    }
}
