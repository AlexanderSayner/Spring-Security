package sayner.sandbox.services;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import sayner.sandbox.dto.UserDto;
import sayner.sandbox.model.User;

import java.util.List;

public interface UserService {

    String GODLiKE = "ROLE_GODLiKE";
    String A_MERE_MORTAL = "ROLE_A_MERE_MORTAL";

    @PreAuthorize("hasRole('" + GODLiKE + "')  or hasRole('" + A_MERE_MORTAL + "')")
    List<User> getAllUsers() throws NullPointerException;

    @Secured({GODLiKE, A_MERE_MORTAL})
    User getOnlyOneUser(String id) throws NullPointerException;

    @Secured({GODLiKE, A_MERE_MORTAL})
    User getUserByHisName(String name) throws NullPointerException;

    @PreAuthorize("#login == authentication.principal.username")
    String getMyRoles(String login) throws NullPointerException;

    User signUp(UserDto userDto) throws IllegalArgumentException;
}
