package browserManager;

import com.microsoft.playwright.Page;

import java.util.Objects;

public final class PageManager {
    private static final ThreadLocal<Page> PAGE_THREAD_LOCAL = ThreadLocal.withInitial(() -> null);

    private PageManager() {
    }

    static Page getPage() {
        if (Objects.isNull(PAGE_THREAD_LOCAL.get())) {
            Page page = BrowserContextManager.getBrowserContext().newPage();
            setPage(page);
        }
        return PAGE_THREAD_LOCAL.get();
    }

    static void setPage(Page page) {
        PAGE_THREAD_LOCAL.set(page);
    }

    static void quit() {
        if (Objects.nonNull(PAGE_THREAD_LOCAL.get())) {
            try {
                PAGE_THREAD_LOCAL.get().close(new Page.CloseOptions().setRunBeforeUnload(false));
                PAGE_THREAD_LOCAL.remove();
            } catch (Exception err) {
                System.err.println("Error while closing the page: " + err);
            }
        }
    }

}
