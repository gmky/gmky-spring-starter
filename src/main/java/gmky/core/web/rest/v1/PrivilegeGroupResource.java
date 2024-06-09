package gmky.core.web.rest.v1;

import gmky.core.api.PrivilegeGroupApi;
import gmky.core.api.model.CreatePrivilegeGroupReq;
import gmky.core.api.model.PrivilegeDTO;
import gmky.core.api.model.PrivilegeGroupDTO;
import gmky.core.service.PrivilegeGroupService;
import gmky.core.service.PrivilegeService;
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
public class PrivilegeGroupResource implements PrivilegeGroupApi {
    private final PrivilegeGroupService pgService;
    private final PrivilegeService privilegeService;

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).PRIVILEGE_GROUP, {T(gmky.core.enumeration.ActionEnum).EDIT})")
    public ResponseEntity<PrivilegeGroupDTO> attachPrivilege(Long pgId, List<Long> requestBody) {
        var result = pgService.attachPrivilege(pgId, requestBody);
        return ResponseEntity.ok(result);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).PRIVILEGE_GROUP, {T(gmky.core.enumeration.ActionEnum).CREATE})")
    public ResponseEntity<PrivilegeGroupDTO> createPrivilegeGroup(CreatePrivilegeGroupReq createPrivilegeGroupReq) {
        var result = pgService.createPrivilegeGroup(createPrivilegeGroupReq);
        return ResponseEntity.ok(result);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).PRIVILEGE_GROUP, {T(gmky.core.enumeration.ActionEnum).EDIT})")
    public ResponseEntity<PrivilegeGroupDTO> detachPrivilege(Long pgId, List<Long> requestBody) {
        var result = pgService.detachPrivilege(pgId, requestBody);
        return ResponseEntity.ok(result);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).PRIVILEGE_GROUP, {T(gmky.core.enumeration.ActionEnum).VIEW})")
    public ResponseEntity<PrivilegeGroupDTO> getPGById(Long pgId) {
        var pg = pgService.findById(pgId);
        return ResponseEntity.ok(pg);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).PRIVILEGE_GROUP, {T(gmky.core.enumeration.ActionEnum).VIEW})")
    public ResponseEntity<List<PrivilegeDTO>> getPrivilegeByPGId(Long pgId, Pageable pageable) {
        var page = privilegeService.searchByPgId(pgId, pageable);
        var headers = PageUtil.generatePaginationHeader(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).PRIVILEGE_GROUP, {T(gmky.core.enumeration.ActionEnum).VIEW})")
    public ResponseEntity<List<PrivilegeGroupDTO>> searchPrivilegeGroup(String name, Pageable pageable) {
        var page = pgService.search(name, pageable);
        var headers = PageUtil.generatePaginationHeader(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
