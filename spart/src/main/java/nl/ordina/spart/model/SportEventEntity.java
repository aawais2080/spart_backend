package nl.ordina.spart.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sport_event")
public class SportEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;
    private OffsetDateTime dateTime;
    private double price;
    private int level;
    private int maxParticipants;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "spartan_in_event",
            joinColumns = @JoinColumn(name = "sport_event_id"),
            inverseJoinColumns = @JoinColumn(name = "spartan_id")
    )
    private List<SpartanEntity> spartansInEvent;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_type_id", nullable = false)
    private SportTypeEntity sportTypeEntity;

    @OneToMany(mappedBy = "sportEventEntity")
    private List<SportEventCommentEntity> sportEventCommentEntities;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity locationEntity;
}
