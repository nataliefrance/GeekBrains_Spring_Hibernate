package ru.shipova.hibernate.one_to_one;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee implements Serializable {
    private static final long serialVersionUID = -2402680795113876167L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "details_id")//"details_id" - столбец в таблице employees
    private EmployeeDetails details;//будет хранить не число, а объект, на который он ссылается

    @Override
    public String toString() {
        return String.format("Employee [id = %d, name = %s]", id, name);
    }
}
