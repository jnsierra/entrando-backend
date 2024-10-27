package co.com.entrando.datos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class CityId implements java.io.Serializable {
    private static final long serialVersionUID = 5537666284707468977L;
    @Column(name = "code", nullable = false)
    private Integer code;

    @Column(name = "department_code", nullable = false)
    private Integer departmentCode;

    public CityId(Integer code, Integer departmentCode) {
        this.code = code;
        this.departmentCode = departmentCode;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Integer departmentCode) {
        this.departmentCode = departmentCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CityId entity = (CityId) o;
        return Objects.equals(this.code, entity.code) &&
                Objects.equals(this.departmentCode, entity.departmentCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, departmentCode);
    }

}