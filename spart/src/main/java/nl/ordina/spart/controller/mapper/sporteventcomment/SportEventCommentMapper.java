package nl.ordina.spart.controller.mapper.sporteventcomment;

import nl.ordina.spart.controller.dto.SportEventCommentDto;
import nl.ordina.spart.model.SportEventCommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SportEventCommentMapper {
    default OffsetDateTime map(String value) {
        LocalDateTime localDateTime = LocalDateTime.parse(value);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Europe/Amsterdam"));
        return zonedDateTime.toOffsetDateTime();
    }

    String map(OffsetDateTime value);

    @Mapping(source = "sportEventEntity", target = "sportEvent")
    @Mapping(target = "sportEvent.sportEventComments", ignore = true)
    @Mapping(source = "spartanEntity", target = "spartan")
    @Mapping(target = "spartan.comments", ignore = true)
    SportEventCommentDto entityToDto(SportEventCommentEntity sportEventCommentEntity);

    SportEventCommentEntity dtoToEntity(SportEventCommentDto sportEventCommentDto);

    List<SportEventCommentEntity> dtoListToEntityList(List<SportEventCommentDto> sportEventComments);
}
