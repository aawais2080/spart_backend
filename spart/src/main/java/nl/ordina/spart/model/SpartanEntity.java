package nl.ordina.spart.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "spartan")
public class SpartanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    private String name;
    private String email;
    private LocalDate dateOfBirth;

    @ManyToMany(mappedBy = "spartansInEvent")
    private List<SportEventEntity> sportEventEntities;

    @ManyToMany(mappedBy = "spartanEntities")
    private List<SportEntity> sportEntities;

    @OneToMany(mappedBy = "spartanEntity")
    private List<SportEventCommentEntity> sportEventCommentEntities;

    @ManyToOne()
    @JoinColumn(name = "municipality_id", nullable = false)
    private MunicipalityEntity municipalityEntity;
}
