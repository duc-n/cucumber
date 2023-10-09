package com.coface.corp.autonomy.pages.externalcomponents;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CompanyView {
    private final Page page;
    private final Frame companyViewFrame;
    private final Locator easyNumber;
    private final Locator easyNumberEstab;

    private final Locator searchButton;
    private final Locator selectCompanyBtn;
    private final String companyViewModal = "#companyViewModal";

    public CompanyView(Page page) {
        this.page = page;

        this.page.locator(companyViewModal).click();
        companyViewFrame = page.frame("searchCompanyViewUrl");
        easyNumber = companyViewFrame.locator("#easyNumber");
        easyNumberEstab = companyViewFrame.locator("#easyEstabNumber");
        searchButton = companyViewFrame.locator("#searchButton");
        selectCompanyBtn = companyViewFrame.locator("#selectCompanyBtnId");
    }

    public void getCompanyByEasyNumber(String easyNumber) {
        this.easyNumber.fill(easyNumber);
        searchButton.click();
        selectCompanyBtn.click();
    }

    public void getCompanyByEasyEtab(String easyEtab) {
        this.easyNumberEstab.fill(easyEtab);
        searchButton.click();
        selectCompanyBtn.click();
    }

}
