package nl.ordina.spart.repository;

import nl.ordina.spart.model.SportTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SportTypeRepository extends JpaRepository<SportTypeEntity, UUID> {
}
