package cz.honestcity.endpoints.configuration.authorization;

import io.jsonwebtoken.JwtParser;
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

    private final JwtParser parser;

    public JwtTokenService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.parser = buildParser(jwtProperties.getSecret());
    }

    public String getToken(String userId) {
        Date now = new Date();
        return Jwts.builder()
                .setIssuer("Honest City")
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.getExpirationAtMilliseconds()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    public String getUserId(String token) {
        return parser.parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            return parser.parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .before(new Date());
        }catch (Exception exception){
            return false;
        }
    }

    private JwtParser buildParser(String secret){
        return Jwts.parser()
                .setSigningKey(secret);
    }
}
