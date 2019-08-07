package sayner.sandbox.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sayner.sandbox.dto.ext.SingleResponseObjectDtoExt;
import sayner.sandbox.dto.status.enums.StatusEnum;
import sayner.sandbox.dto.views.SingleResponseObjectDtoView;
import sayner.sandbox.services.UserService;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {

    private final UserService userService;

    @GetMapping
    @JsonView(SingleResponseObjectDtoView.Full.class)
    public SingleResponseObjectDtoExt<Object> listUsers() {

        SingleResponseObjectDtoExt<Object> singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                "listUsers()",
                true,
                this.userService.getAllUsers()
        );

        return singleResponseObjectDto;
    }

    @GetMapping(value = "/{id}")
    @JsonView(SingleResponseObjectDtoView.Full.class)
    public SingleResponseObjectDtoExt<Object> listUser(@PathVariable(value = "id") String id) {

        SingleResponseObjectDtoExt<Object> singleResponseObjectDto = new SingleResponseObjectDtoExt<>(
                StatusEnum.AllDoneWell,
                "listUsers()",
                true,
                this.userService.getAllUsers().stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null)
        );

        return singleResponseObjectDto;
    }
}
