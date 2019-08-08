package sayner.sandbox.services;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import sayner.sandbox.model.User;

import java.util.List;

public interface UserService {

    String GODLiKE = "ROLE_GODLiKE";

    //    @Secured({GODLiKE})
    @PreAuthorize("hasRole('" + GODLiKE + "')")
    List<User> getAllUsers() throws NullPointerException;

    @Secured({GODLiKE})
    User getOnlyOneUser(String id) throws NullPointerException;

    @Secured({GODLiKE})
    User getUserByHisName(String name) throws NullPointerException;

    @PreAuthorize("#username == authentication.principal.username")
    String getMyRoles(String username) throws NullPointerException;
}
