package ru.practicum.service;

import dto.StatisticsGetResponseDto;
import dto.StatisticsPostResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatService {
    StatisticsPostResponseDto hit(StatisticsPostResponseDto statisticsPostResponseDto);

    List<StatisticsGetResponseDto> getStats(LocalDateTime start,
                                            LocalDateTime end,
                                            List<String> uris,
                                            boolean unique);
}
