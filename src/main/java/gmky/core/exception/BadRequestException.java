package gmky.core.exception;

import gmky.core.enumeration.CommonExceptionEnum;
import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
    public BadRequestException(CommonExceptionEnum detail) {
        super(HttpStatus.BAD_REQUEST, detail);
    }
}
