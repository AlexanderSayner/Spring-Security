package sayner.sandbox.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sayner.sandbox.dto.SingleResponseObjectDto;
import sayner.sandbox.dto.ext.SingleResponseObjectDtoExt;
import sayner.sandbox.dto.status.enums.StatusEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @GetMapping("/login")
    public SingleResponseObjectDto getLoginRequest(HttpServletRequest request) {

        SingleResponseObjectDto singleResponseObjectDto;
        String responseMessage;

        if (request.getParameterMap().containsKey("error")) {

            responseMessage = "Логин или пароль введены неверно";

            singleResponseObjectDto = new SingleResponseObjectDtoExt<>(

                    StatusEnum.Unauthorized,
                    responseMessage,
                    false,
                    request.getContextPath()
            );
        } else {
            responseMessage = "Прокатило";

            singleResponseObjectDto = new SingleResponseObjectDtoExt<>(

                    StatusEnum.AllDoneWell,
                    responseMessage,
                    true,
                    request.getContextPath()
            );
        }

        return singleResponseObjectDto;
    }
}
