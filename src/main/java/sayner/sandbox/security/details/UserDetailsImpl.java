package sayner.sandbox.security.details;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sayner.sandbox.model.User;
import sayner.sandbox.model.enums.RoleEnum;
import sayner.sandbox.model.enums.StateEnum;
import sayner.sandbox.security.authority.CustomGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

/**
 * Adapter between your model.User and Spring Security
 */
@Log4j2
public class UserDetailsImpl implements UserDetails {

    @Getter
    private User user;

    /**
     * Мне бы создать объект с @Autowired grantedAuthority,
     * но вот user'a Spring в бинах искать не должен
     */
    @Autowired
    private CustomGrantedAuthority grantedAuthority;

    /**
     * user'a в коде изменить будет уже нельзя.
     * А вот повторно инициализировать - запросто
     *
     * @param user
     */
    public UserDetailsImpl(final User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        RoleEnum userRole = this.user.getUserRole();
        this.grantedAuthority.setAuthority(userRole);
        return Collections.singletonList(this.grantedAuthority);
    }

    @Override
    public String getPassword() {
        return this.user.getHashPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getLogin();
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
        return this.user.getUserState().equals(StateEnum.ACTIVE);
    }
}
