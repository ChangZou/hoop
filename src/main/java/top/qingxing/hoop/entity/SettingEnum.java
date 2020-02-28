package top.qingxing.hoop.entity;

/**
 * @ProjectName: hoop-preview
 * @Package: top.qingxing.hoop.entity
 * @ClassName: SettingEnum
 * @Author: sober
 * @Description:
 * @Date: 2020/2/29 上午1:07
 * @Version: 1.0
 */
public enum SettingEnum {
    ENCRYPT(""),
    SALESIZE(0),
    HASHSIZE(0),
    ITERATIONS(0),
    SECRET(""),
    ISSUER(""),
    EXTIME(0),
    DOMAIN("");

    private Object value;

    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }

    SettingEnum(Object value) {
        this.value = value;
    }

}
