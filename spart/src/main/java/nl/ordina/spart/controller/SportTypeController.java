package nl.ordina.spart.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import nl.ordina.spart.controller.dto.SportTypeDto;
import nl.ordina.spart.service.SportTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/sport-types")
public class SportTypeController {

    private final SportTypeService sportTypeService;

    @SneakyThrows
    @PostMapping
    public SportTypeDto addSportType(@RequestBody SportTypeDto sportType) {
        return sportTypeService.saveSportType(sportType);
    }

    @SneakyThrows
    @GetMapping
    public List<SportTypeDto> getAllSportTypes() {
        return sportTypeService.getAllSportTypes();
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public SportTypeDto getSportType(@PathVariable("id") UUID id) {
        return sportTypeService.getSportType(id);
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public SportTypeDto updateSportType(@PathVariable("id") UUID id, @RequestBody SportTypeDto sportType) {
        return sportTypeService.updateSportType(id, sportType);
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public void deleteSportType(@PathVariable("id") UUID id) {
        sportTypeService.deleteSportType(id);
    }
}
