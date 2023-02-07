package nl.ordina.spart.controller.mapper.location;

import nl.ordina.spart.controller.dto.LocationDto;
import nl.ordina.spart.controller.dto.SportEventDto;
import nl.ordina.spart.model.LocationEntity;
import nl.ordina.spart.model.SportEventEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    String map(OffsetDateTime value);

    @Mapping(source = "municipalityEntity", target = "municipality")
    @Mapping(target = "municipality.locations", ignore = true)
    @Mapping(source = "sportClubEntity", target = "sportClub")
    @Mapping(target = "sportClub.location", ignore = true)
    @Mapping(source = "sportEventEntities", target = "sportEvents", qualifiedBy = SportEventWithoutLocations.class)
    LocationDto entityToDto(LocationEntity location);

    LocationEntity dtoToEntity(LocationDto locationDto);
    
    List<LocationEntity> dtoListToEntityList(List<LocationDto> locations);

    @SportEventWithoutLocations
    @IterableMapping(qualifiedByName = "sportEventWithoutLocations")
    List<SportEventDto> sportEventEntitiesToListWithoutLocations(List<SportEventEntity> sportEventEntities);

    @Mapping(target = "location", ignore = true)
    @Named("sportEventWithoutLocations")
    SportEventDto sportEventToSportEventDtoWithoutLocations(SportEventEntity sportEventEntity);
}
