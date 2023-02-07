package nl.ordina.spart.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sport")
public class SportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_category_id", nullable = false)
    private SportCategoryEntity sportCategoryEntity;

    @OneToMany(mappedBy = "sportEntity")
    private List<SportTypeEntity> sportTypeEntities;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "spartan_liked_sport",
            joinColumns = @JoinColumn(name = "sport_id"),
            inverseJoinColumns = @JoinColumn(name = "spartan_id")
    )
    private List<SpartanEntity> spartanEntities;

}
