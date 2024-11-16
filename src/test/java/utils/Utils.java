package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static AppiumDriver driver;
    static JSONObject deviceObj;
    static JSONObject properties = JsonParser.parse("properties.json");
    static String userName =  properties.getString("userName");
    static String accessKey = properties.getString("accessKey");
    public static AppiumDriver getDriver(){
        return driver;
    }

    public static JSONObject getDeviceObj(String deviceId, String fileName){
        return new JSONObject(JsonParser.parse(fileName).getJSONObject(deviceId).toString());
    }

    public static DesiredCapabilities getEmulatorDesiredCapabilities(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "android");
        caps.setCapability("appium:platformVersion", "14");
        caps.setCapability("appium:deviceName", "pixel-6-android-14");
        caps.setCapability("appium:app", "src/test/resources/apps/WikipediaSample.apk");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:autoGrantPermissions", "true");

        return caps;
    }

    public  static DesiredCapabilities getDesiredCapabilities(String deviceId){
        deviceObj = getDeviceObj(deviceId, "devices.json");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "android");
        caps.setCapability("platformVersion", deviceObj.getString("os_version"));
        caps.setCapability("deviceName", deviceObj.getString("device"));
        caps.setCapability("app", deviceObj.getString("app_url"));
        caps.setCapability("browserstack.networkLogs", "true");
        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("project", properties.getString("project"));
        caps.setCapability("build", properties.getString("build"));
        caps.setCapability("name", properties.getString("name"));

        return caps;
    }

    public static URL createBrowserStackUrl() throws MalformedURLException {
        //JSONObject deviceObj = new JSONObject(JsonParser.parse("Devices.json").getJSONObject(deviceID).toString());
        // Testar sem HTTPS -> HTTP
        return new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub");
    }

    public static URL createtEmulatorUrl() throws  MalformedURLException {
        return new URL("http://127.0.0.1:4723");
    }

    public static void acessarLocalApp() {
        try {
            driver = new AndroidDriver(createtEmulatorUrl(), getEmulatorDesiredCapabilities());
            configTimeOut();
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        }

    }

    public static void configTimeOut(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static void acessarCloudApp(String deviceId) {

        DesiredCapabilities desiredCapabilities = getDesiredCapabilities(deviceId);
        URL url = null;
        try {
            url = createBrowserStackUrl();
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        }

        switch (deviceObj.getString("os")){
            case "android":
                driver = new AndroidDriver(url, desiredCapabilities);
                configTimeOut();
                System.out.println("[Check Info] - Android app rodando!");
                break;
            case "ios":
                driver = new IOSDriver(url, desiredCapabilities);
                configTimeOut();
                System.out.println("[Check Info] - iOS app rodando!");
                break;
            default:
                System.out.println("[Check Fail] - Falha ao criar o driver, invalid OS!");
                throw new IllegalStateException("invalid device OS in devices.json");
        }
    }
}
