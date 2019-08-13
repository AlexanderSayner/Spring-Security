package sayner.sandbox;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
@Log4j2
public class Application {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // Входная точка приложения
    public static void main(String[] args) {

        String port = args[0];
        if (args[0] == null) port = "8080";

        log.info("Аргумент 1: '" + args[0] + "' - порт запуска приложения: '" + port + "'");

        SpringApplication app = new SpringApplication(Application.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", port));

        app.run(args);
    }
}
