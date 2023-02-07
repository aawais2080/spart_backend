package nl.ordina.spart.service;

import lombok.RequiredArgsConstructor;
import nl.ordina.spart.controller.dto.SportDto;
import nl.ordina.spart.controller.dto.SportTypeDto;
import nl.ordina.spart.controller.exception.ResourceNotCreatedException;
import nl.ordina.spart.controller.exception.ResourceNotFoundException;
import nl.ordina.spart.controller.mapper.spartan.SpartanMapper;
import nl.ordina.spart.controller.mapper.sportevent.SportEventMapper;
import nl.ordina.spart.controller.mapper.sport.SportMapper;
import nl.ordina.spart.controller.mapper.sporttype.SportTypeMapper;
import nl.ordina.spart.model.SportTypeEntity;
import nl.ordina.spart.repository.SportTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SportTypeService {

    private final SportTypeRepository sportTypeRepository;
    private final SportTypeMapper sportTypeMapper;
    private final SportEventMapper sportEventMapper;
    private final SportMapper sportMapper;
    private final SpartanMapper spartanMapper;

    public SportTypeDto saveSportType(SportTypeDto createSportTypeDto) throws ResourceNotCreatedException {
        SportTypeEntity sportTypeToSave = sportTypeMapper.dtoToEntity(createSportTypeDto);
        return sportTypeMapper.entityToDto(
                Optional.of(sportTypeRepository.save(sportTypeToSave))
                        .orElseThrow(() -> new ResourceNotCreatedException(SportTypeEntity.class.getSimpleName())));
    }

    public List<SportTypeDto> getAllSportTypes() throws ResourceNotFoundException {
        List<SportTypeDto> sportDtos = sportTypeMapper.listToDtoList(sportTypeRepository.findAll());
        if (sportDtos.isEmpty()) {
            throw new ResourceNotFoundException(SportTypeEntity.class.getSimpleName());
        }
        return sportDtos;
    }

    public SportTypeDto getSportType(UUID id) throws ResourceNotFoundException {
        return sportTypeMapper.entityToDto(sportTypeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(SportTypeEntity.class.getSimpleName(), id)
        ));
    }

    public SportTypeDto updateSportType(UUID id, SportTypeDto sportType) throws ResourceNotFoundException, ResourceNotCreatedException {
        SportTypeEntity sportTypeToUpdate = sportTypeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(SportTypeEntity.class.getSimpleName(), id));
        SportDto sportDto = sportMapper.entityToDto(sportTypeToUpdate.getSportEntity());
        sportTypeToUpdate.setName(sportType.getName());
        sportTypeToUpdate.setDescription(sportType.getDescription());
        sportTypeToUpdate.setSportEntity(sportMapper.dtoToEntity(sportDto));
        sportTypeToUpdate.setSportEventEntities(sportEventMapper.dtoListToEntityList(sportType.getSportEvents()));
        return sportTypeMapper.entityToDto(
                Optional.of(sportTypeRepository.save(sportTypeToUpdate))
                        .orElseThrow(() -> new ResourceNotCreatedException(SportTypeEntity.class.getSimpleName())));
    }

    public void deleteSportType(UUID id) throws ResourceNotFoundException {
        if (!sportTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException(SportTypeEntity.class.getSimpleName(), id);
        }

        sportTypeRepository.deleteById(id);
    }
}
