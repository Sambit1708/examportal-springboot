package com.exam.portal.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

	// Base64 Decode => SambitExamPortalProjectForSpringBoot
    private static final String SECRET_KEY = "1da838f41e0b3159511271a488bfb0b608acd136f0dfefd8c4d8c5da6abc36fdb4be587f3450d6fcb3e14547a3948d71ea934d37a5164d21b5fc92a346bdab7bdac585aea607afd9b1910cc557a79f328b58022ee776bc94689067c6c7164980b3d2074b51a3de7094d3821a73fe2bde1c082370008d4ad6b2be34ee5ae5c10dbfb707ecf9d018eef477395ec9721ace600fafa8e21cd2c3a55a98519523011e4608efbeea163f9c9dbf1e0f52628b6dd2708cbfbc6f3ca4e3fae872d5523f51c29cd04e431151977a2886515b88d3863295809c10dafe8300ad8f8bb5b48cf00348a14973c1be5a18f75d0b56b17e48e7ddf6bb2d52f343d347e61c0ef10a6ba02d890a28c69f844582d3dd0099419ff3f65ec08b9fc84882f313bac65d96cbe46c1f8a2e2012e3849ee3dea5d54004eed9f329ae807374";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

	private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getKey() {
    	byte[] newKey = Decoders.BASE64.decode(SECRET_KEY);
    	return Keys.hmacShaKeyFor(newKey);
    }
    
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}