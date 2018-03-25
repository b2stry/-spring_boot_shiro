package cn.shallowan.spring.boot.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ShallowAn
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
