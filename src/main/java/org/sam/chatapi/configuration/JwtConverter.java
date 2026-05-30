package org.sam.chatapi.configuration;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;

import static org.sam.chatapi.constant.AuthConstant.AUTHORITY_CLAIM_NAME;
import static org.sam.chatapi.constant.AuthConstant.AUTHORITY_PREFIX;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    JwtGrantedAuthoritiesConverter converter;

    public JwtConverter() {
        this.converter = new JwtGrantedAuthoritiesConverter();
        this.converter.setAuthoritiesClaimName(AUTHORITY_CLAIM_NAME);
        this.converter.setAuthorityPrefix(AUTHORITY_PREFIX);
    }

    @Override
    public @Nullable AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> authorities = this.converter.convert(jwt);
        return new JwtAuthenticationToken(jwt, authorities);
    }
}
