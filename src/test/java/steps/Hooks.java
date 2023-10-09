package steps;

import browserManager.PlaywrightFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hooks {

    @Before
    public void setUp() {
        log.info("###### Hook before ########");
    }

    @After
    public void tearDown(Scenario scenario) {
        log.info("###### Closes browser context ! ########");

        if (scenario.isFailed()) {
            PlaywrightFactory.attachScreenshotToReport(scenario);
        }
        //PlaywrightFactory.quit();
    }
}
