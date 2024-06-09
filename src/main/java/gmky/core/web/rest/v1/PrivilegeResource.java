package gmky.core.web.rest.v1;

import gmky.core.api.PrivilegeApi;
import gmky.core.api.model.PrivilegeDTO;
import gmky.core.enumeration.ActionEnum;
import gmky.core.enumeration.ResourceCodeEnum;
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
public class PrivilegeResource implements PrivilegeApi {
    private final PrivilegeService privilegeService;

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).PRIVILEGE, {T(gmky.core.enumeration.ActionEnum).VIEW})")
    public ResponseEntity<PrivilegeDTO> getPrivilegeById(Long privilegeId) {
        var result = privilegeService.findById(privilegeId);
        return ResponseEntity.ok(result);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).PRIVILEGE, {T(gmky.core.enumeration.ActionEnum).VIEW})")
    public ResponseEntity<List<PrivilegeDTO>> searchPrivileges(ActionEnum action, ResourceCodeEnum resourceCode, Pageable pageable) {
        var page = privilegeService.searchPrivilege(action, resourceCode, pageable);
        var headers = PageUtil.generatePaginationHeader(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
