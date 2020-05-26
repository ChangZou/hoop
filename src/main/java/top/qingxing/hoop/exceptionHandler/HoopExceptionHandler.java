package top.qingxing.hoop.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: hoop
 * @Package: top.qingxing.hoop.exceptionHandler
 * @ClassName: HoopExceptionHandler
 * @Author: sober
 * @Description: HOOP全局异常处理类
 * @Date: 2020/2/14 下午3:05
 * @Version: 1.0
 */
@ControllerAdvice
public class HoopExceptionHandler {

    @ExceptionHandler(LoginNotException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String, Object> LoginNotException(LoginNotException loginnot) {
        Map<String, Object> errorResultMap = new HashMap<>();
        errorResultMap.put("status", loginnot.getStatus());
        errorResultMap.put("message", loginnot.getMessage());
        return errorResultMap;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> AccessDeniedException(AccessDeniedException acess) {
        Map<String, Object> errorResultMap = new HashMap<>();
        errorResultMap.put("status", acess.getStatus());
        errorResultMap.put("message", acess.getMessage());
        return errorResultMap;
    }
}
