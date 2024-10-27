package co.com.entrando.datos.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "zone_config_event")
public class ZoneConfigEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zone_config_event_id_gen")
    @SequenceGenerator(name = "zone_config_event_id_gen", sequenceName = "zone_config_event_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "config_event_id", nullable = false)
    private ConfigEvent configEvent;

    @Column(name = "number_of_tickets", nullable = false)
    private Integer numberOfTickets;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @ColumnDefault("0")
    @Column(name = "number_of_tickets_sold")
    private Integer numberOfTicketsSold;

    @ColumnDefault("false")
    @Column(name = "create_tickets")
    private Boolean createTickets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public ConfigEvent getConfigEvent() {
        return configEvent;
    }

    public void setConfigEvent(ConfigEvent configEvent) {
        this.configEvent = configEvent;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getNumberOfTicketsSold() {
        return numberOfTicketsSold;
    }

    public void setNumberOfTicketsSold(Integer numberOfTicketsSold) {
        this.numberOfTicketsSold = numberOfTicketsSold;
    }

    public Boolean getCreateTickets() {
        return createTickets;
    }

    public void setCreateTickets(Boolean createTickets) {
        this.createTickets = createTickets;
    }

}