package nl.ordina.spart.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sport_type")
public class SportTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_id", nullable = false)
    private SportEntity sportEntity;

    @OneToMany(mappedBy = "sportTypeEntity")
    private List<SportEventEntity> sportEventEntities;
}
