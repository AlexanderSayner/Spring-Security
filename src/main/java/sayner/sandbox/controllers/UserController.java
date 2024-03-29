package sayner.sandbox.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sayner.sandbox.dto.SingleResponseObjectDto;
import sayner.sandbox.dto.UserDto;
import sayner.sandbox.dto.ext.SingleResponseObjectDtoExt;
import sayner.sandbox.dto.mappers.UserMapper;
import sayner.sandbox.dto.status.enums.StatusEnum;
import sayner.sandbox.dto.views.SingleResponseObjectDtoView;
import sayner.sandbox.model.enums.RoleEnum;
import sayner.sandbox.services.UserService;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {

    final String GODLiKE = RoleEnum.ROLE_GODLiKE.name();

    private final UserMapper userMapper = UserMapper.INSTANCE;

    private final UserService userService;

    @GetMapping
    @JsonView(SingleResponseObjectDtoView.FullWithUserFull.class)
    public SingleResponseObjectDto getUsersList() {

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

    @PostMapping
    @JsonView(SingleResponseObjectDtoView.FullWithUserFull.class)
    public SingleResponseObjectDto postNewUser(@RequestBody UserDto userDto) {

        Optional<UserDto> optionalUserDto = Optional.of(this.userMapper.toUserDto(this.userService.signUp(userDto)));

        SingleResponseObjectDto singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                "Новый пользователь",
                true,
                optionalUserDto.orElseThrow(NullPointerException::new)
        );

        return singleResponseObjectDto;
    }

    @GetMapping(value = "/{id}")
    @JsonView(SingleResponseObjectDtoView.FullWithUserFull.class)
    public SingleResponseObjectDto getUserId(@PathVariable(value = "id") Long id) {

        SingleResponseObjectDtoExt<Object> singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                "listUser(" + id + ")",
                true,
                userMapper.toUserDto(this.userService.getOnlyOneUser(id))
        );

        return singleResponseObjectDto;
    }

    @GetMapping(value = "/role")
    @JsonView(SingleResponseObjectDtoView.Full.class)
    public SingleResponseObjectDto getUserRole(@RequestParam(value = "token") String token) {

        log.warn("Достал токен: " + token); // Можно роли и по токену найти

        SingleResponseObjectDtoExt<Object> singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                "listRole()",
                true,
                this.userService.getMyRolesFromSpringContex() // Здесь они берутся из контекста
        );

        return singleResponseObjectDto;
    }

    @GetMapping(value = "/security/filter/pre")
    @JsonView(SingleResponseObjectDtoView.FullWithUserFull.class)
    public SingleResponseObjectDto getSecurityFilterPre() {

        log.info("Испытание началось");

        SingleResponseObjectDtoExt<Object> singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                "listRole()",
                true,
                this.userService.joinUsernames(this.userService.getAllUsers())
        );

        return singleResponseObjectDto;
    }

    @GetMapping(value = "/security/filter/post")
    @JsonView(SingleResponseObjectDtoView.FullWithUserFull.class)
    public SingleResponseObjectDto getSecurityFilterPost() {

        log.info("Испытание началось");

        SingleResponseObjectDtoExt<Object> singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                "listRole()",
                true,
                userMapper.toUserDTOs(this.userService.getAllExceptPrincipalUser())
        );

        return singleResponseObjectDto;
    }
}
