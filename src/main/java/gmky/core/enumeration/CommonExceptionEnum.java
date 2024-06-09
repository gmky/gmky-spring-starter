package gmky.core.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonExceptionEnum {
    ACCESS_DENIED("ERR0001", "Access denied"),
    BAD_REQUEST("ERR0002", "Bad request"),
    UNAUTHORIZED("ERR0014", "Unauthorized"),
    INTERNAL_SERVER("ERR0017", "Internal server error"),
    USER_NOT_FOUND("ERR0003", "User not found"),
    LOGIN_FAILURE("ERR0004", "Failed to login"),
    ROLE_NOT_FOUND("ERR0005", "Role not found"),
    PG_NOT_FOUND("ERR0006", "Privilege group not found"),
    PRIVILEGE_NOT_FOUND("ERR0007", "Privilege not found"),
    PG_EXISTED("ERR0008", "Privilege group existed"),
    ROLE_EXISTED("ERR0009", "Role existed"),
    PARENT_ROLE_NOT_EXISTED("ERR0010", "Parent role not existed"),
    INVALID_PG_IDS("ERR0011", "Invalid privilege group ids"),
    INVALID_PRIVILEGE_IDS("ERR0012", "Invalid privilege ids"),
    INVALID_ROLE_IDS("ERR0013", "Invalid role ids");

    private final String code;
    private final String message;
}
