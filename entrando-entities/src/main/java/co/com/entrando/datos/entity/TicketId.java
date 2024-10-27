package co.com.entrando.datos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TicketId implements java.io.Serializable {
    private static final long serialVersionUID = -2785123430616894360L;
    @Column(name = "event_id", nullable = false)
    private Integer eventId;

    @Column(name = "zone_id", nullable = false)
    private Integer zoneId;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "presentation_id", nullable = false)
    private Integer presentationId;

    @Column(name = "number_ticket", nullable = false)
    private Integer numberTicket;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TicketId entity = (TicketId) o;
        return Objects.equals(this.eventId, entity.eventId) &&
                Objects.equals(this.presentationId, entity.presentationId) &&
                Objects.equals(this.zoneId, entity.zoneId) &&
                Objects.equals(this.numberTicket, entity.numberTicket) &&
                Objects.equals(this.categoryId, entity.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, presentationId, zoneId, numberTicket, categoryId);
    }

}