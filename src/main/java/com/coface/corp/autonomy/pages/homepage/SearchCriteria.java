package com.coface.corp.autonomy.pages.homepage;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchCriteria {
    private String typeOfUse;
    private  String creationDateRange;
}
