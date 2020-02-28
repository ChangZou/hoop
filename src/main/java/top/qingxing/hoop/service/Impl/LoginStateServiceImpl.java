package top.qingxing.hoop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.qingxing.hoop.entity.SettingEnum;
import top.qingxing.hoop.service.EncryotentService;
import top.qingxing.hoop.service.JudgeAuthorityService;
import top.qingxing.hoop.service.LoginStateService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ProjectName: hoop
 * @Package: top.qingxing.hoop.service.Impl
 * @ClassName: LoginStateServiceImpl
 * @Author: sober
 * @Description: 登录态impl
 * @Date: 2020/2/13 下午3:44
 * @Version: 1.0
 */
public class LoginStateServiceImpl implements LoginStateService {

    @Autowired
    EncryotentService encryotentService;

    @Autowired
    JudgeAuthorityService judgeAuthorityService;


    @Override
    public boolean loginSuccess (Map<String, String> map) throws Exception {
        /**
         * @Method: loginSuccess
         * @Author: sober
         * @Version:  1.0.0
         * @Description:
         * @param map
         * @Return: java.lang.String
         * @Exception: 前端登录审核通过申请token 将token返回并将角色信息存入redis id为key
         * @Date: 2020/2/13 下午3:54
         */
        try {
            //返回token
            String token = encryotentService.createToken(map);
            //生成cookie
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            Cookie cookie = new Cookie("uhooptoken",token);
            cookie.setMaxAge((int) SettingEnum.EXTIME.getValue() * 3600);
            cookie.setDomain((String)SettingEnum.DOMAIN.getValue());
            //返回新token
            response.addCookie(cookie);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean loginOut() throws Exception {
        try {
            //生成cookie
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            Cookie cookie = new Cookie("uhooptoken",null);
            cookie.setMaxAge(0);
            cookie.setDomain((String)SettingEnum.DOMAIN.getValue());
            //返回cookie
            response.addCookie(cookie);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
