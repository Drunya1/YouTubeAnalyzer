package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeItemThumbnails {
    public ThumbnailImage medium;
    public ThumbnailImage high;
}
