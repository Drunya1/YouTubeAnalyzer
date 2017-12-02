package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeItemSnippet {
    private String title;
    private String publishedAt;
    private YouTubeItemThumbnails thumbnails = new YouTubeItemThumbnails();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public YouTubeItemThumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(YouTubeItemThumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }
}
