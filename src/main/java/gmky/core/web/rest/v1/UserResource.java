package gmky.core.web.rest.v1;

import gmky.core.api.UserApi;
import gmky.core.api.model.ChangePasswordReq;
import gmky.core.api.model.FilterUserReq;
import gmky.core.api.model.UpdateUserReq;
import gmky.core.api.model.UserDTO;
import gmky.core.service.UserService;
import gmky.core.utils.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserResource implements UserApi {
    private final UserService userService;

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).USER, {T(gmky.core.enumeration.ActionEnum).EDIT})")
    public ResponseEntity<UserDTO> attachRoles(Long userId, List<Long> requestBody) {
        var result = userService.attachRoles(userId, requestBody);
        return ResponseEntity.ok(result);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).USER, {T(gmky.core.enumeration.ActionEnum).EDIT})")
    public ResponseEntity<Void> changePasswordById(Long userId, ChangePasswordReq changePasswordReq) {
        userService.changePassword(userId, changePasswordReq);
        return ResponseEntity.ok().build();
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).USER, {T(gmky.core.enumeration.ActionEnum).DELETE})")
    public ResponseEntity<Void> deleteUserById(Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.ok().build();
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).USER, {T(gmky.core.enumeration.ActionEnum).EDIT})")
    public ResponseEntity<UserDTO> detachRoles(Long userId, List<Long> requestBody) {
        var result = userService.detachRoles(userId, requestBody);
        return ResponseEntity.ok(result);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).USER, {T(gmky.core.enumeration.ActionEnum).VIEW})")
    public ResponseEntity<List<UserDTO>> filterUser(Pageable pageable, FilterUserReq filterUserReq) {
        var page = userService.filterUser(filterUserReq, pageable);
        var headers = PageUtil.generatePaginationHeader(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).USER, {T(gmky.core.enumeration.ActionEnum).VIEW})")
    public ResponseEntity<UserDTO> getUserById(Long userId) {
        var result = userService.findById(userId);
        return ResponseEntity.ok(result);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).USER, {T(gmky.core.enumeration.ActionEnum).EDIT})")
    public ResponseEntity<UserDTO> updateUser(Long userId, UpdateUserReq updateUserReq) {
        var result = userService.updateUser(userId, updateUserReq);
        return ResponseEntity.ok(result);
    }
}
