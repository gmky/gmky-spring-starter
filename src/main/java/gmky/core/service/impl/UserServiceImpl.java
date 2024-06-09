package gmky.core.service.impl;

import gmky.core.api.model.ChangePasswordReq;
import gmky.core.api.model.FilterUserReq;
import gmky.core.api.model.UpdateUserReq;
import gmky.core.api.model.UserDTO;
import gmky.core.exception.BadRequestException;
import gmky.core.exception.NotFoundException;
import gmky.core.mapper.UserMapper;
import gmky.core.repository.RoleRepository;
import gmky.core.repository.UserRepository;
import gmky.core.service.UserService;
import gmky.core.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

import static gmky.core.enumeration.CommonExceptionEnum.INVALID_ROLE_IDS;
import static gmky.core.enumeration.CommonExceptionEnum.USER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<UserDTO> filterUser(FilterUserReq req, Pageable pageable) {
        var page = userRepository.filterUser(req, pageable);
        return page.map(userMapper::toDto);
    }

    @Override
    public UserDTO findByUsername(String username) {
        var user = userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO findById(Long userId) {
        var user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return userMapper.toDto(user);
    }

    @Override
    public void deleteById(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        user.setDeletedAt(Instant.now());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void changePassword(Long userId, ChangePasswordReq req) {
        var user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        var isCurrentPasswordMatched = passwordEncoder.matches(req.getCurrentPassword(), user.getPassword());
        if (!isCurrentPasswordMatched) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Current password mismatch");
        var encodedNewPassword = passwordEncoder.encode(req.getNewPassword());
        user.setPassword(encodedNewPassword);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDTO attachRoles(Long userId, List<Long> roleIds) {
        var user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        var roles = roleRepository.findAllById(roleIds);
        if (roles.size() != roleIds.size()) throw new BadRequestException(INVALID_ROLE_IDS);
        user.addRoles(roles);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserDTO detachRoles(Long userId, List<Long> roleIds) {
        var user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        var roles = roleRepository.findAllById(roleIds);
        if (roles.size() != roleIds.size()) throw new BadRequestException(INVALID_ROLE_IDS);
        user.removeRoles(roles);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO updateUser(Long userId, UpdateUserReq req) {
        Long currentUserId = SecurityUtil.getCurrentUserId();
        var user = userRepository.findByIdIgnoreCurrentUser(currentUserId, userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        user = userMapper.partialUpdate(user, req);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }
}
