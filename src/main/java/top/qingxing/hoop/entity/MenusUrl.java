package top.qingxing.hoop.entity;


/**
 * @ProjectName: hoop
 * @Package: top.qingxing.hoop.entity
 * @ClassName: MenusUrl
 * @Author: sober
 * @Description: 权限列表实体类
 * @Date: 2020/2/13 下午6:12
 * @Version: 1.0
 */
public class MenusUrl {
    private String url;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MenusUrl{" +
                "url='" + url + '\'' +
                '}';
    }
}
