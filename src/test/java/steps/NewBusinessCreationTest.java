package steps;

import browserManager.PlaywrightFactory;
import com.coface.corp.autonomy.pages.externalcomponents.ElanLoginPage;
import com.coface.corp.autonomy.pages.homepage.HomePage;
import com.microsoft.playwright.Page;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.testng.Reporter;

import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Slf4j
public class NewBusinessCreationTest {

    private static final String AUTONOMY_CUCUMBER_PASSWORD_1 = "AUTONOMY_CUCUMBER_PASSWORD_1";

    private Page page;
    private ElanLoginPage login;
    private HomePage homePage;
    private String contractNumber;


    @Before("@web")
    public void beforeScenario() {
        log.info("beforeScenario BEGIN");

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                log.info("Close context");
            }
        });

    }

    @Given("User is on Autonomy Contract Home Page from Elan application")
    public void user_is_on_autonomy_contract_home_page_from_elan_application() {
        String elanUser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("elanUser");
        String elanPass = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("elanPass");
        //String elanPass = System.getenv(AUTONOMY_CUCUMBER_PASSWORD_1);
        String elanURL = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("elanURL");
        String appURL = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("appURL");
        this.page = PlaywrightFactory.getPage();
        this.page.navigate(elanURL);

        login = new ElanLoginPage(page);
        login.loginIntoElanApp(elanUser, elanPass);
        this.page.navigate(appURL);
        homePage = new HomePage(this.page);
    }

    @When("User creates a new business case with the below easyEstabNumber")
    public void user_creates_a_new_business_case_with_the_below_easy_estab_number(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> easyNumbersMap = dataTable.asMap(String.class, String.class);
        String easyEstabNumber = easyNumbersMap.get("easyEstabNumber");
        this.homePage.createContractByEasyEtab(easyEstabNumber);

    }

    @Then("The application navigates the questionnaire page. A new contract is created and the company name is {string}")
    public void goto_questionnaire_page_a_new_contract_is_created_and_the_company_name_is(String companyName) {
        //contractNumber = page.locator("tr").nth(2).locator("td").nth(1).innerText();
        this.contractNumber = page.locator("#h_contractNumber").innerText();
        assertThat(page.locator(".ellipsis")).hasText(companyName);
    }

}
