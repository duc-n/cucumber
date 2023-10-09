package browserManager;

import com.microsoft.playwright.BrowserContext;

import java.util.Objects;

public final class BrowserContextManager {
    private static final ThreadLocal<BrowserContext> BROWSER_CONTEXT_THREAD_LOCAL = ThreadLocal.withInitial(() -> null);

    private BrowserContextManager() {
    }

    static BrowserContext getBrowserContext() {
        if (Objects.isNull(BROWSER_CONTEXT_THREAD_LOCAL.get())) {

            BrowserContext browserCtx = createNormalContext();
            setBrowserContext(browserCtx);
        }

        return BROWSER_CONTEXT_THREAD_LOCAL.get();
    }

    static void setBrowserContext(BrowserContext ctx) {
        BROWSER_CONTEXT_THREAD_LOCAL.set(ctx);
    }
    private static BrowserContext createNormalContext() {

        return BrowserManager.getBrowser().newContext();
    }

    static void quit() {
        if (Objects.nonNull(BROWSER_CONTEXT_THREAD_LOCAL.get())) {
            try {
                BROWSER_CONTEXT_THREAD_LOCAL.get().close();
                BROWSER_CONTEXT_THREAD_LOCAL.remove();
            } catch (Exception err) {
            }
        }
    }

}
