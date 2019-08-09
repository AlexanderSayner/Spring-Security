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

        return this.userRepository.findAll();
    }

    @Override
    public User getOnlyOneUser(Long id) throws NullPointerException {
        return this.userRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    @Override
    public User getUserByHisLogin(String login) throws NullPointerException {
        return this.userRepository.findOneByLogin(login).orElseThrow(NullPointerException::new);
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
