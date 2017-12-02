import entities.YouTubeItem;
import entities.YouTubeResponse;

public class InfoViewer {
    private static void showChannelInfo(YouTubeResponse body) {
        for (YouTubeItem item : body.getItems()) {
            String date = item.getSnippet().getPublishedAt();
            System.out.println("          title: " + item.getSnippet().getTitle());
            System.out.println("             id: " + item.getId());
            System.out.println("    publishedAt: " + date.substring(0, 4) + "/" + date.substring(5, 7) + "/"
                    + date.substring(8, 10));
            System.out.println("subscriberCount: " + item.getStatistics().getSubscriberCount());
            System.out.println("      viewCount: " + item.getStatistics().getViewCount());
            System.out.println("     videoCount: " + item.getStatistics().getVideoCount());
        }
    }

    public static void showGlobalInfo(String channelId) {
        YouTubeResponse body = YouTubeClient.getChannelInfo(channelId).getBody();
        System.out.println("---------------------------------------------");
        showChannelInfo(body);
        System.out.println("---------------------------------------------");
    }

    public static void showMediaResonanceInfo(String channelId) {
        YouTubeResponse body = YouTubeClient.getChannelInfo(channelId).getBody();
        System.out.println("---------------------------------------------");
        showChannelInfo(body);
        for (YouTubeItem item : body.getItems()) {
            String date = item.getSnippet().getPublishedAt();
            System.out.println("   commentCount: " + item.getStatistics().getCommentCount());
        }
        System.out.println("---------------------------------------------");
    }

    public static void main(String[] args) {
        InfoViewer.showGlobalInfo("UC_x5XG1OV2P6uZZ5FSM9Ttw");
        InfoViewer.showMediaResonanceInfo("UC-lHJZR3Gqxm24_Vd_AJ5Yw");
    }
}
