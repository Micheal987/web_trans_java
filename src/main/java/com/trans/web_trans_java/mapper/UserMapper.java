package com.trans.web_trans_java.mapper;

import com.github.pagehelper.PageHelper;
import com.trans.web_trans_java.entity.UserModel;

import java.util.List;

public interface UserMapper {
    UserModel selectUserByIdSql(Integer id);
    UserModel selectUserByInfoSql(UserModel user);
    List<UserModel> selectUserListByInfoSql(Integer PageNum, Integer PageSize,UserModel user);
    List<UserModel> selectUserListByPageSql(Integer PageNum, Integer PageSize,UserModel userModel);
    Integer createUserSql(UserModel user);
    Integer updateUserByIdSql(Integer id,UserModel user);
    Integer delUserRemoveListSql(List<Integer> ids);
}
