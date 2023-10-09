package browserManager;

import com.microsoft.playwright.Playwright;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class PlaywrightManager {
    private static final ThreadLocal<Playwright> PLAYWRIGHT_THREAD_LOCAL = ThreadLocal.withInitial(() -> null);

    private PlaywrightManager() {
    }

    static Playwright getPlaywright() {
        if (Objects.isNull(PLAYWRIGHT_THREAD_LOCAL.get())) {
            Map<String, String> env = new HashMap<>();

            env.put("DEBUG","pw:api");
            env.put("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD","1");

            Playwright.CreateOptions cOps = new Playwright.CreateOptions();
            cOps.setEnv(env);

            Playwright pw = Playwright.create(cOps);
            setPlaywright(pw);
        }
        return PLAYWRIGHT_THREAD_LOCAL.get();
    }

    private static void setPlaywright(Playwright pw) {
        PLAYWRIGHT_THREAD_LOCAL.set(pw);
    }

    static void quit() {
        if (Objects.nonNull(PLAYWRIGHT_THREAD_LOCAL.get())) {
            try {
                PLAYWRIGHT_THREAD_LOCAL.get().close();
                PLAYWRIGHT_THREAD_LOCAL.remove();
            } catch (Exception err) {
                System.err.println(err.getMessage());
            }
        }
    }
}
