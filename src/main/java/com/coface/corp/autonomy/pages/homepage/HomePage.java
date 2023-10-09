package com.coface.corp.autonomy.pages.homepage;

import com.coface.corp.autonomy.pages.externalcomponents.CompanyView;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HomePage {

    private final Page page;
    private final Locator yesBtn;
    private final Locator typeOfUseSelect;
    private final Locator startDateInput;
    private final Locator createNewBusinessBtn;
    private final Locator companyNameInput;
    private final Locator ownerNameInput;
    private final Locator searchBtn;

    private CompanyView companyView;


    public HomePage(Page page) {
        this.page = page;
        this.typeOfUseSelect = page.locator("#business");
        this.startDateInput = page.locator("input#startDate");
        this.createNewBusinessBtn = page.locator("#createNewBusiness");
        this.yesBtn = page.locator("#yes_btn");
        this.companyNameInput = page.locator("#companyName");
        this.ownerNameInput = page.locator("#ownerName");
        this.searchBtn = page.locator("#searchContractsButton");
    }

    public SearchPage search(SearchCriteria searchCriteria) {
        typeOfUseSelect.selectOption(searchCriteria.getTypeOfUse());
        startDateInput.fill(searchCriteria.getCreationDateRange());

        return new SearchPage(page);
    }

    public void searchByCompanyNameAndOwnerName(String companyName, String ownerName) {
        companyNameInput.fill(companyName);
        ownerNameInput.fill(ownerName);
        this.searchBtn.click();
        Response response = page.waitForResponse("**/rest/cases/search", () -> {
            log.info("Search terminated !!!");
            //page.getByText("Update").click();
        });

    }

    public void createContractByEasyNumber(String easyNumber) {

        this.createNewBusinessBtn.click();
        this.companyView = new CompanyView(page);
        this.companyView.getCompanyByEasyNumber(easyNumber);
        this.yesBtn.click();
    }

    public void createContractByEasyEtab(String easyEtab) {

        this.createNewBusinessBtn.click();
        this.companyView = new CompanyView(page);
        this.companyView.getCompanyByEasyEtab(easyEtab);
        this.yesBtn.click();
    }
}
