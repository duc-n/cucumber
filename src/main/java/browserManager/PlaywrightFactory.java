package browserManager;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.Scenario;

import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Objects;

public final class PlaywrightFactory {
    public static final String MEDIA_TYPE = "image/png";
    public static final String SCREENSHOT = "Screenshot :";

    private PlaywrightFactory() {
    }

    public static Browser getBrowser() {
        return BrowserManager.getBrowser();
    }

    public static BrowserContext getContext() {
        return BrowserContextManager.getBrowserContext();
    }

    public static Page getPage() {
        return PageManager.getPage();
    }

    public static void setPage(Page page) {
        PageManager.setPage(Objects.requireNonNullElse(page, getPage()));
    }

    public static Playwright getPlaywright() {
        return PlaywrightManager.getPlaywright();
    }

    public static void quit() {
        PageManager.quit();
        BrowserContextManager.quit();
        BrowserManager.quit();
        PlaywrightManager.quit();
    }

    public static void takeScreenshot() {
        long millisStart = Calendar.getInstance().getTimeInMillis();
        PlaywrightFactory.getPage().screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("target/test-output/screenShots/" + millisStart + ".png"))
                .setFullPage(true));
    }

    public static byte[] takeScreenshotAndReturnByte() {
        return PlaywrightFactory.getPage().screenshot(new Page.ScreenshotOptions()
                .setFullPage(true));
    }

    public static void attachScreenshotToReport(Scenario scenario) {
        byte[] screenshot = PlaywrightFactory.takeScreenshotAndReturnByte();
        scenario.attach(screenshot, MEDIA_TYPE, SCREENSHOT);

    }


}
