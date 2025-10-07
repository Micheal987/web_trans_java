package com.trans.web_trans_java.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class ListRequest {
    @Min(value = 1)
    @Max(value = 100)
    private Integer limit;
    @Min(value = 1)
    @Max(value = 50)
    private Integer page;
    private String key;
}
