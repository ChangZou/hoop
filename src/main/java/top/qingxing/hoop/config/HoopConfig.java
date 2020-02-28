package top.qingxing.hoop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.qingxing.hoop.exceptionHandler.HoopExceptionHandler;
import top.qingxing.hoop.service.Impl.EncryotentServiceImpl;
import top.qingxing.hoop.service.Impl.LoginStateServiceImpl;

@Configuration
public class HoopConfig extends SettingConfig{

    //加密入口类
    @Bean
    public EncryotentServiceImpl encryotentServiceimpl(){
        return new EncryotentServiceImpl();
    }

    //全局异常
    @Bean
    public HoopExceptionHandler hoopExceptionHandler(){
        return  new HoopExceptionHandler();
    }

    //登录调用配置
    @Bean
    public LoginStateServiceImpl loginStateServiceimpl(){
        return new LoginStateServiceImpl();
    }



}
