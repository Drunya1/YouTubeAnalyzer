import entities.YouTubeItem;
import entities.YouTubeResponse;

public class InfoViewer {
    public static void showGlobalInfo(String channelId) {
        YouTubeResponse body = YouTubeClient.getChannelInfo(channelId).getBody();
        for (YouTubeItem item : body.items) {
            String date = item.snippet.publishedAt;
            System.out.println("---------------------------------------------");
            System.out.println("          title: " + item.snippet.title);
            System.out.println("             id: " + item.id);
            System.out.println("    publishedAt: " + date.substring(0, 4) + "/" + date.substring(5, 7) + "/"
                    + date.substring(8, 10));
            System.out.println("subscriberCount: " + item.statistics.subscriberCount);
            System.out.println("      viewCount: " + item.statistics.viewCount);
            System.out.println("     videoCount: " + item.statistics.videoCount);
            System.out.println("---------------------------------------------");
        }
    }

    public static void showMediaResonanceInfo(String channelId) {
        YouTubeResponse body = YouTubeClient.getChannelInfo(channelId).getBody();
        for (YouTubeItem item : body.items) {
            String date = item.snippet.publishedAt;
            System.out.println("---------------------------------------------");
            System.out.println("          title: " + item.snippet.title);
            System.out.println("             id: " + item.id);
            System.out.println("    publishedAt: " + date.substring(0, 4) + "/" + date.substring(5, 7) + "/"
                    + date.substring(8, 10));
            System.out.println("subscriberCount: " + item.statistics.subscriberCount);
            System.out.println("      viewCount: " + item.statistics.viewCount);
            System.out.println("     videoCount: " + item.statistics.videoCount);
            System.out.println("   commentCount: " + item.statistics.commentCount);
            System.out.println("---------------------------------------------");
        }
    }

    public static void main(String[] args) {
        InfoViewer.showGlobalInfo("UC_x5XG1OV2P6uZZ5FSM9Ttw");
        InfoViewer.showMediaResonanceInfo("UC_x5XG1OV2P6uZZ5FSM9Ttw");
    }
}
