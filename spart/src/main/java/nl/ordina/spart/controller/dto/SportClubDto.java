package nl.ordina.spart.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SportClubDto {
    private UUID id;
    private String name;
    private String primaryColor;
    private String secondaryColor;
    private String phoneNumber;
    private String email;
    private String description;
    private LocationDto location;
}
