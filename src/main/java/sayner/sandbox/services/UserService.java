package sayner.sandbox.services;

import org.springframework.security.access.annotation.Secured;
import sayner.sandbox.model.User;

import java.util.List;

public interface UserService {

    @Secured({"ROLE_GODLiKE"})
    List<User> getAllUsers() throws NullPointerException;
}
