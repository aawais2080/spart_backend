package nl.ordina.spart.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import nl.ordina.spart.controller.dto.SpartanDto;
import nl.ordina.spart.service.SpartanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/spartans")
public class SpartanController {

    private final SpartanService spartanService;

    @SneakyThrows
    @PostMapping
    public SpartanDto addSpartan(@RequestBody SpartanDto spartan) {
        return spartanService.saveSpartan(spartan);
    }

    @SneakyThrows
    @GetMapping
    public List<SpartanDto> getAllSpartans() {
        return spartanService.getAllSpartans();
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public SpartanDto getSpartan(@PathVariable("id") UUID id) {
        return spartanService.getSpartan(id);
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public SpartanDto updateSport(@PathVariable("id") UUID id, @RequestBody SpartanDto spartan) {
        return spartanService.updateSpartan(id, spartan);
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public void deleteSpartan(@PathVariable("id") UUID id) {
        spartanService.deleteSpartan(id);
    }
}
