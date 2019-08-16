package sayner.sandbox.services;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import sayner.sandbox.dto.UserDto;
import sayner.sandbox.model.User;

import java.util.List;

/**
 * Замечание.
 * Если в реализации сервиса вызывать один метод сервиса из другого метода этого сервиса,
 * то аннотации, указанные в интерфейсе не работают
 */
public interface UserService {

    String GODLiKE = "ROLE_GODLiKE";
    String A_MERE_MORTAL = "ROLE_A_MERE_MORTAL";

    @PreAuthorize("hasRole('" + GODLiKE + "') or hasRole('" + A_MERE_MORTAL + "')")
    List<User> getAllUsers() throws NullPointerException;

    @Secured({GODLiKE, A_MERE_MORTAL})
    User getOnlyOneUser(Long id) throws NullPointerException;

    @Secured({GODLiKE, A_MERE_MORTAL})
    User getUserByHisLogin(String login) throws NullPointerException;

    @Secured({GODLiKE, A_MERE_MORTAL})
    String getMyRolesFromSpringContex() throws NullPointerException;

    @PreAuthorize("hasRole('" + GODLiKE + "') or hasRole('" + A_MERE_MORTAL + "')")
    User signUp(UserDto userDto) throws IllegalArgumentException;

    /**
     * Вывести всех юзеров, кроме самого себя (админская штука)
     *
     * @param usernames
     * @return
     */
    @PreFilter("filterObject.login != authentication.principal.username")
    String joinUsernames(List<User> usernames);

    @PostFilter("filterObject.login != authentication.principal.username")
    List<User> getAllExceptPrincipalUser();
}
