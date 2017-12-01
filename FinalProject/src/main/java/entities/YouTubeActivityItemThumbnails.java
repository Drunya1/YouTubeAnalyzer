package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entity.ThumbnailImage;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeActivityItemThumbnails {
    public ThumbnailImage medium;
    public ThumbnailImage high;
    public ThumbnailImage standard;
    public ThumbnailImage maxres;
}
