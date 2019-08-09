package sayner.sandbox.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sayner.sandbox.model.User;
import sayner.sandbox.repositories.UserRepository;
import sayner.sandbox.security.details.UserDetailsImpl;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userCondidate=this.userRepository.findOneByLogin(username);
        return new UserDetailsImpl(userCondidate.orElseThrow(IllegalArgumentException::new));
    }
}
