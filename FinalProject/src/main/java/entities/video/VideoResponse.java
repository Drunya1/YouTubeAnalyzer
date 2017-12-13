package entities.video;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoResponse {
    private ArrayList<Video> items = new ArrayList<>();

    public ArrayList<Video> getItems() {
        return items;
    }

    public void setItems(ArrayList<Video> items) {
        this.items = items;
    }
}
