package lk.ijse.apigateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.ijse.apigateway.util.constant.SecurityConstants;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtValidation {

    public void validateJwt(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SecurityConstants.JWT_KEY));

        Claims body = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token.trim())
                .getBody();
    }
}
