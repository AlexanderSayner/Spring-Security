package sayner.sandbox.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sayner.sandbox.dto.SingleResponseObjectDto;
import sayner.sandbox.dto.ext.SingleResponseObjectDtoExt;
import sayner.sandbox.dto.status.enums.StatusEnum;
import sayner.sandbox.dto.forms.LoginForm;
import sayner.sandbox.services.LoginService;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@RestController
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public SingleResponseObjectDto login(@RequestBody LoginForm form) {

        SingleResponseObjectDto singleResponseObjectDto = new SingleResponseObjectDtoExt<>(

                    StatusEnum.AllDoneWell,
                    "login service",
                    true,
                    this.loginService.login(form)
            );

        return singleResponseObjectDto;
    }
}
