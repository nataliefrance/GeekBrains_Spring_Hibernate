package ru.shipova.hibernate.locking;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "locking_items")
//Блокировка
public class LockingItem implements Serializable {
    private static final long serialVersionUID = -1049528515179193867L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private int value;

    @Version
    //Для версии существует только геттер
    private Long version;

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LockingItem(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("LockingItem [id = %d, value = %d, version = %d]", id, value, version);
    }
}
