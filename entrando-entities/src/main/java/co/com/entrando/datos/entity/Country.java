package co.com.entrando.datos.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_gen")
    @SequenceGenerator(name = "country_id_gen", sequenceName = "country_seq", allocationSize = 1)
    @Column(name = "code", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "diminutive_alpha3", nullable = false, length = 3)
    private String diminutiveAlpha3;

    @OneToMany(mappedBy = "countryCode")
    private Set<Department> departments = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiminutiveAlpha3() {
        return diminutiveAlpha3;
    }

    public void setDiminutiveAlpha3(String diminutiveAlpha3) {
        this.diminutiveAlpha3 = diminutiveAlpha3;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

}