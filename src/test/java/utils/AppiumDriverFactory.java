package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumDriverFactory {

    public static AndroidDriver createDriver() {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName(ConfigReader.get("appium.platform.name", "Android"));
        options.setDeviceName(ConfigReader.get("appium.device.name", "emulator-5554"));
        options.setAutomationName(ConfigReader.get("appium.automation.name", "UiAutomator2"));

        String appPath = ConfigReader.get("appium.app.path");
        if (appPath != null && !appPath.isEmpty() && !appPath.equals("/path/to/wikipedia.apk")) {
            options.setApp(appPath);
        } else {
            options.setAppPackage(ConfigReader.get("appium.app.package", "org.wikipedia"));
            options.setAppActivity(ConfigReader.get("appium.app.activity", "org.wikipedia.main.MainActivity"));
        }

        options.setNoReset(false);
        options.setFullReset(false);
        options.setAutoGrantPermissions(true);

        String platformVersion = ConfigReader.get("appium.platform.version");
        if (platformVersion != null && !platformVersion.isEmpty()) {
            options.setPlatformVersion(platformVersion);
        }

        try {
            String serverUrl = ConfigReader.get("appium.server.url", "http://127.0.0.1:4723");
            AndroidDriver driver = new AndroidDriver(new URL(serverUrl), options);

            driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(ConfigReader.getInt("appium.implicit.wait", 10))
            );

            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL: " + e.getMessage());
        }
    }

    public static AndroidDriver createDriver(String deviceName, String platformVersion) {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setDeviceName(deviceName);
        options.setPlatformVersion(platformVersion);
        options.setAutomationName("UiAutomator2");
        options.setAppPackage(ConfigReader.get("appium.app.package", "org.wikipedia"));
        options.setAppActivity(ConfigReader.get("appium.app.activity", "org.wikipedia.main.MainActivity"));
        options.setNoReset(false);
        options.setAutoGrantPermissions(true);

        try {
            String serverUrl = ConfigReader.get("appium.server.url", "http://127.0.0.1:4723");
            AndroidDriver driver = new AndroidDriver(new URL(serverUrl), options);

            driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(ConfigReader.getInt("appium.implicit.wait", 10))
            );

            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL: " + e.getMessage());
        }
    }
}
