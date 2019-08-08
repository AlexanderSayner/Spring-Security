package sayner.sandbox.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sayner.sandbox.model.CurrentUser;
import sayner.sandbox.model.User;
import sayner.sandbox.services.CurrentUserDetailsService;
import sayner.sandbox.services.UserService;

import java.util.List;

/**
 * Нужен, чтобы разделить функциональсть сервисов JPA и Spring Security
 */
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Service
@Log4j2
public class CurrentUserDetailsServiceImpl implements CurrentUserDetailsService {

    protected final UserService userService;

    @Override
    public List<User> getAllUsers() throws NullPointerException {
        return this.userService.getAllUsers();
    }

    @Override
    public User getOnlyOneUser(String id) throws NullPointerException {
        return this.userService.getOnlyOneUser(id);
    }

    @Override
    public User getUserByHisName(String name) throws NullPointerException {
        return this.userService.getUserByHisName(name);
    }

    @Override
    public String getMyRoles(String username) throws NullPointerException {
        return this.userService.getMyRoles(username);
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userService.getUserByHisName(username);
        return new CurrentUser(user);
    }
}
