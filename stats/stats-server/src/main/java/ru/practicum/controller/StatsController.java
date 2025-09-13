package ru.practicum.controller;

import dto.StatisticsGetResponseDto;
import dto.StatisticsPostResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.service.StatServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatsController {
    private final StatServiceImpl statService;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public StatisticsPostResponseDto hit(@Valid @RequestBody StatisticsPostResponseDto statisticsPostResponseDto) {
        return statService.hit(statisticsPostResponseDto);
    }

    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.OK)
    public List<StatisticsGetResponseDto> getStats(@RequestParam("start") LocalDateTime start,
                                                   @RequestParam("end") LocalDateTime end,
                                                   @RequestParam(value = "uris", required = false) List<String> uris,
                                                   @RequestParam(value = "unique", required = false, defaultValue = "false") boolean unique) {
        return statService.getStats(start, end, uris, unique);
    }
}
