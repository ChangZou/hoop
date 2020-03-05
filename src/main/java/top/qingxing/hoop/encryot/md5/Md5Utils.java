package top.qingxing.hoop.encryot.md5;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: hoop-preview
 * @Package: top.qingxing.hoop.encryot.md5
 * @ClassName: Md5Utils
 * @Author: sober
 * @Description:
 * @Date: 2020/2/28 下午10:10
 * @Version: 1.0
 */
public class Md5Utils {

    /**
     * MD5加密字符串
     *
     */
    public String getEncryot(String password, String salt) {
        byte[] hash;
        try {
            password = password + salt;
            //创建一个MD5算法对象，并获得MD5字节数组,16*8=128位
            hash = MessageDigest.getInstance("MD5").digest(password.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        //转换为十六进制字符串
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
    /**
     * 对输入的密码进行验证
     *
     */
    public boolean verify(String password, String salt, String key){
        // 把加密后的密文和原密文进行比较，相同则验证成功，否则失败
        return getEncryot(password,salt).equals(key);
    }


}
