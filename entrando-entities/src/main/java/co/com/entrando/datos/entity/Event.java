package co.com.entrando.datos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
@Getter @Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_id_gen")
    @SequenceGenerator(name = "event_id_gen", sequenceName = "event_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "minimum_age", nullable = false)
    private Integer minimumAge;

    @Column(name = "responsible", nullable = false)
    private String responsible;

    @Column(name = "nit", nullable = false)
    private String nit;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_code", nullable = false)
    private City city;

    @Column(name = "status", nullable = false, length = 15)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private EventCategory category;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Column(name = "last_modified_by", nullable = false)
    private String lastModifiedBy;

    @Column(name = "last_modified_date", nullable = false)
    private Instant lastModifiedDate;

    @OneToMany(mappedBy = "event")
    private Set<ConfigEvent> configEvents = new LinkedHashSet<>();

    @OneToMany(mappedBy = "event")
    private Set<EventImage> eventImages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "event")
    private Set<Presentation> presentations = new LinkedHashSet<>();

    @OneToMany(mappedBy = "event")
    private Set<Ticket> tickets = new LinkedHashSet<>();

}