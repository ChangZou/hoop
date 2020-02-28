package top.qingxing.hoop.aop.interceptor;

import java.lang.annotation.*;

/**
 * @ProjectName: hoop
 * @Package: top.qingxing.hoop.aop
 * @ClassName: Hoop
 * @Author: sober
 * @Description:
 * @Date: 2020/2/6 下午10:28
 * @Version: 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Hoop {
}
