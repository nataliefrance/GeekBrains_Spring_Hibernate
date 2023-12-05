package ru.shipova.hibernate.composed_key;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
//Комбинированный ключ
@Embeddable
public class SerialNumber implements Serializable {
    private static final long serialVersionUID = -3163035995355860580L;

    //указываем все столбы, которые входят в primary key
    @Column(name = "pserial")
    private Integer serial;

    @Column(name = "pnumber")
    private Integer number;

    public SerialNumber(Integer serial, Integer number) {
        this.serial = serial;
        this.number = number;
    }

    //обязательно для комбинированного ключа прописывать equals и hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerialNumber that = (SerialNumber) o;
        return Objects.equals(serial, that.serial) &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serial, number);
    }
}
