package top.qingxing.hoop.service.Impl;

import top.qingxing.hoop.encryot.pbkdf2.PdkdfUtils;
import top.qingxing.hoop.encryot.token.TokenUtils;
import top.qingxing.hoop.entity.EncryptEnum;
import top.qingxing.hoop.entity.SettingEnum;
import top.qingxing.hoop.service.EncryotentService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

/**
 * @ProjectName: hoop
 * @Package: top.qingxing.hoop.encryot
 * @ClassName: Encryotent
 * @Author: sober
 * @Description: 密码加密Impl
 * @Date: 2020/2/12 下午10:42
 * @Version: 1.0
 */
public class EncryotentServiceImpl implements EncryotentService {

    /**
     *
     * 对输入的密码进行验证
     *
     */
    @Override
    public boolean verify(String password, String salt, String key)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        return EncryptEnum.valueOf((String) SettingEnum.ENCRYPT.getValue())
                .verify(password,salt,key);
    }

    /**
     *
     * 根据password和salt生成密文
     *
     */
    @Override
    public String getEncryot(String password, String salt) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        return EncryptEnum.valueOf((String) SettingEnum.ENCRYPT.getValue())
                .encrypt(password,salt);
    }

    @Override
    public String getSalt() throws Exception {
        return new PdkdfUtils().getSalt();
    }


    /**
     * token
     * 生成token
     */
    @Override
    public String createToken(Map<String, String> claims) throws Exception {
        return new TokenUtils().createToken(claims);
    }


    /**
     * token
     * 验证jwt，并返回数据
     */
    @Override
    public Map<String, String> verifyToken(String token) throws Exception {
        return new TokenUtils().verifyToken(token);
    }
}
