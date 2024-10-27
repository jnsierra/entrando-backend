package co.com.entrando.datos.entity;

import co.com.entrando.enumeration.EventStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Event.findByIdAndPresentation", query = """ 
                                                           from Event event
                                                          inner join fetch event.presentation as pr 
                                                          inner join fetch event.configEvents as conEv
                                                          inner join fetch event.city as cit
                                                          inner join fetch cit.department as dep
                                                          inner join fetch dep.country as coun
                                                          inner join fetch event.categoryEvent as catEve
                                                          where  event.id = :eventId 
                                                            and pr.id = :presentationId
                                                            and conEv.event.id = event.id
                                                            and conEv.presentation.id = pr.id
                                                                """)
@NamedQuery(name = "Event.findByEventStatusAndAfterTodayEvent", query = """
                                                           FROM Event event
                                                          WHERE event.eventStatus = :eventStatus
                                                            AND event.date >= current_date
        """)
@NamedQuery(name = "Event.updateEventStatus", query = """
        Update Event eve
           set eventStatus = :eventStatus
         where eve.id = :id
        """)
@Getter @Setter
@Entity
@Table(name = "event")
public class Event extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Id
    @GeneratedValue(generator = "sequence-generator-event")
    @GenericGenerator(
            name = "sequence-generator-event",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "event_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "name", nullable = false, updatable = false)
    private String name;
    @Column(name="place")
    private String place;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "time")
    private String time;
    @Column(name = "minimum_age")
    private int minimumAge;
    @Column(name = "responsible")
    private String responsible;
    @Column(name = "nit")
    private String nit;
    @Column(name = "address")
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_code", referencedColumnName = "code")
    @JoinColumn(name = "department_code", referencedColumnName = "department_code")
    private City city;
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private Set<Presentation> presentation;
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private Set<ConfigEvent> configEvents;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 8)
    private EventStatus eventStatus;
    @OneToMany(mappedBy = "ticketPk.event", fetch = FetchType.LAZY)
    private Set<Ticket> tickets;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEvent categoryEvent;
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
    private Set<EventImages> eventImagesEntities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event that = (Event) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}