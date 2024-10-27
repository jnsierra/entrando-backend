package co.com.entrando.datos.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user_type")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_type_id_gen")
    @SequenceGenerator(name = "user_type_id_gen", sequenceName = "user_type_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Column(name = "last_modified_by", nullable = false)
    private String lastModifiedBy;

    @Column(name = "last_modified_date", nullable = false)
    private Instant lastModifiedDate;

    @ManyToMany
    @JoinTable(name = "user_tickets_user_type",
            joinColumns = @JoinColumn(name = "user_type_id"),
            inverseJoinColumns = @JoinColumn(name = "user_tickets_id"))
    private Set<UserTicket> userTickets = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Set<UserTicket> getUserTickets() {
        return userTickets;
    }

    public void setUserTickets(Set<UserTicket> userTickets) {
        this.userTickets = userTickets;
    }

}