package entities.video;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {
    @Getter
    @Setter
    private VideoStatistics statistics;
}
