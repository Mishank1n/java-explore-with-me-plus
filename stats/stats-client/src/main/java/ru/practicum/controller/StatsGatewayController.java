package ru.practicum.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.client.StatsClient;
import ru.practicum.client.dto.ClientRequestDto;

import java.time.LocalDateTime;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping
public class StatsGatewayController {
    private final StatsClient client;

    @PostMapping("/hit")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> hit(@Valid @RequestBody ClientRequestDto body) {
        return client.setHit(body);
    }


    @GetMapping("/stats")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> stats(
            @RequestParam @NotNull LocalDateTime start,
            @RequestParam @NotNull LocalDateTime end,
            @RequestParam(required = false) List<String> uris,
            @RequestParam(defaultValue = "false") Boolean unique) {
        return client.getStats(start, end, uris, unique);
    }
}
