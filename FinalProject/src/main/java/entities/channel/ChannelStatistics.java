package entities.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelStatistics {
    private String viewCount;
    private String commentCount;
    private String subscriberCount;
    private String videoCount;
}
