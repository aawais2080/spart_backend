package nl.ordina.spart.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class MunicipalityDto {
    private UUID id;
    private String name;
    private List<SpartanDto> spartans;
    private List<LocationDto> locations;
}
