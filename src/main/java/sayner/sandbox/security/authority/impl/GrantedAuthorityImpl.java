package sayner.sandbox.security.authority.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import sayner.sandbox.model.enums.RoleEnum;

@Component
public class GrantedAuthorityImpl implements GrantedAuthority {

    private final String authorities;

    public GrantedAuthorityImpl(String authorities) {
        this.authorities = authorities;
    }

    public GrantedAuthorityImpl(RoleEnum role) {
        this.authorities = role.name();
    }

    public GrantedAuthorityImpl() {
        this.authorities = RoleEnum.ROLE_NULL.name();
    }

    @Override
    public String getAuthority() {
        return this.authorities;
    }
}
