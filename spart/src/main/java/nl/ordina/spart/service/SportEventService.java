package nl.ordina.spart.service;

import lombok.RequiredArgsConstructor;
import nl.ordina.spart.controller.dto.SportEventDto;
import nl.ordina.spart.controller.exception.ResourceNotCreatedException;
import nl.ordina.spart.controller.exception.ResourceNotFoundException;
import nl.ordina.spart.controller.mapper.location.LocationMapper;
import nl.ordina.spart.controller.mapper.spartan.SpartanMapper;
import nl.ordina.spart.controller.mapper.sportevent.SportEventMapper;
import nl.ordina.spart.controller.mapper.sporteventcomment.SportEventCommentMapper;
import nl.ordina.spart.controller.mapper.sporttype.SportTypeMapper;
import nl.ordina.spart.repository.SportEventRepository;
import nl.ordina.spart.model.SportEventEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SportEventService {

    private final SportEventRepository sportEventRepository;
    private final SportEventMapper sportEventMapper;
    private final SpartanMapper spartanMapper;
    private final SportTypeMapper sportTypeMapper;
    private final LocationMapper locationMapper;
    private final SportEventCommentMapper sportEventCommentMapper;

    public SportEventDto saveSportEvent(SportEventDto sportEvent) throws ResourceNotCreatedException {
        SportEventEntity sportEventToSave = sportEventMapper.dtoToEntity(sportEvent);
        return sportEventMapper.entityToDto(
                Optional.of(sportEventRepository.save(sportEventToSave))
                        .orElseThrow(() -> new ResourceNotCreatedException(SportEventEntity.class.getSimpleName())));
    }

    public List<SportEventDto> getAllSportEvents() throws ResourceNotFoundException {
        List<SportEventDto> sportEventDtos = sportEventMapper.entityListToDtoList(sportEventRepository.findAll());
        if (sportEventDtos.isEmpty()) {
            throw new ResourceNotFoundException(SportEventEntity.class.getSimpleName());
        }
        return sportEventDtos;
    }

    public SportEventDto getSportEvent(UUID id) throws ResourceNotFoundException {
        return sportEventMapper.entityToDto(sportEventRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(SportEventEntity.class.getSimpleName(), id)
        ));
    }

    public SportEventDto updateSportEvent(UUID id, SportEventDto sportEvent) throws ResourceNotFoundException, ResourceNotCreatedException {
        SportEventEntity sportEventToUpdate = sportEventRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(SportEventEntity.class.getSimpleName(), id));
        sportEventToUpdate.setName(sportEvent.getName());
        sportEventToUpdate.setDescription(sportEvent.getDescription());
        sportEventToUpdate.setPrice(sportEvent.getPrice());
        sportEventToUpdate.setSportTypeEntity(sportTypeMapper.dtoToEntity(sportEvent.getSportType()));
        sportEventToUpdate.setDateTime(OffsetDateTime.parse(sportEvent.getDateTime()));
        sportEventToUpdate.setLevel(sportEvent.getLevel());
        sportEventToUpdate.setSpartansInEvent(spartanMapper.dtoListToEntityList(sportEvent.getSpartans()));
        sportEventToUpdate.setLocationEntity(locationMapper.dtoToEntity(sportEvent.getLocation()));
        sportEventToUpdate.setMaxParticipants(sportEvent.getMaxParticipants());
        sportEventToUpdate.setSportEventCommentEntities(sportEventCommentMapper.dtoListToEntityList(sportEvent.getSportEventComments()));
        return sportEventMapper.entityToDto(
                Optional.of(sportEventRepository.save(sportEventToUpdate))
                        .orElseThrow(() -> new ResourceNotCreatedException(SportEventEntity.class.getSimpleName())));
    }

    public void deleteSportEvent(UUID id) throws ResourceNotFoundException {
        if (!sportEventRepository.existsById(id)) {
            throw new ResourceNotFoundException(SportEventEntity.class.getSimpleName(), id);
        }
        sportEventRepository.deleteById(id);
    }
}
