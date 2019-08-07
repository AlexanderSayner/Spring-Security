package sayner.sandbox.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import sayner.sandbox.dto.SingleResponseObjectDto;
import sayner.sandbox.dto.ext.SingleResponseObjectDtoExt;
import sayner.sandbox.dto.status.enums.StatusEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException, ServletException {

        response.addHeader("WWW-Authenticate", "Basic realm=" + this.getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        SingleResponseObjectDto singleResponseObjectDtpExt = new SingleResponseObjectDtoExt<>(
                StatusEnum.Unauthorized,
                "HTTP Status 401 - " + authEx.getMessage(),
                false,
                response.getStatus());

        ObjectMapper mapper = new ObjectMapper();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        mapper.writeValue(out, singleResponseObjectDtpExt
        );
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("DeveloperStack");
        super.afterPropertiesSet();
    }
}
