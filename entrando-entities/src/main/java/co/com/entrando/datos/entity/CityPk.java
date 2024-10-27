package co.com.entrando.datos.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CityPk implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Column(name = "code")
    private Long code;
    @Column(name = "department_code")
    private Long departmentCode;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityPk that = (CityPk) o;
        return Objects.equals(code, that.code) && Objects.equals(departmentCode, that.departmentCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, departmentCode);
    }
}
