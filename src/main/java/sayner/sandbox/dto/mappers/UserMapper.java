package sayner.sandbox.dto.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import sayner.sandbox.dto.UserDto;
import sayner.sandbox.model.User;

import java.util.Collection;

@Component
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "id", source = "userId"),
            @Mapping(target = "hashPassword", source = "password", ignore = true),
            @Mapping(target = "tokens", ignore = true)
    })
    User toUser(UserDto userDto);

    Collection<UserDto> toUserDTOs(Collection<User> users);

    @InheritInverseConfiguration
    UserDto toUserDto(User user);
}
