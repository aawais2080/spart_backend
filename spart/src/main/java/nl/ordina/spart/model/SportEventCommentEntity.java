package nl.ordina.spart.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sport_event_comment")
public class SportEventCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String comment;
    private OffsetDateTime dateTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sport_event_id", nullable = false)
    private SportEventEntity sportEventEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "spartan_id", nullable = false)
    private SpartanEntity spartanEntity;
}
