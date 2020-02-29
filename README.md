# Hoop

<p align="center">
<a href="#"><img alt="JDK" src="https://img.shields.io/badge/JDK-1.8-yellow.svg?style=flat-square"/></a>
<a href="https://github.com/ChangZou/hoop"><img alt="Star" src="https://img.shields.io/github/stars/ChangZou/hoop?style=flat-square"></a>
<a href="https://github.com/ChangZou/hoop"><img alt="GitHub last commit" src="https://img.shields.io/github/license/ChangZou/hoop?style=flat-square"></a>
</p>

------

## 它是什么
Hoop是一个基于Spring boot的 controller 层权限拦截框架
## 特点
- 基于url的aop拦截
- 集成单点登录
- token验证
- 提供多种密码加密方式
## 快速使用
- 导入pom
```text
    <dependency>
        <groupId>top.qingxing</groupId>
        <artifactId>hoop</artifactId>
        <version>1.0.0</version>
    </dependency>
```
- 编写yml
```yaml
hoop:                     #hoop配置组---必要
  encrypt: PBKDF2         #加密方式(PBKDF2/MD5)---必要
  pbkdf:                  #只有采用PBKDF2加密才书写此配置组
    salesize: 8           #随机盐的长度
    hashsize: 32          #生成的密文长度
    iterations: 1000      #加密迭代次数越大越好，当然也会影响性能这里建议1000
  token:                  #token配置组---必要
    secret: ks585qw5x46   #加密密文 (建议书写你的域名如top.qingxing.www，当然这也不是必须的，你可以自定义)---必要
    issuer: hyrj          #发行人（及用户名，当然这也不是必须的，你可以自定义）---必要
    extime: 240           #有效期 (单位：小时，要注意 X * 3600000 < 2147483647)---必要
    domain: localhost     #Cookie的domin (例如你需要www.qingxing.top和blog.qingxing.top共享登录状态则需要填写主域qingxing.top)---必要
```
- 编写配置类
```java
@Configuration
public class HoopCpnfig extends HoopConfig {

    //AOP配置(书写此bean则代表启动权限拦截)
    @Bean
    public HoopLjAop hoopLjAop(){
        return new HoopLjAop();
    }

}
```
- 在你的登录方法中添加
```java
//你可以通过@Autowired声明EncryotentService和LoginStateService并使用
//password为用户输入的密码，sale为盐，key为用户表中获取的加密后的密码
if (encryotentService.verify(password,sale,key)){
    Map<String,String> map = new HashMap<>();
    map.put("id",roUser.getId()+"");
    loginStateService.loginSuccess(map);
    return new JsonResult(200,"登录成功!");
}
```
- 在你的注册方法中进行进行加密
```java
//注意这里使用的是PBKDF2加密，这里获取的salt是需要保存到用户表或其他地方的，当然你也可以将salt藏匿与密码中，验证时在分割密码提取出salt和原本的密文
//如MD5则不需生成盐，只需给定一个全局固定的salt
//生成盐
String salt = encryotentService.getSalt();
//获取加密后的密文
String key = encryotentService.getEncryot(password,salt));
```
- 在你的注销方法中使用`loginStateService.loginOut()`
- 编写`implements``JudgeAuthorityService`获取用户权限列表的Service
```java
@Service
public class JudgeAuthorityServiceImpl implements JudgeAuthorityService {
    @Autowired
    RoUserMapper roUserMapper;

    //这里注意返回类型必须是List<MenusUrl>
    //MenusUrl实体类中具有 String url 参数，及你的接口url如 /api/user/all
    //并且需要参数userid，及用户的唯一辨识id
    @Override
    public List<MenusUrl> getmenurights(int userid) {
        return roUserMapper.getmenurights(userid);
    }

}
```
- 将注解配置与类或方法之上
```java
@Hoop
@RestController
@RequestMapping("api/menu")
public class RoMenuPermissionController {
}
```
或是
```java
@Hoop
@PostMapping("/hoop")
public JsonResult test(){
    return new JsonResult(500);
}
```