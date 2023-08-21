package com.example.basic.config;

import com.example.basic.dto.common.TokenInfoDto;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Log4j2
public class JwtTokenProvider {

    @Value(value = "${app.jwtSecret}")
    private String jwtSecret; // 암호화 키

    @Value(value = "${app.jwtAccessExpirationInMs}")
    private int jwtAccessExpirationInMs; // Access Token 만료일

    @Value(value = "${app.jwtRefreshExpirationInMs}")
    private int jwtRefreshExpirationInMs; // Refresh Toekn 만료일

    public TokenInfoDto generateToken(Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String authorities = customUserDetails.getAuthorities().stream()
          .map(GrantedAuthority::getAuthority)
          .collect(Collectors.joining(","));
        Date now = new Date();
        Date accessTokenExpireTime = new Date(now.getTime() + jwtAccessExpirationInMs);
        Date refreshTokenExpireTime = new Date(now.getTime() + jwtRefreshExpirationInMs);

        String accessToken = Jwts.builder()
          .setSubject(customUserDetails.getIdx().toString())
          .claim("auth", authorities)
          .setIssuedAt(new Date())
          .setExpiration(accessTokenExpireTime)
          .signWith(SignatureAlgorithm.HS512, jwtSecret)
          .compact();

        String refreshToken = Jwts.builder()
          .setSubject(customUserDetails.getIdx().toString())
          .claim("auth", authorities)
          .setIssuedAt(new Date())
          .setExpiration(refreshTokenExpireTime)
          .signWith(SignatureAlgorithm.HS512, jwtSecret)
          .compact();

        return TokenInfoDto.builder()
          .accessToken(accessToken)
          .refreshToken(refreshToken)
          .build();
    }

    private Claims getClaimsFormToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    public String getSubject(String token) {
        return getClaimsFormToken(token).getSubject();
    }

    public Long getUserIdxFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return Long.valueOf(claims.getSubject());
    }

    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
          Arrays.stream(claims.get("auth").toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public boolean validateToken(String authToken) throws IOException {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
            throw new JwtException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
            throw new JwtException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
            throw new JwtException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
            throw new JwtException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty");
            throw new JwtException("JWT claims string is empty");
        }
    }
}
