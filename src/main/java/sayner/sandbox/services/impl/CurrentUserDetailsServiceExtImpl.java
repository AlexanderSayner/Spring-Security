package sayner.sandbox.services.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sayner.sandbox.model.User;
import sayner.sandbox.services.UserService;

import java.util.List;

/**
 * CustomUserDetailsService расширяет UserDetailsService,
 * но нужен был сервис, который совместил бы в себе функциональность JPA UserService
 * и Spring Security UserDetailsService.
 * Поэтому данный класс наследует свойства обоих классов,
 * объединив в себе их функциональность
 */
@Service
@Log4j2
public class CurrentUserDetailsServiceExtImpl extends CustomUserDetailsServiceImpl implements UserService {

    @Autowired
    public CurrentUserDetailsServiceExtImpl(UserService userService) {
        super(userService);
    }

    @Override
    public List<User> getAllUsers() throws NullPointerException {
        return super.userService.getAllUsers();
    }

    @Override
    public User getOnlyOneUser(String id) throws NullPointerException {
        return super.userService.getOnlyOneUser(id);
    }

    @Override
    public User getUserByHisName(String name) throws NullPointerException {
        return super.userService.getUserByHisName(name);
    }

    @Override
    public String getMyRoles(String username) throws NullPointerException {
        return super.userService.getMyRoles(username);
    }
}
