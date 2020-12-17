package com.example.demo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Performs any conversion logic of your Jwt into Spring Authorities (for example, Cognito groups)
 */
public class GrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String COGNITO_GROUPS = "cognito:groups";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<String> groups = (Collection<String>) jwt.getClaims().get(COGNITO_GROUPS);

        return Optional.ofNullable(groups)
                .orElse(Collections.emptyList())
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
