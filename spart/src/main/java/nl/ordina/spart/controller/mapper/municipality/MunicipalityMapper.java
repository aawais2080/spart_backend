package nl.ordina.spart.controller.mapper.municipality;

import nl.ordina.spart.controller.dto.LocationDto;
import nl.ordina.spart.controller.dto.MunicipalityDto;
import nl.ordina.spart.controller.dto.SpartanDto;
import nl.ordina.spart.model.LocationEntity;
import nl.ordina.spart.model.MunicipalityEntity;
import nl.ordina.spart.model.SpartanEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MunicipalityMapper {
    @Mapping(source = "locationEntities", target = "locations", qualifiedBy = LocationWithoutMunicipality.class)
    @Mapping(source = "spartanEntities", target = "spartans", qualifiedBy = SpartansWithoutMunicipality.class)
    MunicipalityDto entityToDto(MunicipalityEntity municipalityEntity);

    MunicipalityEntity dtoToEntity(MunicipalityDto municipalityDto);

    List<MunicipalityEntity> dtoListToEntityList(List<MunicipalityDto> municipalities);

    @LocationWithoutMunicipality
    @IterableMapping(qualifiedByName = "locationWithoutMunicipality")
    List<LocationDto> locationEntitiesToListWithoutMunicipality(List<LocationEntity> locationEntities);

    @SpartansWithoutMunicipality
    @IterableMapping(qualifiedByName = "spartanWithoutMunicipality")
    List<SpartanDto> spartanEntitiesToListWithoutMunicipality(List<SpartanEntity> spartanEntities);

    @Mapping(target = "municipality", ignore = true)
    @Named("locationWithoutMunicipality")
    LocationDto locationToLocationDtoWithoutMunicipality(LocationEntity locationEntity);

    @Mapping(target = "municipality", ignore = true)
    @Named("spartanWithoutMunicipality")
    SpartanDto spartanToSpartanDtoWithoutMunicipality(SpartanEntity spartanEntity);
}
