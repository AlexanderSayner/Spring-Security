package sayner.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import sayner.sandbox.dto.views.UserDtoView;

@Data
public final class UserDto {

    @JsonView(UserDtoView.Id.class)
    private String userId;

    @JsonView(UserDtoView.Name.class)
    private String name;

    @JsonView(UserDtoView.Address.class)
    private String address;

    @JsonView(UserDtoView.Email.class)
    private String email;
}
