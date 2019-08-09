package sayner.sandbox.security.token;

import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.util.Collection;

/**
 * Spring adapter for our token
 */
public class TokenAuthentication implements Authentication {

    private String token; // Теперь он будет понятен для Spring Security

    private Boolean isAuthenticated; // Есть ли пользователь в системе

    @Setter
    private UserDetails userDetails; // Информация о пользователе

    public TokenAuthentication(final String token) {
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userDetails.getAuthorities();
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return token;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
