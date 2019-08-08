package sayner.sandbox.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sayner.sandbox.model.CurrentUser;
import sayner.sandbox.model.User;
import sayner.sandbox.services.UserService;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Service
@Log4j2
public class CurrentUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userService.getUserByHisName(username);
        return new CurrentUser(user);
    }
}
