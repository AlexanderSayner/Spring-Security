package sayner.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import sayner.sandbox.dto.views.UserDtoView;
import sayner.sandbox.model.enums.StateEnum;

/**
 * In JSON form:
 * {
 * "userId": "new",
 * "login": "login",
 * "email": "email@e.mail",
 * "password": "password",
 * "username": "username",
 * "accountNonExpired": true,
 * "accountNonLocked": true,
 * "credentialsNonExpired": true,
 * "userRole": "userRole",
 * "userState": "userState"
 * }
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public final class UserDto {

    @JsonView(UserDtoView.Id.class)
    private String userId;

    @JsonView(UserDtoView.Login.class)
    private String login;

    @JsonView(UserDtoView.Email.class)
    private String email;

    @JsonView(UserDtoView.Password.class)
    private String password;

    @JsonView(UserDtoView.Username.class)
    private String username;

    @JsonView(UserDtoView.AccountNonExpired.class)
    private Boolean accountNonExpired;

    @JsonView(UserDtoView.AccountNonLocked.class)
    private Boolean accountNonLocked;

    @JsonView(UserDtoView.CredentialsNonExpired.class)
    private Boolean credentialsNonExpired;

    @JsonView(UserDtoView.UserRole.class)
    private String userRole;

    @JsonView(UserDtoView.UserState.class)
    private StateEnum userState;
}
