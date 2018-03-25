package cn.shallowan.spring.boot.shiro.service;

import cn.shallowan.spring.boot.shiro.dao.DataSource;
import cn.shallowan.spring.boot.shiro.entity.User;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ShallowAn
 */
@Service
public class UserService {

    public User getUser(String username) {
        //用户不存在直接返回null
        if (!DataSource.getData().containsKey(username)) {
            return null;
        }

        User user = new User();
        Map<String, String> detail = DataSource.getData().get(username);

        user.setUsername(username);
        user.setPassword(detail.get("password"));
        user.setRole(detail.get("role"));
        user.setPermission(detail.get("permission"));
        return user;
    }
}
