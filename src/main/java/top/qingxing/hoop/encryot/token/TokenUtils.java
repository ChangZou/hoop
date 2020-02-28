package top.qingxing.hoop.encryot.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import top.qingxing.hoop.entity.SettingEnum;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: hoop
 * @Package: top.qingxing.hoop.tokenoperation.token
 * @ClassName: JwtUtils
 * @Author: sober
 * @Description: token类
 * @Date: 2020/2/12 下午11:44
 * @Version: 1.0
 */
public class TokenUtils {


    /**
     * 生成token
     *
     * @param claims
     * @return
     */
    public String createToken(Map<String, String> claims) {
        try {
            Algorithm algorithm = Algorithm.HMAC256((String) SettingEnum.SECRET.getValue());
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer((String)SettingEnum.ISSUER.getValue())
                    //设置过期时间
                    .withExpiresAt(new Date(System.currentTimeMillis() + (int)SettingEnum.EXTIME.getValue() * 3600 * 1000));
            claims.forEach(builder::withClaim);
            return builder.sign(algorithm);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * 验证jwt，并返回数据
     */
    public Map<String, String> verifyToken(String token) {
        Algorithm algorithm;
        Map<String, Claim> map;
        try {
            algorithm = Algorithm.HMAC256((String) SettingEnum.SECRET.getValue());
            JWTVerifier verifier = JWT.require(algorithm).withIssuer((String)SettingEnum.ISSUER.getValue()).build();
            DecodedJWT jwt = verifier.verify(token);
            map = jwt.getClaims();
        } catch (Exception e) {
            return null;
        }
        Map<String, String> resultMap = new HashMap<>(map.size());
        map.forEach((k, v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }

}
