package com.trans.web_trans_java.common.result;


import lombok.Data;

import java.util.List;

@Data
public class ResultList<T> {
    PaginationParam paginationParam;
    SearchKey searchKey;
    private List<T> items;

    //必须做限制
    //todo 对接口增加jwt权限认证,只有admin才能查 50页以上的数据
    //todo redis/map黑名单机制  存账号一分钟内查询次数,
    //这种直接传按我的理解,没有做校验,这个有风险
    //必须要你自己controller绑定PaginationParam这个类去校验 不能让前端随便传
    ResultList(Integer limit, Integer page, List<T> items) {
        this.paginationParam = new PaginationParam(limit, page);
        this.items = items;
    }

    ResultList(Integer limit, Integer page, Integer total, List<T> items) {
        this.paginationParam = new PaginationParam(limit, page, total);
        this.items = items;
    }

    //我认为这个方式是最好的
    //推荐这个
    ResultList(PaginationParam paginationParam, List<T> items) {
        this.paginationParam = paginationParam;
        this.items = items;
    }

    //search
    ResultList(PaginationParam paginationParam, String key, List<T> items) {
        this.paginationParam = paginationParam;
        searchKey.setKeyword(key);
        this.items = items;
    }

    //(Integer limit, Integer page, List<T> items
    private static <T> ResultList<T> newResultList(Integer limit, Integer page, List<T> items) {
        return new ResultList<>(limit, page, items);
    }

    public ResultList<T> successWithSelf() {
        return newResultList(getPaginationParam().getLimit(), getPaginationParam().getPage(), getItems());
    }

    public static <T> Result<ResultList<T>> success(Integer limit, Integer page, T items) {
        ResultList<T> data = newResultList(limit, page, List.of(items));
        return Result.failWithData(data);
    }

    public static <T> Result<ResultList<T>> success(Integer limit, Integer page, List<T> items) {
        ResultList<T> data = newResultList(limit, page, items);
        return Result.failWithData(data);
    }

    public static <T> Result<ResultList<T>> successWithList(PaginationParam paginationParam, List<T> items) {
        ResultList<T> data = newResultList(paginationParam.getLimit(), paginationParam.getPage(), items);
        return Result.failWithData(data);
    }

    //PaginationParam paginationParam, List<T> items
    public static <T> ResultList<T> newResultListParam(PaginationParam paginationParam, List<T> items) {
        return new ResultList<>(paginationParam, items);
    }

    public ResultList<T> successWithParamSelf() {
        return newResultListParam(getPaginationParam(), getItems());
    }

    public static <T> Result<ResultList<T>> successParam(PaginationParam paginationParam, T items) {
        ResultList<T> data = newResultListParam(paginationParam, List.of(items));
        return Result.failWithData(data);
    }

    public static <T> Result<ResultList<T>> successParamList(PaginationParam paginationParam, List<T> items) {
        ResultList<T> data = newResultListParam(paginationParam, items);
        return Result.failWithData(data);
    }

    public static <T> Result<ResultList<T>> successWithParamData(ResultList<T> result) {
        ResultList<T> data = newResultListParam(result.getPaginationParam(), result.getItems());
        return Result.failWithData(data);
    }

    //Integer limit, Integer page, Integer total, List<T> items
    public static <T> ResultList<T> newResultListParam(Integer limit, Integer page, Integer total, List<T> items) {
        return new ResultList<>(limit, page, total, items);
    }

    //total
    public ResultList<T> successWithEvenSelf(Integer total) {
        return newResultListParam(getPaginationParam().getLimit(), getPaginationParam().getPage(), total, getItems());
    }

    public static <T> Result<ResultList<T>> successEven(Integer limit, Integer page, Integer total, T items) {
        ResultList<T> data = newResultListParam(limit, page, total, List.of(items));
        return Result.failWithData(data);
    }

    public static <T> Result<ResultList<T>> successEvenList(PaginationParam paginationParam, List<T> items) {
        ResultList<T> data = newResultListParam(paginationParam.getLimit(), paginationParam.getPage(), paginationParam.getTotal(), items);
        return Result.failWithData(data);
    }

    //Search
    private static <T> ResultList<T> newResultListSearch(PaginationParam paginationParam, String key, List<T> items) {
        return new ResultList<>(paginationParam, key, items);
    }

    public ResultList<T> successWithSearchSelf() {
        return newResultListSearch(getPaginationParam(), getSearchKey().getKeyword(), getItems());
    }

    public static <T> Result<ResultList<T>> successSearch(PaginationParam paginationParam, String key, T items) {
        ResultList<T> data = newResultListSearch(paginationParam, key, List.of(items));
        return Result.failWithData(data);
    }

    public static <T> Result<ResultList<T>> successSearchList(PaginationParam paginationParam, String key, List<T> items) {
        ResultList<T> data = newResultListSearch(paginationParam, key, items);
        return Result.failWithData(data);
    }
}
