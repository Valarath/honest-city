package cz.honestcity.endpoints.configuration.authorization;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.util.Date;

/**
 * @author michal.keder
 */
@Service
public class JwtTokenService {

    private final JwtProperties jwtProperties;

    public JwtTokenService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String getToken(String userId){
        Date now = new Date();
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+jwtProperties.getExpirationAtMilliseconds()))
                .signWith(SignatureAlgorithm.HS512,jwtProperties.getSecret())
                .compact();
    }

    public String getUserId(String token){
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
