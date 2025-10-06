package com.trans.web_trans_java.exception;

import lombok.Data;

@Data
public class ExceptionData<T> {
    private T data;

    public ExceptionData(T data) {
        this.data = data;
    }
}
