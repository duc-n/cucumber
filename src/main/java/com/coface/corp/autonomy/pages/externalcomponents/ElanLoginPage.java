package com.coface.corp.autonomy.pages.externalcomponents;

import com.microsoft.playwright.Page;

public class ElanLoginPage {

    private final Page page;
    // Locator — — — -
    String username = "id=loginForm:login";
    String password = "id=loginForm:pwd";
    String clickLogin = "id=loginForm:logonButton";

    public ElanLoginPage(Page page) {
        this.page = page;
    }

    public String getTitle() {
        return page.title();
    }

    public void enterUserName(String elanAcc) {
        page.fill(username, elanAcc);
    }

    public void enterPassword(String pass) {
        page.fill(password, pass);
    }

    public void clickLoginButton() {
        page.click(clickLogin);
    }

    public void loginIntoElanApp(String elanAcc, String pass) {
        enterUserName(elanAcc);
        enterPassword(pass);
        clickLoginButton();
    }

}
