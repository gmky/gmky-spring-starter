package gmky.core.service.impl;

import gmky.core.api.model.LoginReq;
import gmky.core.api.model.LoginRes;
import gmky.core.api.model.UserSummaryRes;
import gmky.core.entity.User;
import gmky.core.exception.NotFoundException;
import gmky.core.exception.UnauthorizedException;
import gmky.core.mapper.PrivilegeMapper;
import gmky.core.repository.UserRepository;
import gmky.core.security.TokenProvider;
import gmky.core.service.AuthService;
import gmky.core.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

import static gmky.core.enumeration.CommonExceptionEnum.LOGIN_FAILURE;
import static gmky.core.enumeration.CommonExceptionEnum.USER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final long REFRESH_TOKEN_EXPIRE_IN_SECONDS = 3600L * 24L * 30L;
    private static final String FULL_NAME_KEY = "fullName";
    private static final String EXPIRE_AT_KEY = "expireAt";
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final PrivilegeMapper privilegeMapper;
    @Override
    public LoginRes login(LoginReq req) {
        var user = userRepository.findByUsernameIgnoreCase(req.getUsername())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        var isMatched = passwordEncoder.matches(req.getPassword(), user.getPassword());
        if (!isMatched) throw new UnauthorizedException(LOGIN_FAILURE);
        var result = generateTokens(user);
        log.info("[{}][{}] Logged in successfully", user.getId(), user.getUsername());
        return result;
    }

    @Override
    public void forgotPassword(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    @Override
    @Transactional
    public UserSummaryRes summary() {
        var currentUserId = SecurityUtil.getCurrentUserId();
        var user = userRepository.findById(currentUserId).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        var privileges = userRepository.getAllUserPrivileges(currentUserId).stream().toList();
        var result = new UserSummaryRes();
        result.setStatus(user.getStatus());
        result.setPrivileges(privilegeMapper.fromRO(privileges));
        return result;
    }

    private LoginRes generateTokens(User user) {
        var result = new LoginRes();
        result.setAccessToken(generateAccessToken(user));
        result.setIdToken(generateIdToken(user));
        result.setRefreshToken(generateRefreshToken(user));
        return result;
    }

    private String generateIdToken(User user) {
        var ctx = new HashMap<String, Object>();
        ctx.put(FULL_NAME_KEY, user.getFullName());
        return tokenProvider.generateToken(user.getUsername(), ctx);
    }

    private String generateRefreshToken(User user) {
        var ctx = new HashMap<String, Object>();
        ctx.put(EXPIRE_AT_KEY, user.getExpireAt());
        return tokenProvider.generateToken(user.getUsername(), ctx, REFRESH_TOKEN_EXPIRE_IN_SECONDS);
    }

    private String generateAccessToken(User user) {
        var ctx = new HashMap<String, Object>();
        ctx.put(FULL_NAME_KEY, user.getFullName());
        ctx.put(EXPIRE_AT_KEY, user.getExpireAt());
        return tokenProvider.generateToken(user.getUsername(), ctx);
    }
}
