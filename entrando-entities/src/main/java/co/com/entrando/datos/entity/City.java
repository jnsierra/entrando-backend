package co.com.entrando.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "city")
@NamedQuery(name="City.findByDepartment", query = "from City city inner join fetch city.department as dep where dep.code = ?1 ")
public class City implements Serializable {
    @Serial
    private static final long serialVersionUID = 1234567L;
    @EmbeddedId
    private CityPk cityPk;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_code", insertable = false, updatable = false)
    private Department department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City that = (City) o;

        return Objects.equals(cityPk, that.cityPk);
    }

    @Override
    public int hashCode() {
        return cityPk != null ? cityPk.hashCode() : 0;
    }
}