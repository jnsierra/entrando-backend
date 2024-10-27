package co.com.entrando.datos.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "city")
public class City {
    @EmbeddedId
    private CityId id;

    @MapsId("departmentCode")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_code", nullable = false)
    private Department departmentCode;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "city")
    private Set<Event> events = new LinkedHashSet<>();

    public CityId getId() {
        return id;
    }

    public void setId(CityId id) {
        this.id = id;
    }

    public Department getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Department departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

}