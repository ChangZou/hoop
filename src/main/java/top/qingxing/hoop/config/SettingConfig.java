package top.qingxing.hoop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.qingxing.hoop.entity.SettingEnum;

/**
 * @ProjectName: hoop-preview
 * @Package: top.qingxing.hoop.config
 * @ClassName: SettingConfig
 * @Author: sober
 * @Description:
 * @Date: 2020/2/29 上午2:01
 * @Version: 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "hoop")
public class SettingConfig {
    private final Pbkdf pbkdf = new Pbkdf();
    private final Token token = new Token();
    private String encrypt = "";

    public static class Pbkdf {
        private int salesize = 0;
        private int hashsize = 0;
        private int iterations = 0;

        public int getSalesize() {
            return salesize;
        }

        public void setSalesize(int salesize) {
            this.salesize = salesize;
        }

        public int getHashsize() {
            return hashsize;
        }

        public void setHashsize(int hashsize) {
            this.hashsize = hashsize;
        }

        public int getIterations() {
            return iterations;
        }

        public void setIterations(int iterations) {
            this.iterations = iterations;
        }
    }

    public static class Token {

        private String cookiename = "uhooptoken";
        private String secret = "";
        private String issuer = "";
        private int extime = 0;
        private String domain = "";

        public String getCookiename() {
            return cookiename;
        }

        public void setCookiename(String cookiename) {
            this.cookiename = cookiename;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getIssuer() {
            return issuer;
        }

        public void setIssuer(String issuer) {
            this.issuer = issuer;
        }

        public int getExtime() {
            return extime;
        }

        public void setExtime(int extime) {
            this.extime = extime;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }
    }

    public Pbkdf getPbkdf() {
        return pbkdf;
    }

    public Token getToken() {
        return token;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    //基础设置
    @Bean
    public void hoopSetting() {
        SettingEnum.ENCRYPT.setValue(this.getEncrypt());
        SettingEnum.SALESIZE.setValue(this.pbkdf.getSalesize());
        SettingEnum.HASHSIZE.setValue(this.pbkdf.getHashsize());
        SettingEnum.ITERATIONS.setValue(this.pbkdf.getIterations());
        SettingEnum.COOKIENAME.setValue(this.token.getCookiename());
        SettingEnum.SECRET.setValue(this.token.getSecret());
        SettingEnum.ISSUER.setValue(this.token.getIssuer());
        SettingEnum.EXTIME.setValue(this.token.getExtime());
        SettingEnum.DOMAIN.setValue(this.token.getDomain());
    }
}
