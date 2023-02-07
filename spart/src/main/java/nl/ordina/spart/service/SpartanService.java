package nl.ordina.spart.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nl.ordina.spart.controller.exception.ResourceNotCreatedException;
import nl.ordina.spart.controller.exception.ResourceNotFoundException;
import nl.ordina.spart.repository.SpartanRepository;
import nl.ordina.spart.controller.dto.SpartanDto;
import nl.ordina.spart.controller.mapper.spartan.SpartanMapper;
import nl.ordina.spart.model.SpartanEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpartanService {

    private final SpartanRepository spartanRepository;
    private final SpartanMapper spartanMapper;

    public SpartanDto saveSpartan(SpartanDto spartan) throws ResourceNotCreatedException {
        SpartanEntity spartanToSave = spartanMapper.dtoToEntity(spartan);
        return spartanMapper.entityToDto(
                Optional.of(spartanRepository.save(spartanToSave))
                        .orElseThrow(() -> new ResourceNotCreatedException(SpartanEntity.class.getSimpleName())));
    }

    @Transactional
    public List<SpartanDto> getAllSpartans() throws ResourceNotFoundException {
        List<SpartanDto> spartanDtos = spartanRepository.findAll().stream().map(spartanMapper::entityToDto).toList();
        if (spartanDtos.isEmpty()) {
            throw new ResourceNotFoundException(SpartanEntity.class.getSimpleName());
        }
        return spartanDtos;
    }

    public SpartanDto getSpartan(UUID id) throws ResourceNotFoundException {
        return spartanMapper.entityToDto(spartanRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(SpartanEntity.class.getSimpleName(), id)
        ));
    }

    public SpartanDto updateSpartan(UUID id, SpartanDto spartan) throws ResourceNotFoundException, ResourceNotCreatedException {
        SpartanEntity spartanToUpdate = spartanRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(SpartanEntity.class.getSimpleName(), id)
        );
        spartanToUpdate.setName(spartan.getName());
        spartanToUpdate.setUsername(spartan.getUsername());
        spartanToUpdate.setDateOfBirth(LocalDate.parse(spartan.getDateOfBirth()));
        spartanToUpdate.setEmail(spartan.getEmail());
        spartanToUpdate.setMunicipalityEntity(spartanMapper.dtoToEntity(spartan).getMunicipalityEntity());
        spartanToUpdate.setSportEventCommentEntities(spartanMapper.dtoToEntity(spartan).getSportEventCommentEntities());
        spartanToUpdate.setSportEntities(spartanMapper.dtoToEntity(spartan).getSportEntities());
        spartanToUpdate.setSportEventEntities(spartanMapper.dtoToEntity(spartan).getSportEventEntities());
        return spartanMapper.entityToDto(
                    Optional.of(spartanRepository.save(spartanToUpdate))
                            .orElseThrow(() -> new ResourceNotCreatedException(SpartanEntity.class.getSimpleName())));
    }

    public void deleteSpartan(UUID id) throws ResourceNotFoundException {
        if (!spartanRepository.existsById(id)) {
            throw new ResourceNotFoundException(SpartanEntity.class.getSimpleName(), id);
        }
        spartanRepository.deleteById(id);
    }
}

