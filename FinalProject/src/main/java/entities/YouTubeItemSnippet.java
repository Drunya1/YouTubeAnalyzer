package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeItemSnippet {
    public String channelId;
    public String title;
    public String channelTitle;
    public String publishedAt;
    public YouTubeActivityItemThumbnails thumbnails = new YouTubeActivityItemThumbnails();
}
