package cz.honestcity.endpoints.configuration.authorization;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author michal.keder
 */
@Configuration
@ConfigurationProperties(prefix = "jwt", ignoreUnknownFields = false)
public class JwtProperties {

    private String secret;
    private String issuer;
    private long expirationAtMilliseconds;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpirationAtMilliseconds() {
        return expirationAtMilliseconds;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setExpirationAtMilliseconds(long expirationAtMilliseconds) {
        this.expirationAtMilliseconds = expirationAtMilliseconds;
    }
}
