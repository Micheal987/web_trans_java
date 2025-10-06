package com.trans.web_trans_java.common.result;


import lombok.Data;

import java.util.List;

@Data
public class ResultList<T> {
    private Integer limit;
    private Integer page;
    private Integer total;
    private List<T> items;

    public Integer total() {
        return getLimit() * getPage();
    }
    ResultList(Integer limit, Integer page, Integer total, List<T> items) {
        this.limit = limit;
        this.page = page;
        this.total = total;
        this.items = items;
    }
    public static <T> ResultList<T> newResultList(Integer limit, Integer page, Integer total, List<T> items) {
        return new ResultList<>(limit, page, total, items);
    }

    public ResultList<T> successWithSelf(){
        return  newResultList(getLimit(),getPage(),total(),getItems());
    }
    public static <T> Result<ResultList<T>> successWithData(ResultList<T> result) {
        ResultList<T> data = newResultList(result.getLimit(), result.getPage(), result.total(), result.getItems());
        return Result.failWithData(data);
    }

    public static <T> Result<ResultList<T>> success(Integer limit, Integer page, Integer total,T items) {
        ResultList<T> data = newResultList(limit,page, total, List.of(items));
        return Result.failWithData(data);
    }

    public static <T> Result<ResultList<T>> successWith(PaginationParam paginationParam, T items) {
        ResultList<T> data = newResultList(paginationParam.getLimit(), paginationParam.getPage(), paginationParam.total(), List.of(items));
        return Result.failWithData(data);
    }
    public static <T> Result<ResultList<T>> successWithList(PaginationParam paginationParam, List<T> items) {
        ResultList<T> data = newResultList(paginationParam.getLimit(), paginationParam.getPage(), paginationParam.total(), items);
        return Result.successWithData(data);
    }

}
