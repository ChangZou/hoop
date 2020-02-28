package top.qingxing.hoop.aop.interceptor;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.qingxing.hoop.entity.MenusUrl;
import top.qingxing.hoop.exceptionHandler.AccessDeniedException;
import top.qingxing.hoop.exceptionHandler.ExceptionEnum;
import top.qingxing.hoop.exceptionHandler.LoginNotException;
import top.qingxing.hoop.service.EncryotentService;
import top.qingxing.hoop.service.JudgeAuthorityService;
import top.qingxing.hoop.service.LoginStateService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Aspect
public class HoopLjAop {

    @Autowired
    EncryotentService encryotentService;

    @Autowired
    LoginStateService loginStateService;

    @Autowired
    JudgeAuthorityService judgeAuthorityService;

    @Pointcut("@within(top.qingxing.hoop.aop.interceptor.Hoop) || @annotation(top.qingxing.hoop.aop.interceptor.Hoop)")
    public void lj(){}

    @Around("lj()")
    public Object around(ProceedingJoinPoint pjp)throws Throwable{
        int status = 200;
        Object obj = null;
        try {
            //获取Request  Response
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //获取token
            String token  = null;
            Cookie[] cookies =  request.getCookies();
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("uhooptoken")){
                        token = cookie.getValue();
                        break;
                    }
                }
            }
            //token鉴权
            Map<String, String> ares = encryotentService.verifyToken(token);
            if (ares != null){
                //从sql中查找权限
                List<MenusUrl> menusUrls = judgeAuthorityService.getmenurights(Integer.parseInt(ares.get("id")));
                String userurl = JSON.toJSON(menusUrls).toString();
                //判断权限
                if(!userurl.contains("{\"url\":\""+request.getRequestURI()+"\"}")){
                    status = 403;
                }else {
                    obj = pjp.proceed();
                }
            }else {
                status = 401;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }finally {
            if (status == 403){
                throw new AccessDeniedException(ExceptionEnum.ACCESS_DENIED);
            }else if (status == 401){
                throw new LoginNotException(ExceptionEnum.LOGIN_NOT);
            }
            return obj;
        }
    }

}
