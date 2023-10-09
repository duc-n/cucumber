package com.coface.corp.autonomy.pages.homepage;

import com.microsoft.playwright.Page;

public class SearchPage {
	Page page;

	SearchPage(Page page) {
		this.page=page;
	}

	public int getRowNumber() {
		return page.locator("tr").count();
	}
}
