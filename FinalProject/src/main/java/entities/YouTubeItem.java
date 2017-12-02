package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeItem {
    private YouTubeItemSnippet snippet = new YouTubeItemSnippet();
    private YouTubeStatistics statistics = new YouTubeStatistics();
    private String id;

    public YouTubeItemSnippet getSnippet() {
        return snippet;
    }

    public void setSnippet(YouTubeItemSnippet snippet) {
        this.snippet = snippet;
    }

    public YouTubeStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(YouTubeStatistics statistics) {
        this.statistics = statistics;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
