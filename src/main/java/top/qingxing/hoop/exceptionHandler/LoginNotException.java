package top.qingxing.hoop.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.awt.image.ImageProducer;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: hoop
 * @Package: top.qingxing.hoop.exceptionHandler
 * @ClassName: HoopResponseException
 * @Author: sober
 * @Description: 请求异常类
 * @Date: 2020/2/14 下午2:39
 * @Version: 1.0
 */
public class LoginNotException extends RuntimeException{
    private int status;
    private String message;

    public LoginNotException(ExceptionEnum exceptionEnum){
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
