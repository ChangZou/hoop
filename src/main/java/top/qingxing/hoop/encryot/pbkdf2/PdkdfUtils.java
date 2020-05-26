package top.qingxing.hoop.encryot.pbkdf2;


import top.qingxing.hoop.entity.SettingEnum;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: hoop
 * @Package: top.qingxing.hoop.Utils
 * @ClassName: EncryptUtil
 * @Author: sober
 * @Description: 密码加密
 * @Date: 2020/2/11 下午5:43
 * @Version: 1.0
 */
public class PdkdfUtils {

    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    /**
     * 对输入的密码进行验证
     */
    public boolean verify(String password, String salt, String key)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 用相同的盐值对用户输入的密码进行加密
        // 把加密后的密文和原密文进行比较，相同则验证成功，否则失败
        return getEncryot(password, salt).equals(key);
    }

    /**
     * 根据password和salt生成密文
     */
    public String getEncryot(String password, String salt) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        Map<String, String> map = new HashMap<>();
        //将16进制字符串形式的salt转换成byte数组
        byte[] bytes = DatatypeConverter.parseHexBinary(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), bytes, (int) SettingEnum.ITERATIONS.getValue(),
                (int) SettingEnum.HASHSIZE.getValue() * 4);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();
        //将byte数组转换为16进制的字符串
        return DatatypeConverter.printHexBinary(hash);
    }


    /**
     * 生成随机盐值
     */
    public String getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] bytes = new byte[(int) SettingEnum.SALESIZE.getValue() / 2];
        random.nextBytes(bytes);
        //将byte数组转换为16进制的字符串
        String salt = DatatypeConverter.printHexBinary(bytes);
        return salt;
    }

}
