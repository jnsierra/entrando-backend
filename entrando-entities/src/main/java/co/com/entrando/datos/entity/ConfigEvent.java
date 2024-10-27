package co.com.entrando.datos.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "config_event")
public class ConfigEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "config_event_id_gen")
    @SequenceGenerator(name = "config_event_id_gen", sequenceName = "config_event_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "presentation_id", nullable = false)
    private Presentation presentation;

    @Column(name = "door_opening", nullable = false)
    private LocalTime doorOpening;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "number_of_tickets", nullable = false)
    private BigDecimal numberOfTickets;

    @ColumnDefault("0")
    @Column(name = "number_of_tickets_sold", nullable = false)
    private BigDecimal numberOfTicketsSold;

    @OneToMany(mappedBy = "configEvent")
    private Set<ZoneConfigEvent> zoneConfigEvents = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public LocalTime getDoorOpening() {
        return doorOpening;
    }

    public void setDoorOpening(LocalTime doorOpening) {
        this.doorOpening = doorOpening;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public BigDecimal getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(BigDecimal numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public BigDecimal getNumberOfTicketsSold() {
        return numberOfTicketsSold;
    }

    public void setNumberOfTicketsSold(BigDecimal numberOfTicketsSold) {
        this.numberOfTicketsSold = numberOfTicketsSold;
    }

    public Set<ZoneConfigEvent> getZoneConfigEvents() {
        return zoneConfigEvents;
    }

    public void setZoneConfigEvents(Set<ZoneConfigEvent> zoneConfigEvents) {
        this.zoneConfigEvents = zoneConfigEvents;
    }

}