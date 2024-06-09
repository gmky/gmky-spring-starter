package gmky.core.utils;

import gmky.core.exception.UnauthorizedException;
import gmky.core.security.CustomUserDetails;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static gmky.core.enumeration.CommonExceptionEnum.UNAUTHORIZED;

@UtilityClass
public class SecurityUtil {
    public static String getCurrentUsername() {
        var ctxHolder = SecurityContextHolder.getContext();
        var authentication = ctxHolder.getAuthentication();
        if (authentication == null) return null;
        var principal = authentication.getPrincipal();
        if (principal instanceof String username) {
            return username;
        } else if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        return null;
    }

    public static Long getCurrentUserId() {
        Long userId = null;
        var ctxHolder = SecurityContextHolder.getContext();
        var authentication = ctxHolder.getAuthentication();
        if (authentication != null) {
            var principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails userDetails) {
                userId = userDetails.getId();
            }
        }
        if (userId == null) throw new UnauthorizedException(UNAUTHORIZED);
        return userId;
    }
}
