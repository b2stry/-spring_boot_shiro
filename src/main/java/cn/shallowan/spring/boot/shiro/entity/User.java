package cn.shallowan.spring.boot.shiro.entity;

import lombok.Data;

/**
 * @author ShallowAn
 */
@Data
public class User {

    private String username;

    private String password;

    private String role;

    private String permission;

}
