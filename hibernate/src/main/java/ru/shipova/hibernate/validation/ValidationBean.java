package ru.shipova.hibernate.validation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "validation_beans")
public class ValidationBean implements Serializable {
    private static final long serialVersionUID = 5648118653879627076L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "priority")
    //Значение может находиться от 1 до 10, если нет - будет эксепшн
    @Range(min = 1, max = 10)
    private int priority;

    //не должно быть null
    @NotNull
    //размер должен быть 6 символов (количество элементов в строке)
    @Size(min = 6, max = 6)
    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "created_at")
    @CreationTimestamp
    //время создания объекта
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    //время изменения объекта
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return String.format("ValidationBean [id = %d, email = %s, priority = %d, created_at = %s, updated_at = %s]", id, email, priority, createdAt.toString(), updatedAt.toString());
    }
}
