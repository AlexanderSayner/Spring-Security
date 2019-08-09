package sayner.sandbox.security.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import sayner.sandbox.model.Token;
import sayner.sandbox.model.User;
import sayner.sandbox.repositories.TokenRepository;
import sayner.sandbox.security.token.TokenAuthentication;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final TokenRepository tokenRepository;
    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        TokenAuthentication tokenAuthentication=(TokenAuthentication)authentication;

        Optional<Token> tokenCandidate=this.tokenRepository.findOneByValue(tokenAuthentication.getName());

        if(tokenCandidate.isPresent()){
            // Идёт обращение к другой таблице
            UserDetails userDetails=this.userDetailsService.loadUserByUsername(tokenCandidate.get().getUser().getLogin());
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        }else {
            throw new IllegalArgumentException("Не найден пользователь по токену");
        }
    }

    /**
     * Список классов, которые поддерживает Token Authentication Provider,
     * и теперь он будет ловить только token.TokenAuthentication
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
