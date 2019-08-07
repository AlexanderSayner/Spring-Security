package sayner.sandbox.dto.ext;

import lombok.extern.log4j.Log4j2;
import sayner.sandbox.dto.SingleResponseObjectDto;
import sayner.sandbox.dto.status.enums.StatusCodeEnum;
import sayner.sandbox.dto.status.enums.StatusEnum;

@Log4j2
public class SingleResponseObjectDtoExt<T> extends SingleResponseObjectDto {

    private StatusCodeEnum statusCode;
    private String message;
    private Boolean isSuccess;
    private T dataOrException;

    public SingleResponseObjectDtoExt(StatusEnum status, String message, Boolean success, T dataOrException) {

        this.statusCode = this.getErrorCodeEnum(status);
        this.message = message;
        this.isSuccess = success;
        this.dataOrException = dataOrException;

        log.trace("Response dto has created, status: " + status);
    }
}
