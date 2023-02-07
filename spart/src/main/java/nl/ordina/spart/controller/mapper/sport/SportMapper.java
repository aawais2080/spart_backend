package nl.ordina.spart.controller.mapper.sport;

import nl.ordina.spart.controller.dto.SpartanDto;
import nl.ordina.spart.controller.dto.SportDto;
import nl.ordina.spart.controller.dto.SportTypeDto;
import nl.ordina.spart.model.SpartanEntity;
import nl.ordina.spart.model.SportEntity;
import nl.ordina.spart.model.SportTypeEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SportMapper {

    @Mapping(source = "sportCategoryEntity", target = "sportCategory")
    @Mapping(target = "sportCategory.sports", ignore = true)
    @Mapping(source = "sportTypeEntities", target = "sportTypes")
    @Mapping(source = "spartanEntities", target = "spartans", qualifiedBy = SpartanWithoutSports.class)
    SportDto entityToDto(SportEntity sportEntity);

    SportEntity dtoToEntity(SportDto sport);

    List<SportDto> listToDtoList(List<SportEntity> sportEntities);

    List<SportEntity> dtoListToEntityList(List<SportDto> sportDtos);

    @SpartanWithoutSports
    @IterableMapping(qualifiedByName = "spartanWithoutSports")
    List<SpartanDto> spartanEntitiesToListWithoutSports(List<SpartanEntity> spartanEntities);

    @Mapping(target = "sports", ignore = true)
    @Named("spartanWithoutSports")
    SpartanDto spartanToSportDtoWithoutSports(SpartanEntity spartanEntity);
}
