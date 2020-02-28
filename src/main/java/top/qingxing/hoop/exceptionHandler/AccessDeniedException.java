package top.qingxing.hoop.exceptionHandler;

/**
 * @ProjectName: hoop
 * @Package: top.qingxing.hoop.exceptionHandler
 * @ClassName: AccessDeniedException
 * @Author: sober
 * @Description:
 * @Date: 2020/2/14 下午2:47
 * @Version: 1.0
 */
public class AccessDeniedException extends RuntimeException {
    private int status;
    private String message;

    public AccessDeniedException(ExceptionEnum exceptionEnum){
        this.status = exceptionEnum.getStatus();
        this.message = exceptionEnum.getMessage();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
