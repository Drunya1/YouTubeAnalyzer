package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeItemThumbnails {
    private ThumbnailImage medium;
    private ThumbnailImage high;

    public ThumbnailImage getMedium() {
        return medium;
    }

    public void setMedium(ThumbnailImage medium) {
        this.medium = medium;
    }

    public ThumbnailImage getHigh() {
        return high;
    }

    public void setHigh(ThumbnailImage high) {
        this.high = high;
    }
}
