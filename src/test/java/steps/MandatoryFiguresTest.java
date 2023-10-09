package steps;

import browserManager.PlaywrightFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class MandatoryFiguresTest {

    @Given("User is on the Questionnaire tab of the contract number that is created in #CMAUT-5028")
    public void user_is_on_the_questionnaire_tab_of_the_contract_number_that_is_created_in() {
        Page page = PlaywrightFactory.getPage();
        page.locator("#menu-questionnaire").click();
    }

    @When("User clicks on the MANDATORY FIGURES Accordion and fills the fields {string} {string} {string} {string}")
    public void user_clicks_on_the_mandatory_figures_accordion_and_fills_the_fields(String numberCustomer, String domesticTurnover, String exportTurnover, String theoriticalDSO) {
        // Write code here that turns the phrase above into concrete actions
        Page page = PlaywrightFactory.getPage();
        //page.navigate("http://friadwep01.coface.dns:43121/autonomyView/resources/index.html#/contract/questionnaire/77126881/58697991");
        //page.navigate("http://grprocxd10-078.group.coface.dns:7001/autonomyView/resources/index.html#/contract/questionnaire/40063706/66142575");

        page.locator("#figuresAccordionTitle").click();

        page.locator("#numberCustomerValue").click();
        page.locator("#numberCustomer").fill(numberCustomer);

        page.locator("#domesticTurnoverValue").click();
        page.locator("#domesticTurnover").fill(domesticTurnover);

        //page.locator("#exportTurnoverValue").click();
        //page.locator("#exportTurnover").fill(exportTurnover);

        page.locator("#theoriticalDSOValue").click();
        page.locator("#theoriticalDSO").fill(theoriticalDSO);

    }

    @When("User clicks on the ANALYSIS OF BAD DEBTS EXPERIENCE accordion and sets the badDebtsExperience to No")
    public void user_clicks_on_the_analysis() {
        Page page = PlaywrightFactory.getPage();
        page.locator("#badDebtorsAccordionTitle").click();
        page.locator("#badDebtsExperienceNo").click();

        page.locator("#creditSalesAccordionTitle").click();
        //#credit-sales-table >> tbody
        Locator tbody = page.locator("#credit-sales-table").locator("tbody");
        Locator lastRow = tbody.locator("tr").last();
        Locator addRowBtn = lastRow.locator("td").nth(2).locator("button").nth(1);
        addRowBtn.click();
        Locator newCountrySelect = tbody.locator("tr").last().locator("td").nth(0).getByRole(AriaRole.COMBOBOX);
        newCountrySelect.selectOption("91: IRL");

        Locator newInsurableTurnover = tbody.locator("tr").last().locator("td").nth(1).locator("input");
        newInsurableTurnover.fill("800");

    }


    @When("On the Contract Terms page User clicks on the Pricing accordion")
    public void on_the_contract_terms_page_user_clicks_on_the_pricing_accordion() {
        Page page = PlaywrightFactory.getPage();
        page.locator("#menu-policy").click();
        page.locator("#pricingAccordionTitle").click();
    }

    @Then("the Quote button is {string}")
    public void the_quote_button_is(String buttonEnabled) {
        Page page = PlaywrightFactory.getPage();
        boolean actual = page.locator("#quote-btn").isEnabled();
        log.info("Quote enabled {}", actual);
        assertEquals(Boolean.parseBoolean(buttonEnabled), actual);
    }
}
