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
@Table(name = "employees_details")
public class EmployeeDetails implements Serializable {
    private static final long serialVersionUID = -3345483994643602633L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    // Если в этом классе убрать поле employee, то получится однонаправленная связь: сотрудник
    // сможет ссылаться на свои детали, а детали нет.
    // В данном же случае прописана двунаправленная связь
    @OneToOne(mappedBy = "details") //Сотрудник ссылается на EmployeeDetails через обратную связь, через своё поле "details"
    private Employee employee;

    @Override
    public String toString() {
        return String.format("EmployeeDetails [id = %d, email = %s, city = %s, employee.name = %s]", id, email, city, employee.getName());
    }
}
