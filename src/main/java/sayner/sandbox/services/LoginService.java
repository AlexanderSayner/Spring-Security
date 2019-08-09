package sayner.sandbox.services;

import sayner.sandbox.dto.TokenDto;
import sayner.sandbox.dto.forms.LoginForm;

public interface LoginService {

    TokenDto login(LoginForm loginForm)throws NullPointerException;
}
