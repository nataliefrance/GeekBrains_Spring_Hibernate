package ru.shipova.hibernate.one_to_many_and_back;

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
@Table(name = "universities")
public class University implements Serializable {
    private static final long serialVersionUID = -289123754859778673L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "university")
    private List<Student> students;

    @Override
    public String toString() {
        return String.format("University [id = %d, title = %s, students_count = %d]", id, title, students.size());
    }
}
