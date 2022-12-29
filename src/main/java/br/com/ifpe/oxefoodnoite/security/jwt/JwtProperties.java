package br.com.ifpe.oxefoodnoite.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    private String secretKey = "secret";

    // validity in milliseconds
    private long validityInMs = 3600000; // 1h

    // validity in milliseconds
    private long longLiveValidityInMs = 604800000; // 1 semana

}
