package co.com.entrando.datos.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "presentation")
public class Presentation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "presentation_id_gen")
    @SequenceGenerator(name = "presentation_id_gen", sequenceName = "presentation_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @OneToMany(mappedBy = "presentation")
    private Set<ConfigEvent> configEvents = new LinkedHashSet<>();

    @OneToMany(mappedBy = "presentation")
    private Set<MusicBand> musicBands = new LinkedHashSet<>();

    @OneToMany(mappedBy = "presentation")
    private Set<Ticket> tickets = new LinkedHashSet<>();

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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Set<ConfigEvent> getConfigEvents() {
        return configEvents;
    }

    public void setConfigEvents(Set<ConfigEvent> configEvents) {
        this.configEvents = configEvents;
    }

    public Set<MusicBand> getMusicBands() {
        return musicBands;
    }

    public void setMusicBands(Set<MusicBand> musicBands) {
        this.musicBands = musicBands;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

}