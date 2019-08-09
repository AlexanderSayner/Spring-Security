package sayner.sandbox.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public final class TokenDto {

    @JsonView
    private String value;
}
