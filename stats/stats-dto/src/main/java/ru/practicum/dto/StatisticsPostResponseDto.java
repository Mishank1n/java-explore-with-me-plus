package ru.practicum.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.model.Statistics;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsPostResponseDto {
    String app;
    String uri;
    String ip;
    LocalDateTime timestamp;

    public static StatisticsPostResponseDto toStatisticsPostResponseDto(Statistics statistics) {
        return new StatisticsPostResponseDto(statistics.getApp(), statistics.getUri(), statistics.getIp(), statistics.getTimestamp());
    }
}
