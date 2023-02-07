package nl.ordina.spart.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SportDto {
    private UUID id;
    private String name;
    private String description;
    private SportCategoryDto sportCategory;
    private List<SportTypeDto> sportTypes;
    private List<SpartanDto> spartans;
}
