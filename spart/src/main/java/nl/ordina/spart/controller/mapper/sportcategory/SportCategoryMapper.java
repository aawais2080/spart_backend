package nl.ordina.spart.controller.mapper.sportcategory;

import nl.ordina.spart.controller.dto.SportCategoryDto;
import nl.ordina.spart.controller.dto.SportDto;
import nl.ordina.spart.model.SportCategoryEntity;
import nl.ordina.spart.model.SportEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SportCategoryMapper {

    @Mapping(source = "sportEntities", target = "sports", qualifiedBy = SportWithoutSportCategories.class)
    SportCategoryDto entityToDto(SportCategoryEntity sportCategoryEntity);

    SportCategoryEntity dtoToEntity(SportCategoryDto sportCategory);

    List<SportCategoryDto> entityListToDtoList(List<SportCategoryEntity> sportCategoryEntity);

    List<SportCategoryEntity> dtoListToEntityList(List<SportCategoryDto> sportCategoryDtoList);

    @SportWithoutSportCategories
    @IterableMapping(qualifiedByName = "sportWithoutSportCategories")
    List<SportDto> sportEntitiesToListWithoutSportCategories(List<SportEntity> sportEntities);

    @Mapping(target = "sportCategory", ignore = true)
    @Named("sportWithoutSportCategories")
    SportDto sportToSportCategoryDtoWithoutSportCategories(SportEntity sportEntity);
}
