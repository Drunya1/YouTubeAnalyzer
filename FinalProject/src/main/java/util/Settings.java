package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Settings {

    public static boolean checkTime;
    public static boolean checkCache;
    private static long currentTimeMills;
    public static String cachePath;

    private static final String SETTING_PATH = "src/main/resources/setting/setting.txt"; // replace for windows

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

    public static void checkCurrentConfiguration() {
        try (final BufferedReader reader = new BufferedReader(new FileReader(SETTING_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(":");
                switch (split[0]) {
                    case "checkTime":
                        checkTime = split[1].equals("1");
                        break;
                    case "checkCache":
                        checkCache = split[1].equals("1");
                        break;
                    case "cachePath":
                        cachePath = split[1];
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}