package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.service.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtServiceImpl implements JwtService {
    private final JwtEncoder jwtEncoder;

    public JwtServiceImpl(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public String generateToken(Authentication authentication) {
        String email = authentication.getName();  // Assuming the authentication name is the email
        return generateTokenWithClaims(email);
    }

    @Override
    public String generateTokenForUser(DBUser user) {
        return generateTokenWithClaims(user.getEmail());
    }

    private String generateTokenWithClaims(String email) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(email)
                .claim("email", email)
                .build();

        JwtEncoderParameters params = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);

        try {
            return this.jwtEncoder.encode(params).getTokenValue();
        } catch (Exception e) {
            throw new RuntimeException("Token generation failed", e);
        }
    }
}
