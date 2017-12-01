package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThumbnailImage {
    public String url;
    public int width;
    public int height;
}
