package top.qingxing.hoop.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

/**
 * @ProjectName: hoop
 * @Package: top.qingxing.hoop.service
 * @ClassName: EncryotentService
 * @Author: sober
 * @Description: 加密接口
 * @Date: 2020/2/13 下午3:26
 * @Version: 1.0
 */
public interface EncryotentService {
    /**
     * pdkdf
     * 对输入的密码进行验证
     *
     */
    public boolean verify(String password, String salt, String key)
            throws NoSuchAlgorithmException, InvalidKeySpecException;

    /**
     * pdkdf
     * 根据password和salt生成密文
     *
     */
    public String getEncryot(String password, String salt) throws NoSuchAlgorithmException,
            InvalidKeySpecException;

    /**
     * 随机盐
     */
    public String getSalt() throws Exception;

    /**
     * token
     * 生成token
     */
    public String createToken(Map<String, String> claims) throws Exception;


    /**
     * token
     * 验证jwt，并返回数据
     */
    public Map<String, String> verifyToken(String token) throws Exception;
}
