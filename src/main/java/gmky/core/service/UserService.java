package gmky.core.service;

import gmky.core.api.model.ChangePasswordReq;
import gmky.core.api.model.FilterUserReq;
import gmky.core.api.model.UpdateUserReq;
import gmky.core.api.model.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<UserDTO> filterUser(FilterUserReq req, Pageable pageable);

    UserDTO findByUsername(String username);

    UserDTO findById(Long userId);

    void deleteById(Long id);

    void changePassword(Long userId, ChangePasswordReq req);

    UserDTO attachRoles(Long userId, List<Long> roleIds);

    UserDTO detachRoles(Long userId, List<Long> roleIds);

    UserDTO updateUser(Long userId, UpdateUserReq req);
}
