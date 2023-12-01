package ru.shipova.hibernate.composed_key;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "passports")
public class Passport implements Serializable {
    private static final long serialVersionUID = 3734710958953154546L;

    //говорим о том, что тут у нас id - это отдельный класс
    @EmbeddedId
    private SerialNumber id;

    @Column(name = "registration_address")
    private String registrationAddress;

    @Override
    public String toString() {
        return String.format("Passport [serial = %d, number = %d, registration = %s]", id.getSerial(), id.getNumber(), registrationAddress);
    }
}
