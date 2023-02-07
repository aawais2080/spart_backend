package nl.ordina.spart.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SportEventDto {
    private UUID id;
    private String name;
    private String description;
    private String dateTime;
    private double price;
    private int level;
    private int maxParticipants;
    private List<SpartanDto> spartans;
    private SportTypeDto sportType;
    private List<SportEventCommentDto> sportEventComments;
    private LocationDto location;
}
