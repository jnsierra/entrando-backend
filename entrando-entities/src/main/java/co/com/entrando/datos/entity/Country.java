package co.com.entrando.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "country")
public class Country implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Id
    @Column(name = "code", nullable = false, updatable = false)
    private Long code;
    @Column(name = "name")
    private String name;
    @Column(name = "diminutive_alpha3")
    private String diminutive;
    @OneToMany(mappedBy = "country")
    private Set<Department> departments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country that = (Country) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}