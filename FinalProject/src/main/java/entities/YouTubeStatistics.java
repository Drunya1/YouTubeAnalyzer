package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeStatistics {
    public int viewCount;
    public int commentCount;
    public int subscriberCount;
    public int videoCount;
}
