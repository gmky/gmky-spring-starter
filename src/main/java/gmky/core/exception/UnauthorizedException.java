package gmky.core.exception;

import gmky.core.enumeration.CommonExceptionEnum;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, CommonExceptionEnum.UNAUTHORIZED);
    }

    public UnauthorizedException(CommonExceptionEnum detail) {
        super(HttpStatus.UNAUTHORIZED, detail);
    }
}
