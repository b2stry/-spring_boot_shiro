package cn.shallowan.spring.boot.shiro.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShallowAn
 */
public class DataSource {
    private static Map<String, Map<String, String>> data = new HashMap<>();

    static {
        Map<String, String> shallowan = new HashMap<>();
        shallowan.put("password", "shallowan");
        shallowan.put("role", "user");
        shallowan.put("permission", "view");

        Map<String, String> bestry = new HashMap<>();
        bestry.put("password", "bestry");
        bestry.put("role", "admin");
        bestry.put("permission", "view,edit");

        data.put("shallowan", shallowan);
        data.put("bestry", bestry);
    }

    public static Map<String, Map<String, String>> getData() {
        return data;
    }
}
