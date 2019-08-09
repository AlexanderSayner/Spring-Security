package sayner.sandbox.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sayner.sandbox.dto.UserDto;
import sayner.sandbox.dto.mappers.UserMapper;
import sayner.sandbox.model.User;
import sayner.sandbox.repositories.UserRepository;
import sayner.sandbox.services.UserService;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Primary
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() throws NullPointerException {

        log.info("getAllUsers() method");

        User user = new User();
        user.setId("12");
        user.setEmail("johndoe123@gmail.com");
        user.setLogin("John Doe");
        user.setHashPassword("Bangalore, Karnataka");
        log.info(user.toString());

        User user1 = new User();
        user1.setId("13");
        user1.setEmail("amitsingh@yahoo.com");
        user1.setLogin("Amit Singh");
        user1.setHashPassword("Chennai, Tamilnadu");
        log.info(user1.toString());

        User user2 = new User();
        user2.setId("14");
        user2.setEmail("bipulkumar@gmail.com");
        user2.setLogin("Bipul Kumar");
        user2.setHashPassword("Bangalore, Karnataka");
        log.info(user2.toString());

        User user3 = new User();
        user3.setId("15");
        user3.setEmail("prakashranjan@gmail.com");
        user3.setLogin("Prakash Ranjan");
        user3.setHashPassword("Chennai, Tamilnadu");
        log.info(user3.toString());

        return Arrays.asList(user, user1, user2, user3);
    }

    @Override
    public User getOnlyOneUser(String id) throws NullPointerException {
        return this.getAllUsers().stream().filter(user -> user.getId().equals(id)).findFirst().orElse(new User("0", "empty", "empty", "empty"));
    }

    @Override
    public User getUserByHisName(String name) throws NullPointerException {
        return this.getAllUsers().stream().filter(user -> user.getLogin().equals(name)).findFirst().orElse(new User("0", "empty", "empty", "empty"));
    }

    @Override
    public String getMyRoles(String login) throws NullPointerException {
        return this.userRepository.findOneByLogin(login).orElseThrow(NullPointerException::new).getUserRole().name();
    }

    @Override
    public User signUp(UserDto userDto) throws IllegalArgumentException {

        User user = UserMapper.INSTANCE.toUser(userDto);

        // Добавляется новый пользователь, id must be null
        user.setId(null);

        // Из UserDto приходит нехэшированный пароль, поэтому он должен быть захэширован
        user.setHashPassword(this.passwordEncoder.encode(userDto.getPassword()));

        log.info("Сохранён совый пользователь: " + user.toString());

        return this.userRepository.save(user);
    }
}
