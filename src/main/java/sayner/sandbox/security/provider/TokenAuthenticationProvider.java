package sayner.sandbox.security.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import sayner.sandbox.model.Token;
import sayner.sandbox.repositories.TokenRepository;
import sayner.sandbox.security.token.TokenAuthentication;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Component
@Log4j2
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final TokenRepository tokenRepository;
    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        /**
         * TokenAuthentication содержит в себе само значение токена,
         * пользователя и информацию о том, аутентифицирован ли он
         * Другой тип Authentication попасть не может (см this.supports(...))
         */
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;

        // getName() возвращает токен
        Optional<Token> tokenCandidate = this.tokenRepository.findOneByValue(tokenAuthentication.getName());

        if (tokenCandidate.isPresent()) {
            // Идёт обращение к другой таблице, но можно вытащить из токена
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(tokenCandidate.get().getUser().getLogin());
            // Раз уж пользователь найден, аутентифицирую его
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        } else {
            // Сообщаю, что не нашли по токену
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
