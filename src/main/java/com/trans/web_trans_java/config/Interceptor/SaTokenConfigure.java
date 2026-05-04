package com.trans.web_trans_java.config.Interceptor;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册 Sa-Token 的拦截器
    /*
     * 注册 Sa-Token 拦截器，并使用 SaRouter 定义多路径规则
     *
     * 校验顺序：按照 lambda 表达式内部书写的顺序依次匹配
     * 示例规则：
     * - 拦截所有请求，排除 /user/login（登录放行）
     * - /admin/** 路径需要 admin 或 super-admin 角色
     * - /user/** 路径需要 user 权限
     * - /order/** 路径需要 order 权限
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
            String[] pathList = {"/user/register", "/user/login", "/test/**", "/config/**"};
            // 登录校验 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
            SaRouter
                    .match("/**")
                    .notMatch(pathList)
                    .check(r -> StpUtil.checkLogin());

            // 角色校验 -- 拦截以 admin 开头的路由，必须具备 admin 角色或者 super-admin 角色才可以通过认证
            SaRouter.match("/admin/**", r -> StpUtil.checkRoleOr("admin", "super-admin"));

            // 权限校验 -- 不同模块校验不同权限
            SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
            SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
            SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
            SaRouter.match("/orders/**", r -> StpUtil.checkPermission("orders"));
            SaRouter.match("/notice/**", r -> StpUtil.checkPermission("notice"));
            SaRouter.match("/comment/**", r -> StpUtil.checkPermission("comment"));


        })).addPathPatterns("/**");
    }
}
