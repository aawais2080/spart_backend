package nl.ordina.spart.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SportEventCommentDto {
    private UUID id;
    private String comment;
    private String dateTime;
    private SportEventDto sportEvent;
    private SpartanDto spartan;
}
