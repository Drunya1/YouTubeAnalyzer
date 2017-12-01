package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeItemSnippet {
    public String title;
    public String publishedAt;
    public YouTubeItemThumbnails thumbnails = new YouTubeItemThumbnails();
}
