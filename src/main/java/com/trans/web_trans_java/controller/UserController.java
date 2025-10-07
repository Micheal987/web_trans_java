package com.trans.web_trans_java.controller;

import com.trans.web_trans_java.common.enums.CodeEnums;
import com.trans.web_trans_java.common.result.PaginationParam;
import com.trans.web_trans_java.common.result.Result;
import com.trans.web_trans_java.common.result.ResultList;
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
    public Result<UserModel> index(@PathVariable("id") Integer id) {
        return Result.successWithData(userService.getUserMapper(id));
    }

    //register
    @PostMapping("/register")
    public Result<Void> crateUser(@RequestBody UserModel user) {
        Integer res = userService.crateUserMapper(user);
        if (res <= 0) {
            throw new BusinessException(CodeEnums.FAIL, "用户创建失败稍后重试");
        }
        return Result.failWithMsg("创建成功");
    }

    //login
    @PostMapping("/login")
    public Result<LoginToken> login(@RequestBody UserModel user) {
        LoginToken result = new LoginToken(userService.authMapper(user));
        return Result.successWithData(result);
    }


    //page
    @GetMapping("/list")
    public Result<ResultList<UserModel>> getUserList(@RequestParam Integer PageNum, @RequestParam Integer pageSize, UserModel user) {
        List<UserModel> res = userService.getUserListByInfoMapper(PageNum, pageSize, user);
        PaginationParam p = new PaginationParam();
        p.setPage(PageNum);
        p.setLimit(pageSize);
        return ResultList.successWithList(p, res);
    }
    @GetMapping("/page")
    public Result<ResultList<UserModel>> getUserListPage(@RequestParam Integer PageNum, @RequestParam Integer pageSize, UserModel user) {
        List<UserModel> res = userService.getUserListPageMapper(PageNum, pageSize, user);
        PaginationParam p = new PaginationParam();
        p.setPage(PageNum);
        p.setLimit(pageSize);
        return ResultList.successWithList(p, res);
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
