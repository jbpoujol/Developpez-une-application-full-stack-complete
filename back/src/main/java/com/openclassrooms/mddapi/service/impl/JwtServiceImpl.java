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
import java.util.logging.Logger;

@Service
public class JwtServiceImpl implements JwtService {
    private static final Logger logger = Logger.getLogger(JwtServiceImpl.class.getName());
    private final JwtEncoder jwtEncoder;

    public JwtServiceImpl(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public String generateToken(Authentication authentication) {
        return generateTokenWithClaims(authentication.getName());
    }

    @Override
    public String generateTokenForUser(DBUser user) {
        logger.info("Generating token for user: " + user.getEmail());
        return generateTokenWithClaims(user.getEmail());
    }

    private String generateTokenWithClaims(String subject) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(subject)
                .build();

        JwtEncoderParameters params = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        String token = this.jwtEncoder.encode(params).getTokenValue();
        logger.info("Generated token: " + token);
        return token;
    }
}
