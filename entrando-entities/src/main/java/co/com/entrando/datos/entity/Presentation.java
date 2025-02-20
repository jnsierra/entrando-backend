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
@NamedQuery(name = "Presentation.findByEvent", query = "from Presentation pre inner join fetch pre.event as eve  WHERE eve.id = :idEvent ")
@Getter @Setter
@Entity
@Table(name = "presentation")
public class Presentation implements Serializable {
    private static final long serialVersionUID = 1234567L;
    @Id
    @GeneratedValue(generator = "sequence-generator-presentation")
    @GenericGenerator(
            name = "sequence-generator-presentation",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "presentation_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
    @OneToMany(mappedBy = "presentation", fetch = FetchType.LAZY)
    private Set<MusicBand> musicBand = new HashSet<>();
    @OneToMany(mappedBy = "ticketPk.presentation", fetch = FetchType.LAZY)
    private Set<Ticket> tickets = new HashSet<>();
    @OneToMany(mappedBy = "presentation", fetch = FetchType.LAZY)
    private Set<ConfigEvent> configEvents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Presentation entity = (Presentation) o;

        return id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}