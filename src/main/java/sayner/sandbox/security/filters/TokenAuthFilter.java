package sayner.sandbox.security.filters;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sayner.sandbox.security.token.TokenAuthentication;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Component
@Log4j2
public class TokenAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        Optional<String> token;

        try {
            token = Optional.of(request.getParameter("token"));
        } catch (NullPointerException npe) {
            log.error("В фильтр пришёл пустой токен: " + npe.getMessage());
            token = Optional.empty();
        }

        TokenAuthentication tokenAuthentication =
                new TokenAuthentication(token.orElse(""));

        if (!token.isPresent()) {
            tokenAuthentication.setAuthenticated(false); // Не ясно куда токен пойдёт в этом случае. Кидать Exception?
        } else {

            // Обычные фильтры переходят в Spring Security
            // т.е.
            // вот есть обычная аунтефикация token.TokenAuthentication.
            // Туда помещаются фильтры, затем переходят в недры Spring Security
            SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
