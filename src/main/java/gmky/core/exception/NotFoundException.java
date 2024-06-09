package gmky.core.exception;

import gmky.core.enumeration.CommonExceptionEnum;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(String code, String message) {
        super(HttpStatus.NOT_FOUND, code, message);
    }
    public NotFoundException(CommonExceptionEnum detail) {
        super(HttpStatus.NOT_FOUND, detail);
    }

}
