package ru.shipova.hibernate.many_to_many;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book implements Serializable {
    private static final long serialVersionUID = -6183116139588984602L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(
            name = "books_readers", //отдельная таблица в БД
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "reader_id")
    )
    private List<Reader> readers;

    @Override
    public String toString() {
        return String.format("Book [id = %d, title = %s]", id, title);
    }
}
