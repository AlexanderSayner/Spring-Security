package sayner.sandbox.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sayner.sandbox.dto.SingleResponseObjectDto;
import sayner.sandbox.dto.ext.SingleResponseObjectDtoExt;
import sayner.sandbox.dto.mappers.UserMapper;
import sayner.sandbox.dto.status.enums.StatusEnum;
import sayner.sandbox.dto.views.SingleResponseObjectDtoView;
import sayner.sandbox.model.User;
import sayner.sandbox.services.UserService;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {

    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final UserService userService;

    @GetMapping
    @JsonView(SingleResponseObjectDtoView.FullWithUserFull.class)
    public SingleResponseObjectDto listUsers() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        SingleResponseObjectDtoExt<Object> singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                name,
                true,
                userMapper.toUserDTOs(this.userService.getAllUsers())
        );

        return singleResponseObjectDto;
    }

    @GetMapping(value = "/{id}")
    @JsonView(SingleResponseObjectDtoView.FullWithUserFull.class)
    public SingleResponseObjectDto listUser(@PathVariable(value = "id") String id) {

        SingleResponseObjectDtoExt<Object> singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                "listUser()",
                true,
                userMapper.toUserDto(this.userService.getOnlyOneUser(id))
        );

        return singleResponseObjectDto;
    }

    @GetMapping(value = "/role")
    @JsonView(SingleResponseObjectDtoView.Full.class)
    public SingleResponseObjectDto listRole() {

        SingleResponseObjectDtoExt<Object> singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                "listRole()",
                true,
                this.userService.getMyRoles("admin")
        );

        return singleResponseObjectDto;
    }
}
