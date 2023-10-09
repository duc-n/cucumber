package steps.common;

import browserManager.PlaywrightFactory;
import com.coface.corp.autonomy.pages.homepage.HomePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.testng.Reporter;

import java.util.List;
import java.util.Map;

@Slf4j
public class CaseOpening {
    private Page page;
    private HomePage homePage;

    @Before
    public void setUp() {
        this.page = PlaywrightFactory.getPage();
        homePage = new HomePage(this.page);
    }

    @Given("User is on the homepage")
    public void user_is_on_the_homepage() {
        String appURL = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("appURL");
        this.page.navigate(appURL);
    }

    @When("In the Search Criteria panel, user clicks on the Search button after filling the following data :")
    public void in_the_search_criteria_panel_user_clicks_on_the_search_button_after_filling_the_following_data(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        data.forEach(row -> {
            String ownerName = row.get("Owner name");
            String companyName = row.get("Company Name");
            this.homePage.searchByCompanyNameAndOwnerName(companyName, ownerName);
        });

    }

    @When("In the Search results panel, user clicks on the first result")
    public void in_the_search_results_panel_user_clicks_on_the_first_result() {

        Locator contractNumberCol = page.locator("tr").nth(2).locator("td").nth(1);
        log.info("######### Contract Number {} ##################", contractNumberCol.innerText());
        Locator contractNumberLink = contractNumberCol.getByRole(AriaRole.LINK);
        contractNumberLink.click();

    }
}
