package co.com.entrando.datos.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "zone")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zone_id_gen")
    @SequenceGenerator(name = "zone_id_gen", sequenceName = "zone_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "zone")
    private Set<Ticket> tickets = new LinkedHashSet<>();

    @OneToMany(mappedBy = "zone")
    private Set<ZoneConfigEvent> zoneConfigEvents = new LinkedHashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<ZoneConfigEvent> getZoneConfigEvents() {
        return zoneConfigEvents;
    }

    public void setZoneConfigEvents(Set<ZoneConfigEvent> zoneConfigEvents) {
        this.zoneConfigEvents = zoneConfigEvents;
    }

}