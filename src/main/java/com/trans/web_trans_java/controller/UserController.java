package com.trans.web_trans_java.controller;

import cn.dev33.satoken.util.SaResult;
import com.trans.web_trans_java.common.enums.CodeEnums;
import com.trans.web_trans_java.common.result.PaginationParam;
import com.trans.web_trans_java.common.result.Result;
import com.trans.web_trans_java.common.result.ResultList;
import com.trans.web_trans_java.common.result.SearchKey;
import com.trans.web_trans_java.dto.request.IdsRequest;
import com.trans.web_trans_java.dto.response.LoginToken;
import com.trans.web_trans_java.entity.UserModel;
import com.trans.web_trans_java.exception.BusinessException;
import com.trans.web_trans_java.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Resource
    UserService userService;

    //info
    @GetMapping("/info/{id}")
    public Result<UserModel> userInfoView(@PathVariable("id") Integer id) {
        return Result.successWithData(userService.getUserMapper(id));
    }

    //register
    @PostMapping("/register")
    public Result<Void> crateUserView(@RequestBody UserModel user) {
        Integer res = userService.crateUserMapper(user);
        if (res <= 0) {
            throw new BusinessException(CodeEnums.FAIL, "用户创建失败稍后重试");
        }
        return Result.failWithMsg("创建成功");
    }

    //login
    @PostMapping("/login")
    public Result<LoginToken> loginView(@RequestBody UserModel user) {
        LoginToken result = new LoginToken(userService.authMapper(user));
        return Result.successWithData(result);
    }
    //login
    @PostMapping("/logout")
    public Result<SaResult> logoutView() {
        return  Result.successWithData(userService.logoutMapper());
    }


    //page
    @GetMapping("/list")
    public Result<ResultList<UserModel>> userListView(@RequestParam Integer PageNum, @RequestParam Integer pageSize, UserModel user) {
        List<UserModel> res = userService.getUserListByInfoMapper(PageNum, pageSize, user);
        PaginationParam pagination = new PaginationParam(pageSize,PageNum,res.size());
        return ResultList.successParamList(pagination,res);
    }
    @GetMapping("/page")
    public Result<ResultList<UserModel>> userListPageView(@RequestParam Integer PageNum, @RequestParam Integer pageSize, UserModel user) {
        List<UserModel> res = userService.getUserListPageMapper(PageNum, pageSize, user);
        PaginationParam pagination = new PaginationParam(pageSize,PageNum,res.size());
        return new ResultList<UserModel>(pagination,new SearchKey(),res).successWithEvenSelf(res.size());
    }

    // update
    @PutMapping("/update/{id}")
    public Result<Void> userUpdateView(@PathVariable Integer id, @RequestBody UserModel user) {
        Integer res = userService.updateUserMapper(id, user);
        if (res <= 0) {
            return Result.failWithMsg("更新失败");
        }
        return Result.successWithMsg("更新成功");
    }

    // remove
    @DeleteMapping("/remove")
    public Result<Void> userRemoveListView(@RequestBody IdsRequest ids) {
        Integer count = userService.deleteUserMapper(ids.getIds());
        String msg = String.format("删除成功:%d-%s", count, "条");
        return Result.successWithMsg(msg);
    }

}
