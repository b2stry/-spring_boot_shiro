package cn.shallowan.spring.boot.shiro.response;

import lombok.Data;

/**
 * @author ShallowAn
 */
@Data
public class Response {

    /**
     * http状态码
     */

    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;

    public Response(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
