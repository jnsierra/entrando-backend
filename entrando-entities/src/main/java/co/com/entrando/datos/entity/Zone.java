package co.com.entrando.datos.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@NamedQuery(name = "Zone.findByCategory", query = "from Zone zo inner join fetch zo.category ca where ca.id = :categoryId  ")
@Entity
@Table(name = "zone")
public class Zone implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Id
    @GeneratedValue(generator = "sequence-generator-zone")
    @GenericGenerator(
            name = "sequence-generator-zone",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "zone_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "description", nullable = false, unique = true)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "zone", fetch = FetchType.LAZY)
    private Set<ZoneConfigEvent> zoneConfigEvents = new HashSet<>();
    @OneToMany(mappedBy = "ticketPk.zone", fetch = FetchType.LAZY)
    private Set<Ticket> tickets = new HashSet<>();
    public void addZoneConfigEvents(ZoneConfigEvent zoneConfigEvent) {
        this.zoneConfigEvents.add(zoneConfigEvent);
        zoneConfigEvent.setZone(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zone that = (Zone) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
