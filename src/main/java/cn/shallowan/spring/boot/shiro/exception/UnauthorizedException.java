package cn.shallowan.spring.boot.shiro.exception;

/**
 * @author ShallowAn
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException() {
        super();
    }
}
