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
    private Integer total;

    public PaginationParam(Integer limit, Integer page) {
        this.limit = limit;
        this.page = page;
        this.total = total();
    }

    public PaginationParam(Integer limit, Integer page, Integer total) {
        this.limit = limit;
        this.page = page;
        this.total = total;
    }

    public Integer total() {
        return getLimit() * getPage();
    }
}
