package com.trans.web_trans_java.service;

import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trans.web_trans_java.common.enums.CodeEnums;
import com.trans.web_trans_java.entity.UserModel;
import com.trans.web_trans_java.exception.BusinessException;
import com.trans.web_trans_java.mapper.UserMapper;
import com.trans.web_trans_java.utils.pwd.EncryptionPwd;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    //id条件
    public UserModel getUserMapper(Integer id) {
        return userMapper.selectUserByIdSql(id);
    }

    //login
    public String authMapper(UserModel user) {
        String inputPwd = user.getPassword();
        UserModel storedUser = userMapper.selectUserByInfoSql(user);
        if (storedUser == null) {
            throw new BusinessException(CodeEnums.NotFound, "用户不存在");
        }
        String storePwd = storedUser.getPassword();
        try {
            String decryptedInputPwd  = EncryptionPwd.decrypt(storePwd);

            if (!decryptedInputPwd.equals(inputPwd)) {
                log.error("输入密码{}:原密码{}", inputPwd, decryptedInputPwd);
                throw new BusinessException(CodeEnums.FAIL, "用户名称或密码错误");
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            String msg  = String.format("错误原因{}%s",e.getMessage());
            throw new BusinessException(CodeEnums.FAIL, msg);
        }
        //token
        StpUtil.login(storedUser.getID());
        return StpUtil.getTokenValue();
    }

    //分页
    public List<UserModel> getUserListByInfoMapper(Integer PageNum, Integer PageSize, UserModel user) {
        PageHelper.startPage(PageNum, PageSize);
        List<UserModel> res = userMapper.selectUserListByInfoSql(PageNum, PageSize, user);
        return PageInfo.of(res).getList();
    }

    //创建
    @Transactional // 启用事务管理
    public Integer CrateUserMapper(UserModel user) {
        UserModel storedUser = userMapper.selectUserByInfoSql(user);
        //数据设计表限制 字段唯一约束
        //要么代码做判断
        if (storedUser != null) {
            throw new BusinessException(CodeEnums.FAIL, "用户已存在");
        }
        try {
            user.setPassword(EncryptionPwd.encrypt(user.getPassword()));
        } catch (Exception e) {
            throw new BusinessException(CodeEnums.FAIL, e.getMessage());
        }
        return userMapper.createUserSql(user);
    }

    //更新
    public Integer updateUserMapper(Integer id, UserModel user) {
        user.setID(id);
        return userMapper.updateUserByIdSql(id,user);
    }

    //删除
    public Integer deleteUserMapper(List<Integer> ids) {
        return userMapper.delUserRemoveListSql(ids);
    }
}
