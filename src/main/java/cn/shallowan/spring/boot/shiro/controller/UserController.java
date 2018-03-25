package cn.shallowan.spring.boot.shiro.controller;

import cn.shallowan.spring.boot.shiro.entity.User;
import cn.shallowan.spring.boot.shiro.exception.UnauthorizedException;
import cn.shallowan.spring.boot.shiro.response.Response;
import cn.shallowan.spring.boot.shiro.service.UserService;
import cn.shallowan.spring.boot.shiro.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.SecurityUtils;


/**
 * @author ShallowAn
 * <p>
 * URL	                         作用
 * /login                        登入
 * /article           所有人都可以访问，但是用户与游客看到的内容不同
 * /require_auth             登入的用户才可以进行访问
 * /require_role            admin的角色用户才可以登入
 * /require_permission      拥有view和edit权限的用户才可以访问
 */

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public Response login(@RequestParam("username") String username,
                          @RequestParam("password") String password) {
        User user = userService.getUser(username);
        if (user.getPassword().equals(password)) {
            return new Response(200, "Login success", JWTUtil.sign(username, password));
        } else {
            throw new UnauthorizedException();
        }
    }

    @GetMapping("/article")
    public Response article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new Response(200, "You are already logged in", null);
        } else {
            return new Response(200, "You are guest", null);
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public Response requireAuth() {
        return new Response(200, "You are authenticated", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public Response requireRole() {
        return new Response(200, "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public Response requirePermission() {
        return new Response(200, "You are visiting permission require edit, view", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response unauthorized() {
        return new Response(401, "Unauthorized", null);
    }
}
