package kr.codesquad.todolist.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    private final Key secretKey;

    public TokenProvider(@Value("${secret_key}") String secretKey) {
        byte[] bytes = secretKey.getBytes();
        this.secretKey = Keys.hmacShaKeyFor(bytes);
    }

    public String createToken(String userId) {
        long now = new Date().getTime();

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setSubject(userId)
                .setExpiration(new Date(now + 180000))
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                                    .setSigningKey(secretKey)
                                    .build()
                                    .parseClaimsJws(token);
            String subject = claimsJws.getBody().getSubject();
            log.info("subject : {}", subject);
        } catch (ExpiredJwtException expiredJwtException) {
            log.info("token is expired");
            return false;
        } catch (MalformedJwtException malformedJwtException) {
            log.info("token is Malformed");
            return false;
        } catch (SignatureException signatureException) {
            log.info("token is wrong signature");
            return false;
        }

        return true;
    }


}
