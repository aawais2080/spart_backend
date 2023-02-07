package nl.ordina.spart.controller.mapper.sportclub;

import nl.ordina.spart.controller.dto.SportClubDto;
import nl.ordina.spart.model.SportClubEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SportClubMapper {
    @Mapping(source = "locationEntity", target = "location")
    @Mapping(target = "location.sportClub", ignore = true)
    SportClubDto entityToDto(SportClubEntity sportClubEntity);

    SportClubEntity dtoToEntity(SportClubDto sportClubDto);

    List<SportClubEntity> dtoListToEntityList(List<SportClubDto> sportClubs);
}
