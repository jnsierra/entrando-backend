package co.com.entrando.datos.entity;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TicketPk implements Serializable {
    private static final long serialVersionUID = 1234568L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", updatable = false)
    private Event event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id", updatable = false)
    private Zone zone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", updatable = false)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presentation_id", updatable = false)
    private Presentation presentation;
    @Column(name = "number_ticket", updatable = false)
    private Long numberTicket;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketPk that = (TicketPk) o;

        if (!event.equals(that.event)) return false;
        if (!zone.equals(that.zone)) return false;
        if (!category.equals(that.category)) return false;
        if (!presentation.equals(that.presentation)) return false;
        return numberTicket.equals(that.numberTicket);
    }

    @Override
    public int hashCode() {
        int result = event.hashCode();
        result = 31 * result + zone.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + presentation.hashCode();
        result = 31 * result + numberTicket.hashCode();
        return result;
    }
}
