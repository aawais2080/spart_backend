package nl.ordina.spart.controller.mapper.spartan;

import nl.ordina.spart.controller.dto.SpartanDto;
import nl.ordina.spart.controller.dto.SportDto;
import nl.ordina.spart.controller.dto.SportEventCommentDto;
import nl.ordina.spart.controller.dto.SportEventDto;
import nl.ordina.spart.model.SpartanEntity;
import nl.ordina.spart.model.SportEntity;
import nl.ordina.spart.model.SportEventCommentEntity;
import nl.ordina.spart.model.SportEventEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SpartanMapper {
    String map(OffsetDateTime value);

    @Mapping(source = "municipalityEntity", target = "municipality")
    @Mapping(target = "municipality.spartans", ignore = true)
    @Mapping(source = "sportEventEntities", target = "sportEvents", qualifiedBy = SportEventWithoutSpartans.class)
    @Mapping(source = "sportEventCommentEntities", target = "comments", qualifiedBy = CommentWithoutSpartan.class)
    @Mapping(source = "sportEntities", target = "sports", qualifiedBy = SportWithoutSpartans.class)
    SpartanDto entityToDto(SpartanEntity spartanEntity);

    SpartanEntity dtoToEntity(SpartanDto spartan);

    List<SpartanEntity> dtoListToEntityList(List<SpartanDto> spartans);

    @SportEventWithoutSpartans
    @IterableMapping(qualifiedByName = "sportEventWithoutSpartans")
    List<SportEventDto> sportEventEntitiesToListWithoutSpartans(List<SportEventEntity> sportEventEntities);

    @CommentWithoutSpartan
    @IterableMapping(qualifiedByName = "sportEventCommentWithoutSpartan")
    List<SportEventCommentDto> commentEntitiesToListWithoutSpartan(List<SportEventCommentEntity> commentEntities);

    @SportWithoutSpartans
    @IterableMapping(qualifiedByName = "sportWithoutSpartans")
    List<SportDto> sportEntitiesToListWithoutSpartans(List<SportEntity> sportEntities);

    @Mapping(target = "spartan", ignore = true)
    @Named("sportEventCommentWithoutSpartan")
    SportEventCommentDto sportEventCommentToSportEventCommentDtoWithoutSpartan(SportEventCommentEntity sportEventCommentEntity);

    @Mapping(target = "spartans", ignore = true)
    @Named("sportEventWithoutSpartans")
    SportEventDto sportEventToSportEventDtoWithoutSpartan(SportEventEntity sportEvent);

    @Mapping(target = "spartans", ignore = true)
    @Named("sportWithoutSpartans")
    SportDto sportToSportDtoWithoutSpartans(SportEntity sportEntity);
}
