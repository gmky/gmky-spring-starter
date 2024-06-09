package gmky.core.exception;

import gmky.core.enumeration.CommonExceptionEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class BaseException extends ResponseStatusException {
    private final String code;
    private final String message;

    public BaseException(HttpStatus httpStatus, String code, String message) {
        super(httpStatus);
        this.code = code;
        this.message = message;
    }

    public BaseException(HttpStatus httpStatus, CommonExceptionEnum detail) {
        super(httpStatus);
        this.code = detail.getCode();
        this.message = detail.getMessage();
    }
}
