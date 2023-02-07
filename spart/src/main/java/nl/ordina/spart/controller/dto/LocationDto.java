package nl.ordina.spart.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class LocationDto {
    private UUID id;
    private String name;
    private String address;
    private Long latitude;
    private Long longitude;
    private String description;
    private MunicipalityDto municipality;
    private List<SportEventDto> sportEvents;
    private SportClubDto sportClub;
}
