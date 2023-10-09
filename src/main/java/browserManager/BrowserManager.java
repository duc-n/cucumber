package browserManager;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.options.Proxy;
import org.testng.Reporter;

import java.nio.file.Paths;
import java.util.Objects;

public class BrowserManager {
    private static final ThreadLocal<Browser> BROWSER_THREAD_LOCAL = ThreadLocal.withInitial(() -> null);
    private static final String PROXY_URL = "http://proxy-priv.coface.dns:8080";
    private static final String BYPASS = ".coface.dns";

    private BrowserManager() {

    }

    protected static Browser getBrowser() {
        if (Objects.isNull(BROWSER_THREAD_LOCAL.get())) {
            Browser browser = launchBrowser();
            setBrowser(browser);
        }
        return BROWSER_THREAD_LOCAL.get();
    }

    protected static void setBrowser(Browser browser) {
        BROWSER_THREAD_LOCAL.set(browser);
    }

    private static Browser launchBrowser() {
        String chromePath = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("chromePath");

        return PlaywrightManager.getPlaywright().chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome")
                .setHeadless(false)
                .setExecutablePath(Paths.get(chromePath))
                .setProxy(new Proxy(PROXY_URL).setBypass(BYPASS))
        );
    }

    protected static void quit() {
        if (Objects.nonNull(BROWSER_THREAD_LOCAL.get())) {
            try {
                BROWSER_THREAD_LOCAL.get().close();
                BROWSER_THREAD_LOCAL.remove();
            } catch (Exception err) {
                System.err.println("Error while closing the browser: " + err);
            }
        }
    }

}
