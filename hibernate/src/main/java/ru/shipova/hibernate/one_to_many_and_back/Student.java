package ru.shipova.hibernate.one_to_many_and_back;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "students")
public class Student implements Serializable {
    private static final long serialVersionUID = -8181750949221177838L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "university_id") //Внешний ключ, по которому идёт привязка к University
    private University university;

    @Override
    public String toString() {
        return String.format("Student [id = %d, name = %s]", id, name);
    }
}
