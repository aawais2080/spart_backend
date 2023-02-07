package nl.ordina.spart.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "municipality")
public class MunicipalityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "municipalityEntity")
    private List<SpartanEntity> spartanEntities;

    @OneToMany(mappedBy = "municipalityEntity")
    private List<LocationEntity> locationEntities;

}
