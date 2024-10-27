package co.com.entrando.datos.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "user_type")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserType implements Serializable {
    @Id
    @GeneratedValue(generator = "sequence-generator-user-type")
    @GenericGenerator(
            name = "sequence-generator-user-type",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_type_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "description", nullable = false)
    private String description;
    @ManyToMany(mappedBy = "userTypes", fetch = FetchType.LAZY)
    private Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserType that = (UserType) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
