package nl.ordina.spart.service;

import lombok.RequiredArgsConstructor;
import nl.ordina.spart.controller.dto.SportCategoryDto;
import nl.ordina.spart.controller.dto.SportDto;
import nl.ordina.spart.controller.exception.ResourceNotCreatedException;
import nl.ordina.spart.controller.exception.ResourceNotFoundException;
import nl.ordina.spart.controller.mapper.location.LocationMapper;
import nl.ordina.spart.controller.mapper.spartan.SpartanMapper;
import nl.ordina.spart.controller.mapper.sportcategory.SportCategoryMapper;
import nl.ordina.spart.controller.mapper.sport.SportMapper;
import nl.ordina.spart.controller.mapper.sporttype.SportTypeMapper;
import nl.ordina.spart.model.SportEntity;
import nl.ordina.spart.repository.SportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SportService {

    private final SportRepository sportRepository;
    private final SportMapper sportMapper;
    private final SportCategoryMapper sportCategoryMapper;
    private final SportTypeMapper sportTypeMapper;
    private final SpartanMapper spartanMapper;

    public SportDto saveSport(SportDto sport) throws ResourceNotCreatedException {
        SportEntity sportToSave = sportMapper.dtoToEntity(sport);
        return sportMapper.entityToDto(
                Optional.of(sportRepository.save(sportToSave))
                        .orElseThrow(() -> new ResourceNotCreatedException(SportEntity.class.getSimpleName())));

    }

    public List<SportDto> getAllSports() throws ResourceNotFoundException {
        List<SportDto> sportDtos = sportMapper.listToDtoList(sportRepository.findAll());
        if (sportDtos.isEmpty()) {
            throw new ResourceNotFoundException(SportEntity.class.getSimpleName());
        }
        return sportDtos;
    }

    public SportDto getSport(UUID id) throws ResourceNotFoundException {
        return sportMapper.entityToDto(sportRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(SportEntity.class.getSimpleName(), id)
        ));
    }

    public SportDto updateSport(UUID id, SportDto sport) throws ResourceNotFoundException, ResourceNotCreatedException {
        SportEntity sportToUpdate = sportRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(SportEntity.class.getSimpleName(), id)
        );
        SportCategoryDto sportCategoryDto = sportCategoryMapper.entityToDto(sportToUpdate.getSportCategoryEntity());
        sportToUpdate.setName(sport.getName());
        sportToUpdate.setDescription(sport.getDescription());
        sportToUpdate.setSportCategoryEntity(sportCategoryMapper.dtoToEntity(sportCategoryDto));
        sportToUpdate.setSportTypeEntities(sportTypeMapper.dtoListToEntityList(sport.getSportTypes()));
        sportToUpdate.setSpartanEntities(spartanMapper.dtoListToEntityList(sport.getSpartans()));
        return sportMapper.entityToDto(
                Optional.of(sportRepository.save(sportToUpdate))
                        .orElseThrow(() -> new ResourceNotCreatedException(SportEntity.class.getSimpleName())));
    }

    public void deleteSport(UUID id) throws ResourceNotFoundException {
        if (!sportRepository.existsById(id)) {
            throw new ResourceNotFoundException(SportEntity.class.getSimpleName(), id);
        }
        sportRepository.deleteById(id);
    }
}
