package ru.shipova.hibernate.composed_key;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "citizens")
public class Citizen implements Serializable {
    private static final long serialVersionUID = -503212700335355521L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    //одному человеку - один паспорт
    @OneToOne
    //это всё нужно, чтобы добраться до паспорта
    @JoinColumns({
            @JoinColumn(
                    name = "passport_serial",//в нашем классе, в таблице "citizens" есть столбец "passport_serial",
                    // он ссылается на столбец в таблице "passports", который называется "pserial"
                    referencedColumnName = "pserial"),
            @JoinColumn(
                    name = "passport_number",//первая ссылка на таблицу в "citizens"
                    referencedColumnName = "pnumber")//вторая ссылка на таблицу в "passports"
    })
    private Passport passport;

    @Override
    public String toString() {
        return String.format("Citizen [id = %d, name = %s]", id, name);
    }
}
