package com.trans.web_trans_java.common.result;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;


@Data
public class PaginationParam {
    @Min(value = 1)
    @Max(value = 100)
    private Integer limit;
    @Min(value = 1)
    @Max(value = 50)
    private Integer page;
    private String keyword;

    public Integer total() {
        return getLimit() * getPage();
    }

    public boolean isKeywordEmpty() {
        return keyword == null || keyword.isEmpty();
    }
}
