package sayner.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import sayner.sandbox.dto.views.UserDtoView;
import sayner.sandbox.model.enums.RoleEnum;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public final class UserDto {

    @JsonView(UserDtoView.Id.class)
    private String userId;

    @JsonView(UserDtoView.Name.class)
    private String name;

    @JsonView(UserDtoView.Address.class)
    private String address;

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

    @JsonView(UserDtoView.Enabled.class)
    private Boolean enabled;

    @JsonView(UserDtoView.UserRoles.class)
    private List<RoleEnum> userRoles;
}
