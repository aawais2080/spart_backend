package nl.ordina.spart.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SpartanDto {
    private UUID id;
    private String username;
    private String name;
    private String email;
    private String dateOfBirth;
    private List<SportEventDto> sportEvents;
    private List<SportDto> sports;
    private List<SportEventCommentDto> comments;
    private MunicipalityDto municipality;
}
