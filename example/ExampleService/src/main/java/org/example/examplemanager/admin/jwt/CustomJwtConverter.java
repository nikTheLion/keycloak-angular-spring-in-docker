package org.example.examplemanager.admin.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomJwtConverter implements Converter<Jwt, CustomJwt> {
    Logger logger = LoggerFactory.getLogger(CustomJwtConverter.class);
    @Override
    public CustomJwt convert(Jwt source) {
        logger.info("Got something!");
        logger.info(source.toString());
        List<GrantedAuthority> grantedAuthorities = extractAuthorities(source);
        CustomJwt customJwt = new CustomJwt(source, grantedAuthorities);
        customJwt.setFirstname(source.getClaimAsString("given_name"));
        customJwt.setLastname(source.getClaimAsString("family_name"));
        System.out.println(customJwt);
        return customJwt;
    }

    private List<GrantedAuthority> extractAuthorities(Jwt source) {
        List<GrantedAuthority> result = new ArrayList<>();

        Map<String, Object> realmAccess = source.getClaimAsMap("realm_access");
        if (realmAccess != null && realmAccess.get("roles") != null) {
            Object roles = realmAccess.get("roles");
            if (roles instanceof List) {
                ((List<?>) roles).forEach(role -> result.add(new SimpleGrantedAuthority("ROLE_" + role)));
            }
        }
        return result;

    }
}
