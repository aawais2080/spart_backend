package nl.ordina.spart.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import nl.ordina.spart.controller.dto.SportCategoryDto;
import nl.ordina.spart.controller.exception.ResourceNotFoundException;
import nl.ordina.spart.controller.mapper.sportcategory.SportCategoryMapper;
import nl.ordina.spart.controller.mapper.sport.SportMapper;
import nl.ordina.spart.model.SportCategoryEntity;
import nl.ordina.spart.repository.SportCategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SportCategoryService {

    private final SportCategoryMapper sportCategoryMapper;
    private final SportCategoryRepository sportCategoryRepository;
    private final SportMapper sportMapper;

    public List<SportCategoryDto> getAllSportCategories() throws ResourceNotFoundException {
        List<SportCategoryDto> sportCategories =
                sportCategoryMapper.entityListToDtoList(sportCategoryRepository.findAll());
        if (sportCategories.isEmpty()) {
            throw new ResourceNotFoundException(SportCategoryEntity.class.getSimpleName());
        }
        return sportCategories;
    }

    public SportCategoryDto getSportCategory(UUID id) throws ResourceNotFoundException {
        return sportCategoryMapper.entityToDto(sportCategoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(SportCategoryEntity.class.getSimpleName(), id)
        ));
    }

    public SportCategoryDto saveSportCategory(SportCategoryDto sportCategory) throws ResourceNotFoundException {
        SportCategoryEntity sportCategoryToSave =
                sportCategoryMapper.dtoToEntity(sportCategory);

        return sportCategoryMapper.entityToDto(
                Optional.of(sportCategoryRepository.save(sportCategoryToSave))
                        .orElseThrow(() -> new ResourceNotFoundException(SportCategoryEntity.class.getSimpleName())));
    }

    public SportCategoryDto updateSportCategory(UUID id, SportCategoryDto sportCategory) throws ResourceNotFoundException {
        SportCategoryEntity sportCategoryToUpdate = sportCategoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(SportCategoryEntity.class.getSimpleName(), id)
        );
        sportCategoryToUpdate.setName(sportCategory.getName());
        sportCategoryToUpdate.setSportEntities(sportMapper.dtoListToEntityList(sportCategory.getSports()));
        return sportCategoryMapper.entityToDto(
                Optional.of(sportCategoryRepository.save(sportCategoryToUpdate))
                        .orElseThrow(() -> new ResourceNotFoundException(SportCategoryEntity.class.getSimpleName())));
    }

    public void deleteSportCategory(UUID id) throws ResourceNotFoundException {
        if (!sportCategoryRepository.existsById(id)) {
            throw new ResourceNotFoundException(SportCategoryEntity.class.getSimpleName(), id);
        }
        sportCategoryRepository.deleteById(id);
    }
}
