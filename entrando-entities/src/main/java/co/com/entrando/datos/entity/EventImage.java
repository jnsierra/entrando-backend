package co.com.entrando.datos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "event_images")
public class EventImage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_images_id_gen")
    @SequenceGenerator(name = "event_images_id_gen", sequenceName = "event_images_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "type_image", nullable = false, length = 50)
    private String typeImage;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(String typeImage) {
        this.typeImage = typeImage;
    }

}