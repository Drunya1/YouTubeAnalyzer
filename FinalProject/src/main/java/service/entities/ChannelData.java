package service.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelData {
    private String title;
    private String id;
    private String date;
    private String subscribers;
    private String videos;
    private String views;
    private String comments;

    public ChannelData(String title, String id, String date, String subscribers, String videos, String views, String comments) {
        this.title = title;
        this.id = id;
        this.date = date;
        this.subscribers = subscribers;
        this.videos = videos;
        this.views = views;
        this.comments = comments;
    }
}
