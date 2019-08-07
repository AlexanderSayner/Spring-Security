package sayner.sandbox.dto.ext;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import sayner.sandbox.dto.SingleResponseObjectDto;
import sayner.sandbox.dto.status.enums.StatusCodeEnum;
import sayner.sandbox.dto.status.enums.StatusEnum;
import sayner.sandbox.dto.views.SingleResponseObjectDtoView;

@Log4j2
public class SingleResponseObjectDtoExt<T> extends SingleResponseObjectDto {

    @JsonView(SingleResponseObjectDtoView.StatusCode.class)
    private StatusCodeEnum statusCode;

    @JsonView(SingleResponseObjectDtoView.Message.class)
    private String message;

    @JsonView(SingleResponseObjectDtoView.Success.class)
    private Boolean isSuccess;

    @JsonView(SingleResponseObjectDtoView.DataOrException.class)
    private T dataOrException;

    public SingleResponseObjectDtoExt(StatusEnum status, String message, Boolean success, T dataOrException) {

        this.statusCode = this.getErrorCodeEnum(status);
        this.message = message;
        this.isSuccess = success;
        this.dataOrException = dataOrException;

        log.trace("Response dto has created, status: " + status);
    }
}
