package nl.ordina.spart.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SportTypeDto {
    private UUID id;
    private String name;
    private String description;
    private List<SportEventDto> sportEvents;
}
