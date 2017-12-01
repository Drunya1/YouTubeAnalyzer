package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeItem {
    public YouTubeItemSnippet snippet = new YouTubeItemSnippet();
}
