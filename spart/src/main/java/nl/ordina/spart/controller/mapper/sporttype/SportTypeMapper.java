package nl.ordina.spart.controller.mapper.sporttype;

import nl.ordina.spart.controller.dto.SportEventDto;
import nl.ordina.spart.controller.dto.SportTypeDto;
import nl.ordina.spart.model.SportEventEntity;
import nl.ordina.spart.model.SportTypeEntity;
import org.mapstruct.*;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SportTypeMapper {
    String map(OffsetDateTime value);

    @Mapping(source = "sportEventEntities", target = "sportEvents", qualifiedBy = SportEventWithoutSportTypes.class)
    SportTypeDto entityToDto(SportTypeEntity sportType);

    SportTypeEntity dtoToEntity(SportTypeDto sportTypeDto);

    List<SportTypeDto> listToDtoList(List<SportTypeEntity> sportTypes);

    List<SportTypeEntity> dtoListToEntityList(List<SportTypeDto> sportDtos);

    @SportEventWithoutSportTypes
    @IterableMapping(qualifiedByName = "sportEventWithoutSportTypes")
    List<SportEventDto> sportEventEntitiesToListWithoutSportTypes(List<SportEventEntity> sportEventEntities);

    @Mapping(target = "sportType", ignore = true)
    @Named("sportEventWithoutSportTypes")
    SportEventDto sportEventToSportEventDtoWithoutSportTypes(SportEventEntity sportEventEntity);
}
