package nl.ordina.spart.repository;

import nl.ordina.spart.model.SportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SportRepository extends JpaRepository<SportEntity, UUID> {
}
