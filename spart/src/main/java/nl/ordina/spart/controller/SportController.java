package nl.ordina.spart.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import nl.ordina.spart.controller.dto.SportDto;
import nl.ordina.spart.service.SportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sports")
public class SportController {

    private final SportService sportService;

    @SneakyThrows
    @GetMapping
    public List<SportDto> getAllSports() {
        return sportService.getAllSports();
    }

    @SneakyThrows
    @PostMapping
    public SportDto addSport(@RequestBody SportDto sport) {
        return sportService.saveSport(sport);
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public SportDto getSport(@PathVariable("id") UUID id) {
        return sportService.getSport(id);
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public SportDto updateSport(@PathVariable("id") UUID id, @RequestBody SportDto sport) {
        return sportService.updateSport(id, sport);
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public void deleteSport(@PathVariable("id") UUID id) {
        sportService.deleteSport(id);
    }
}
