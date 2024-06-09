package gmky.core.web.rest.v1;

import gmky.core.api.RoleApi;
import gmky.core.api.model.CreateRoleReq;
import gmky.core.api.model.FilterRoleReq;
import gmky.core.api.model.PrivilegeGroupDTO;
import gmky.core.api.model.RoleDTO;
import gmky.core.service.PrivilegeGroupService;
import gmky.core.service.RoleService;
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
public class RoleResource implements RoleApi {
    private final RoleService roleService;
    private final PrivilegeGroupService pgService;

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).ROLE, {T(gmky.core.enumeration.ActionEnum).EDIT})")
    public ResponseEntity<RoleDTO> attachPrivilegeGroup(Long roleId, List<Long> requestBody) {
        var result = roleService.attachPg(roleId, requestBody);
        return ResponseEntity.ok(result);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).ROLE, {T(gmky.core.enumeration.ActionEnum).CREATE})")
    public ResponseEntity<RoleDTO> createRole(CreateRoleReq createRoleReq) {
        var result = roleService.createRole(createRoleReq);
        return ResponseEntity.ok(result);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).ROLE, {T(gmky.core.enumeration.ActionEnum).EDIT})")
    public ResponseEntity<Void> deactivateRoleById(Long roleId) {
        roleService.deactivateById(roleId);
        return ResponseEntity.ok().build();
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).ROLE, {T(gmky.core.enumeration.ActionEnum).EDIT})")
    public ResponseEntity<RoleDTO> detachPrivilegeGroup(Long roleId, List<Long> requestBody) {
        var result = roleService.detachPg(roleId, requestBody);
        return ResponseEntity.ok(result);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).ROLE, {T(gmky.core.enumeration.ActionEnum).VIEW})")
    public ResponseEntity<List<RoleDTO>> filter(Pageable pageable, FilterRoleReq filterRoleReq) {
        var page = roleService.filter(filterRoleReq, pageable);
        var headers = PageUtil.generatePaginationHeader(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).ROLE, {T(gmky.core.enumeration.ActionEnum).VIEW})")
    public ResponseEntity<List<PrivilegeGroupDTO>> getPrivilegeGroupOfRoleByRoleId(Long roleId, Pageable pageable) {
        var page = pgService.findByRoleId(roleId, pageable);
        var headers = PageUtil.generatePaginationHeader(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).ROLE, {T(gmky.core.enumeration.ActionEnum).VIEW})")
    public ResponseEntity<RoleDTO> getRoleById(Long roleId) {
        var role = roleService.findById(roleId);
        return ResponseEntity.ok(role);
    }
}
