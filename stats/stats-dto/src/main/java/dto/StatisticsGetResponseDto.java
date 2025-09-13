package dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import model.Statistics;

@Data
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
