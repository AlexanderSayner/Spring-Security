package sayner.sandbox.services.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import sayner.sandbox.model.User;
import sayner.sandbox.services.UserService;

import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getAllUsers() throws NullPointerException {

        log.info("getAllUsers() method");

        User user = new User();
        user.setId("12");
        user.setEmail("johndoe123@gmail.com");
        user.setName("John Doe");
        user.setAddress("Bangalore, Karnataka");
        log.info(user.toString());

        User user1 = new User();
        user1.setId("13");
        user1.setEmail("amitsingh@yahoo.com");
        user1.setName("Amit Singh");
        user1.setAddress("Chennai, Tamilnadu");
        log.info(user1.toString());

        User user2 = new User();
        user2.setId("14");
        user2.setEmail("bipulkumar@gmail.com");
        user2.setName("Bipul Kumar");
        user2.setAddress("Bangalore, Karnataka");
        log.info(user2.toString());

        User user3 = new User();
        user3.setId("15");
        user3.setEmail("prakashranjan@gmail.com");
        user3.setName("Prakash Ranjan");
        user3.setAddress("Chennai, Tamilnadu");
        log.info(user3.toString());

        return Arrays.asList(user, user1, user2, user3);
    }
}
