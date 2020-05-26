package top.qingxing.hoop.service;

import java.util.Map;

/**
 * @ProjectName: hoop
 * @Package: top.qingxing.hoop.service
 * @ClassName: LoginStateService
 * @Author: sober
 * @Description: 登录态接口类
 * @Date: 2020/2/13 下午3:44
 * @Version: 1.0
 */
public interface LoginStateService {

    //前端登录审核通过申请token 将token返回并将角色信息存入redis id为key
    boolean loginSuccess(Map<String, String> map) throws Exception;

    //前端登录审核通过申请token 将token返回并将角色信息存入redis id为key
    boolean loginOut() throws Exception;
}
