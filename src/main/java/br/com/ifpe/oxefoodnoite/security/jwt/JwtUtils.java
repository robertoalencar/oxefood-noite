package br.com.ifpe.oxefoodnoite.security.jwt;

import java.util.Base64;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

    public String encode(String subject, String secret) {

	String secretKey = encodeSecret(secret);

	Date now = new Date();
	Date expirationDate = DateUtils.addDays(now, 1);

	return Jwts.builder().setClaims(Jwts.claims().setSubject(subject)).setIssuedAt(now)
		.setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public String decode(String secret, String token) {

	String secretKey = encodeSecret(secret);

	JwtParser parser = Jwts.parser().setSigningKey(secretKey);

	Jws<Claims> claims = parser.parseClaimsJws(token);

	return claims.getBody().getSubject();
    }

    private String encodeSecret(String secret) {

	return Base64.getEncoder().encodeToString(secret.getBytes());
    }
}