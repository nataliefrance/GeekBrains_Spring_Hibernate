package ru.shipova.hibernate.lifecycle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "alive_beans")
public class AliveBean implements Serializable {
    private static final long serialVersionUID = -2025940487235285735L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public AliveBean(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("AliveBean [id = %d, name = %s]", id, name);
    }
}
