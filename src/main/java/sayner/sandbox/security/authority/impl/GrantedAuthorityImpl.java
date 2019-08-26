package sayner.sandbox.security.authority.impl;

import lombok.Setter;
import org.springframework.stereotype.Component;
import sayner.sandbox.model.enums.RoleEnum;
import sayner.sandbox.security.authority.CustomGrantedAuthority;

@Component
public class GrantedAuthorityImpl implements CustomGrantedAuthority {

    @Setter
    private String authorities;

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
    public String setAuthority(RoleEnum role) {
        return this.authorities = role.name();
    }

    @Override
    public String getAuthority() {
        return this.authorities;
    }
}
