package gmky.core.web.rest.v1;

import gmky.core.api.AuthApi;
import gmky.core.api.model.LoginReq;
import gmky.core.api.model.LoginRes;
import gmky.core.api.model.UserDTO;
import gmky.core.api.model.UserSummaryRes;
import gmky.core.service.AuthService;
import gmky.core.service.UserService;
import gmky.core.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthResource implements AuthApi {
    private final AuthService authService;
    private final UserService userService;

    @Override
    public ResponseEntity<Void> forgotPassword(String email) {
        authService.forgotPassword(email);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<LoginRes> login(LoginReq loginReq) {
        var result = authService.login(loginReq);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<UserDTO> me() {
        var currentUsername = SecurityUtil.getCurrentUsername();
        var result = userService.findByUsername(currentUsername);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<UserSummaryRes> summary() {
        var result = authService.summary();
        return ResponseEntity.ok(result);
    }
}
