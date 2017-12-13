package util;

public class Settings {

    public static boolean checkTime;
    public static boolean checkCache;
    private static long currentTimeMills;
    public static String cachePath;

    public static void startTime() {
        currentTimeMills = System.currentTimeMillis();
    }

    public static String finishTime() {
        currentTimeMills = System.currentTimeMillis() - currentTimeMills;
        long second = (currentTimeMills / 1000) % 60;
        long minute = (currentTimeMills / (1000 * 60)) % 60;
        long hour = (currentTimeMills / (1000 * 60 * 60)) % 24;

        return String.format("Spent time " + "%02d:%02d:%02d", hour, minute, second);
    }
}
