package nl.ordina.spart.controller.mapper.sportevent;

import nl.ordina.spart.controller.dto.SpartanDto;
import nl.ordina.spart.controller.dto.SportEventCommentDto;
import nl.ordina.spart.controller.dto.SportEventDto;
import nl.ordina.spart.model.SpartanEntity;
import nl.ordina.spart.model.SportEventCommentEntity;
import nl.ordina.spart.model.SportEventEntity;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SportEventMapper {
    String map(OffsetDateTime value);

    default OffsetDateTime map(String value) {
        LocalDateTime localDateTime = LocalDateTime.parse(value);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Europe/Amsterdam"));
        return zonedDateTime.toOffsetDateTime();
    }

    @Mapping(source = "spartansInEvent", target = "spartans", qualifiedBy = SpartansWithoutSportEvents.class)
    @Mapping(source = "sportTypeEntity", target = "sportType")
    @Mapping(target = "sportType.sportEvents", ignore = true)
    @Mapping(source = "sportEventCommentEntities", target = "sportEventComments", qualifiedBy = SportEventCommentsWithoutSportEvents.class)
    @Mapping(source = "locationEntity", target = "location")
    @Mapping(target = "location.sportEvents", ignore = true)
    SportEventDto entityToDto(SportEventEntity sportEvent);

    SportEventEntity dtoToEntity(SportEventDto sportEventDto);

    List<SportEventDto> entityListToDtoList(List<SportEventEntity> sportEvent);

    List<SportEventEntity> dtoListToEntityList(List<SportEventDto> sportEventDtos);

    @SpartansWithoutSportEvents
    @IterableMapping(qualifiedByName = "spartanWithoutSportEvents")
    List<SpartanDto> spartanEntitiesToListWithoutSportEvents(List<SpartanEntity> spartanEntityList);

    @Mapping(target = "sportEvents", ignore = true)
    @Named("spartanWithoutSportEvents")
    SpartanDto spartanToSpartanDtoWithoutSportEvents(SpartanEntity spartanEntity);

    @SportEventCommentsWithoutSportEvents
    @IterableMapping(qualifiedByName = "sportEventCommentWithoutSportEvents")
    List<SportEventCommentDto> sportEventCommentEntitiesToListWithoutSportEvents(List<SportEventCommentEntity> sportEventCommentEntityList);

    @Mapping(target = "sportEvent", ignore = true)
    @Named("sportEventCommentWithoutSportEvents")
    SportEventCommentDto sportEventCommentToSportEventCommentDtoWithoutSportEvents(SportEventCommentEntity sportEventCommentEntity);
}
