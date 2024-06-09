package gmky.core.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonExceptionEnum {
    ACCESS_DENIED("ERR0001", "Access denied"),
    BAD_REQUEST("ERR0002", "Bad request"),
    UNAUTHORIZED("ERR0014", "Unauthorized"),
    INTERNAL_SERVER("ERR0017", "Internal server error");

    private final String code;
    private final String message;
}
