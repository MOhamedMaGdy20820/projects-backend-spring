package com.example.springredditclone.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Value("${jwt.expiration.time}") // بيحدد وقت انتهاء صلاحيته ال token
    private Long jwtExpirationInMillis;


    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return generateTokenWithUserName(principal.getUsername());
    }

    public String generateTokenWithUserName(String username) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")                                              // ال token تم انشاءه ذاتيا
                .issuedAt(Instant.now())                                     // بيحدد الوقت اللي تم اصدار فيه ال token
                .expiresAt(Instant.now().plusMillis(jwtExpirationInMillis))  // بيحدد وقت انتهاء صلاحيته
                .subject(username)                                           // الموضوع بنحط فيه المستخدم المخصص له ال token
                .claim("scope", "ROLE_USER")                     // تفاصيل إضافية مثل أدوار المستخدم.
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }

    public String validateJwt(String token) {
        String userName = jwtDecoder.decode(token).getSubject() ;
        return userName ;
    }
}