package com.trans.web_trans_java.common.result;

import lombok.Data;

@Data
public class SearchKey {
    private String keyword;
    public boolean isKeywordEmpty() {
        return keyword == null || keyword.isEmpty();
    }
}