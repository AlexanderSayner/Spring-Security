package sayner.sandbox.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import sayner.sandbox.security.filters.TokenAuthFilter;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final TokenAuthFilter tokenAuthFilter; // Преобразует токен в объект аунтификации
    private final AuthenticationProvider authenticationProvider; // Автоматически вызывается, проверяя валидность токена
    private final BasicAuthenticationEntryPoint authEntryPoint;

//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .addFilterBefore(this.tokenAuthFilter, BasicAuthenticationFilter.class)
//                .antMatcher("/**")
//                .authenticationProvider(this.authenticationProvider)
//                    .authorizeRequests()
//                        // Важный моммент
//                        // Сначала нужно разрешать
//                        .antMatchers("/login","/logout").permitAll()
//                        // А затем блочить
//                        .anyRequest().authenticated()
//                        .and()
//                    .httpBasic()
//                        .authenticationEntryPoint(this.authEntryPoint)
//        ;
//    }
//
//}


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth
                .inMemoryAuthentication()
                .withUser("adm").password(this.passwordEncoder.encode("adm")).roles("GODLiKE")
                .and()
                .withUser("awesomeUser").password(this.passwordEncoder.encode("123")).roles("A_MERE_MORTAL")
                .and()
                .and()
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder)
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterBefore(this.tokenAuthFilter, BasicAuthenticationFilter.class)
                .antMatcher("/**")
                .authenticationProvider(this.authenticationProvider)
                    .authorizeRequests()
                        // Сначала нужно разрешать
                        .antMatchers("/login", "logout").permitAll()
                        // А затем блочить
                        .antMatchers("/user*/**").hasRole("A_MERE_MORTAL")
                        .anyRequest().authenticated()//.hasAuthority("ROLE_A_MERE_MORTAL")
                        .and()
                .httpBasic()
                .authenticationEntryPoint(this.authEntryPoint)
        ;
    }
}