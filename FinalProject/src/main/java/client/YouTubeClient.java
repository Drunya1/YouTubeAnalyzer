package client;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import entities.channel.ChannelResponse;
import entities.playlist.PlaylistResponse;
import entities.playlistItems.PlaylistItemsResponse;
import entities.video.VideoResponse;
import properties.YouTubeProperties;
import settings.YouTubeCache;
import util.Settings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class YouTubeClient {
    private static Properties properties = YouTubeProperties.loadPPropertiesFromFile("src/main/resources/youTube.properties");
    private static final String CACHE_PATH = "src/main/resources/cache/cache.json";

    private YouTubeClient(){}

    static {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static ChannelResponse getChannelInfo(String idChannel) {
        try {
            YouTubeCache cache = null;
            Scanner scanner = new Scanner(new File(CACHE_PATH));
            if (scanner.hasNext()) {
                String json = scanner.useDelimiter("\\Z").next();
                cache = JSON.parseObject(json, YouTubeCache.class);
                for (ChannelResponse elem : cache.getResponses()) {
                    if (elem.getItems().size() != 0 && elem.getItems().get(0).getId().equals(idChannel)) {
                        return elem;
                    }
                }
            }
            ChannelResponse response = Unirest.get(properties.getProperty("YOU_TUBE"))
                    .routeParam("method", "channels")
                    .queryString("id", idChannel)
                    .queryString("part", "snippet,statistics")
                    .queryString("key", "AIzaSyDMk2QAFyas-sbf0K9nFq-U5J5LAJHr81k")
                    .asObject(ChannelResponse.class).getBody();

            if (cache == null) {
                cache = new YouTubeCache();
            }
            cache.getResponses().add(response);
            if (Settings.checkCache) {
                FileWriter writer = new FileWriter(new File(Settings.cachePath)); // if path != null
                writer.write(JSON.toJSONString(cache));
                writer.flush();
            }
            return response;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static PlaylistResponse getChannelPlaylist(String channelId, String nextPageToken){
        try {
            return Unirest.get(properties.getProperty("YOU_TUBE"))
                    .routeParam("method", "playlists")
                    .queryString("channelId", channelId)
                    .queryString("pageToken", nextPageToken)
                    .queryString("part", "snippet,contentDetails")
                    .queryString("key", properties.getProperty("API_KEY"))
                    .asObject(PlaylistResponse.class).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PlaylistItemsResponse getPlaylistItems(String playlistId, String nextPageToken){
        try {
            return Unirest.get(properties.getProperty("YOU_TUBE"))
                    .routeParam("method", "playlistItems")
                    .queryString("playlistId", playlistId)
                    .queryString("pageToken", nextPageToken)
                    .queryString("part", "snippet,contentDetails")
                    .queryString("key", properties.getProperty("API_KEY"))
                    .asObject(PlaylistItemsResponse.class).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static VideoResponse getVideo(String videoId){
        try {
            return Unirest.get(properties.getProperty("YOU_TUBE"))
                    .routeParam("method", "videos")
                    .queryString("id", videoId)
                    .queryString("part", "statistics")
                    .queryString("key", properties.getProperty("API_KEY"))
                    .asObject(VideoResponse.class).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
