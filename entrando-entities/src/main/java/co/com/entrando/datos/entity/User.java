package co.com.entrando.datos.entity;

import co.com.entrando.enumeration.USER_STATE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "User.updateAttempts", query = """
        update User usu set usu.attempts = :attempts WHERE usu.id = :id
        """)
@NamedQuery(name = "User.updatePassword", query = """
        UPDATE User usu
           SET usu.password = :password,
               usu.changePassword = 'N'
         WHERE usu.email = :email
        """)
@Entity
@Table(name = "user_tickets")
public class User extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 2405172041950251807L;
    @Id
    @GeneratedValue(generator = "sequence-generator-user")
    @GenericGenerator(
            name = "sequence-generator-user",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_seq"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    @Column(name = "email", updatable = false)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "change_password")
    private String changePassword;
    @Column(name = "attempts")
    private Long attempts;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private USER_STATE state;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_tickets_user_type",
            joinColumns = {@JoinColumn(name = "user_tickets_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_type_id")}

    )
    private Set<UserType> userTypes = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
    public void removeUserType(UserType userType){
        this.userTypes.remove(userType);
        userType.getUsers().remove(this);
    }
    public void addUserType(UserType userType){
        this.userTypes.add(userType);
        userType.getUsers().add(this);
    }
}
