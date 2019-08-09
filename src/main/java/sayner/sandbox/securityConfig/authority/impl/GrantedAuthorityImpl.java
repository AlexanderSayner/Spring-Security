package sayner.sandbox.securityConfig.authority.impl;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authorities;

    public GrantedAuthorityImpl(String authorities) {
        this.authorities = authorities;
    }

    public GrantedAuthorityImpl() {
        this.authorities = "You are GODLiKE, be sure";
    }

    @Override
    public String getAuthority() {
        return this.authorities;
    }
}
