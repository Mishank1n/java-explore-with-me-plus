package ru.practicum.service;

import ru.practicum.dto.StatisticsGetResponseDto;
import ru.practicum.dto.StatisticsPostResponseDto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface StatService {
    StatisticsPostResponseDto hit(StatisticsPostResponseDto statisticsPostResponseDto);

    List<StatisticsGetResponseDto> getStats(LocalDateTime start,
                                            LocalDateTime end,
                                            List<String> uris,
                                            boolean unique);
}
