package nl.ordina.spart.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SportCategoryDto {
    private UUID id;
    private String name;
    private List<SportDto> sports;
}
