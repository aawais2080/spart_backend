package nl.ordina.spart.controller;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import nl.ordina.spart.controller.dto.SportCategoryDto;
import nl.ordina.spart.service.SportCategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sport-categories")
public class SportCategoryController {

    private final SportCategoryService sportCategoryService;

    @SneakyThrows
    @GetMapping
    public List<SportCategoryDto> getAllSportCategories() {
        return sportCategoryService.getAllSportCategories();
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public SportCategoryDto getSportCategory(@PathVariable UUID id) {
        return sportCategoryService.getSportCategory(id);
    }

    @SneakyThrows
    @PostMapping
    public SportCategoryDto addSportCategory(@RequestBody SportCategoryDto createSportCategoryDto) {
        return sportCategoryService.saveSportCategory(createSportCategoryDto);
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public SportCategoryDto updateSportCategory(@PathVariable UUID id, @RequestBody SportCategoryDto sportCategory) {
        return sportCategoryService.updateSportCategory(id, sportCategory);
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public void deleteSportCategory(@PathVariable UUID id) {
        sportCategoryService.deleteSportCategory(id);
    }
}
