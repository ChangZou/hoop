package top.qingxing.hoop.service;

import top.qingxing.hoop.entity.MenusUrl;

import java.util.List;

/**
 * @ProjectName: hoop
 * @Package: top.qingxing.hoop.service
 * @ClassName: JudgeAuthority
 * @Author: sober
 * @Description: 判断权限接口
 * @Date: 2020/2/13 下午3:20
 * @Version: 1.0
 */
public interface JudgeAuthorityService {

    //获得当前用户可用的权限
    List<MenusUrl> getmenurights(int userid);

}
