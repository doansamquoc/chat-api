package org.sam.chatapi.util;

import org.sam.chatapi.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Random;

import static org.sam.chatapi.constant.AuthConstant.AUTHORITY_PREFIX;

public class UserUtils {

    /**
     * Generate username by user's email
     *
     * @param email String
     * @return Unique username
     */
    public static String generateUsername(String email) {
        Random random = new Random();
        int randomInt = random.nextInt(9999);
        String name = email.split("@")[0];
        return String.format("%s%s", name, randomInt);
    }

    /**
     * Enrich user role
     *
     * @param role Role
     * @return GrantedAuthority
     */
    public static GrantedAuthority toAuthority(String role) {
        return new SimpleGrantedAuthority(AUTHORITY_PREFIX + role);
    }

    /**
     * Only one role in authorities, just use findFirst to get role
     *
     * @param authorities GrantedAuthority Collection
     * @return Role
     */
    public static Role toRole(Collection<? extends GrantedAuthority> authorities) {
        String authority = authorities.stream().findFirst().map(GrantedAuthority::getAuthority).orElse(null);
        if (authority == null) return null;
        String roleName = authority.replace(AUTHORITY_PREFIX, "").trim();
        return Role.valueOf(roleName);
    }
}