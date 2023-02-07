package nl.ordina.spart.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "location")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String address;
    private Long latitude;
    private Long longitude;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "municipality_id", nullable = false)
    private MunicipalityEntity municipalityEntity;

    @OneToMany(mappedBy = "locationEntity")
    private List<SportEventEntity> sportEventEntities;

    @OneToOne(mappedBy = "locationEntity")
    private SportClubEntity sportClubEntity;

}
