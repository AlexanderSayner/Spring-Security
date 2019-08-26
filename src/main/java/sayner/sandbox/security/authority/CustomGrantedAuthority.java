package sayner.sandbox.security.authority;

import org.springframework.security.core.GrantedAuthority;
import sayner.sandbox.model.enums.RoleEnum;

public interface CustomGrantedAuthority extends GrantedAuthority {

    String setAuthority(RoleEnum role);
}
