package top.qingxing.hoop.entity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import top.qingxing.hoop.encryot.md5.Md5Utils;
import top.qingxing.hoop.encryot.pbkdf2.PdkdfUtils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

/**
 * @ProjectName: hoop
 * @Package: top.qingxing.hoop.entity
 * @ClassName: EncryptEnum
 * @Author: sober
 * @Description: 加密方式枚举类
 * @Date: 2020/2/11 下午6:40
 * @Version: 1.0
 */
public enum EncryptEnum {

    PBKDF2("PBKDF2") {
        public boolean verify(String password, String salt, String key) throws InvalidKeySpecException, NoSuchAlgorithmException {
            return new PdkdfUtils().verify(password, salt, key);
        }

        public String encrypt(String password, String salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
            return new PdkdfUtils().getEncryot(password, salt);
        }
    },
    MD5("MD5") {
        public boolean verify(String password, String salt, String key) throws InvalidKeySpecException, NoSuchAlgorithmException {
            return new Md5Utils().verify(password, salt, key);
        }

        public String encrypt(String password, String salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
            return new Md5Utils().getEncryot(password, salt);
        }
    };

    public abstract String encrypt(String password, String salt) throws InvalidKeySpecException, NoSuchAlgorithmException;

    public abstract boolean verify(String password, String salt, String key) throws InvalidKeySpecException, NoSuchAlgorithmException;

    private String value;

    public String getValue() {
        return value;
    }

    EncryptEnum(String value) {
        this.value = value;
    }
}
