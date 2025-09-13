package dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import model.Statistics;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsGetResponseDto {
    String app;
    String uri;
    Long hits;

    public static StatisticsGetResponseDto toStatisticsGetResponseDto(Statistics statistics, Long hits) {
        return new StatisticsGetResponseDto(statistics.getApp(), statistics.getUri(), hits);
    }
}
