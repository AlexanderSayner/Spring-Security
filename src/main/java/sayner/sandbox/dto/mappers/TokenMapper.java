package sayner.sandbox.dto.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import sayner.sandbox.dto.TokenDto;
import sayner.sandbox.model.Token;

import java.util.Collection;

@Component
@Mapper
public interface TokenMapper {

    TokenMapper INSTANCE = Mappers.getMapper(TokenMapper.class);

    @Mappings({
            @Mapping(ignore = true, target = "id"),
            @Mapping(ignore = true, target = "user")
    })
    Token toToken(TokenDto tokenDto);

    @InheritInverseConfiguration
    TokenDto toTokenDto(Token token);

    Collection<TokenDto> toTokenDTOs(Collection<Token> tokens);
}
