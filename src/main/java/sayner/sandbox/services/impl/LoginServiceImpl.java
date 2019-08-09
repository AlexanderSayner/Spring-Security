package sayner.sandbox.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sayner.sandbox.dto.TokenDto;
import sayner.sandbox.dto.mappers.TokenMapper;
import sayner.sandbox.model.Token;
import sayner.sandbox.model.User;
import sayner.sandbox.dto.forms.LoginForm;
import sayner.sandbox.repositories.TokenRepository;
import sayner.sandbox.repositories.UserRepository;
import sayner.sandbox.services.LoginService;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Service
public class LoginServiceImpl implements LoginService {

    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenMapper tokenMapper = TokenMapper.INSTANCE;

    @Override
    public TokenDto login(LoginForm loginForm) throws NullPointerException {

        Optional<User> userCandidate = this.userRepository.findOneByLogin(loginForm.getLogin());

        User user = userCandidate.orElseThrow(NullPointerException::new);

        if (passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())) {

            Token token = Token.builder()
                    .user(user)
                    .value(RandomStringUtils.random(10, true, true))
                    .build();

            return this.tokenMapper.toTokenDto(tokenRepository.save(token));
        }

        throw new IllegalArgumentException("User not found");
    }
}
