package gmky.core.exception;

import gmky.core.enumeration.CommonExceptionEnum;
import org.springframework.http.HttpStatus;

public class InternalServerException extends BaseException {
    public InternalServerException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, CommonExceptionEnum.INTERNAL_SERVER);
    }
}
