package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeResponse {
    private ArrayList<YouTubeItem> items = new ArrayList<YouTubeItem>();

    public ArrayList<YouTubeItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<YouTubeItem> items) {
        this.items = items;
    }
}
