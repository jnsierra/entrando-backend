package co.com.entrando.datos.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department")
@NamedQuery(name="Department.findByCountry", query = "FROM Department dep inner join dep.country as country WHERE country.code = :idCountry ")
public class Department implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Id
    @Column(name = "code", nullable = false, updatable = false)
    private Long code;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_code")
    private Country country;
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<City> cities = new HashSet<>();

    public Department() {
    }

    public Department(Long code, String name, Country country, Set<City> cities) {
        this.code = code;
        this.name = name;
        this.country = country;
        this.cities = cities;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

}
