package nl.ordina.spart.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import nl.ordina.spart.controller.dto.SportEventDto;
import nl.ordina.spart.service.SportEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/sport-events")
public class SportEventController {

    private final SportEventService sportEventService;

    @SneakyThrows
    @PostMapping
    public SportEventDto createSportEvent(@RequestBody SportEventDto sportEvent) {
        return sportEventService.saveSportEvent(sportEvent);
    }

    @SneakyThrows
    @GetMapping
    public List<SportEventDto> getAllSportEvents() {
        return sportEventService.getAllSportEvents();
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public SportEventDto getSportEvent(@PathVariable("id") UUID id) {
        return sportEventService.getSportEvent(id);
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public SportEventDto updateSportEvent(@PathVariable("id") UUID id, @RequestBody SportEventDto sportEvent) {
        return sportEventService.updateSportEvent(id, sportEvent);
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public void deleteSportEvent(@PathVariable("id") UUID id) {
        sportEventService.deleteSportEvent(id);
    }


}
