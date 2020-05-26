package top.qingxing.hoop.exceptionHandler;

/**
 * @ProjectName: hoop
 * @Package: top.qingxing.hoop.exceptionHandler
 * @ClassName: ExceptionEnum
 * @Author: sober
 * @Description: 异常枚举类
 * @Date: 2020/2/14 下午3:06
 * @Version: 1.0
 */
public enum ExceptionEnum {

    //未登录
    LOGIN_NOT(401, "用户认证失败！"),

    //无权访问
    ACCESS_DENIED(403, "权限认证失败!");

    private int status;

    private String message;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    ExceptionEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
