package sayner.sandbox.model;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sayner.sandbox.model.enums.RoleEnum;
import sayner.sandbox.model.enums.StateEnum;
import sayner.sandbox.securityConfig.authority.impl.GrantedAuthorityImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Log4j2
public class CurrentUser implements UserDetails {

    @Getter
    private User user;

    public CurrentUser(final User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        final Set<GrantedAuthority> _grntdAuths = new HashSet<GrantedAuthority>();

        RoleEnum _role = null;

        if (user != null) {
            _role = user.getUserRole();
        }

        if (_role != null) {
            _grntdAuths.add(new GrantedAuthorityImpl(_role.name()));
        }

        return _grntdAuths;
    }

    @Override
    public String getPassword() {
        return user.getHashPassword();
    }

    @Override
    public String getUsername() {
        if (this.user == null) {
            return null;
        }
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {

        if (this.user.getUserState() == StateEnum.ACTIVE) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "CustomUserDetails [user=" + user.toString() + "]";
    }
}
