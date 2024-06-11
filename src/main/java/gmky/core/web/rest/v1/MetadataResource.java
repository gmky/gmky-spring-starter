package gmky.core.web.rest.v1;

import gmky.core.api.MetadataApi;
import gmky.core.api.model.PrivilegeGroupTypeEnum;
import gmky.core.api.model.RoleTypeEnum;
import gmky.core.enumeration.ActionEnum;
import gmky.core.enumeration.ResourceCodeEnum;
import gmky.core.enumeration.RoleStatusEnum;
import gmky.core.enumeration.UserStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class MetadataResource implements MetadataApi {
    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).ACTION, T(gmky.core.enumeration.ActionEnum).VIEW)")
    public ResponseEntity<List<ActionEnum>> getAction() {
        var data = Arrays.stream(ActionEnum.values()).toList();
        return ResponseEntity.ok(data);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).PRIVILEGE_GROUP, T(gmky.core.enumeration.ActionEnum).VIEW)")
    public ResponseEntity<List<PrivilegeGroupTypeEnum>> getPrivilegeGroupType() {
        var data = Arrays.stream(PrivilegeGroupTypeEnum.values()).toList();
        return ResponseEntity.ok(data);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).RESOURCE, T(gmky.core.enumeration.ActionEnum).VIEW)")
    public ResponseEntity<List<ResourceCodeEnum>> getResourceCode() {
        var data = Arrays.stream(ResourceCodeEnum.values()).toList();
        return ResponseEntity.ok(data);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).ROLE, T(gmky.core.enumeration.ActionEnum).VIEW)")
    public ResponseEntity<List<RoleStatusEnum>> getRoleStatus() {
        var data = Arrays.stream(RoleStatusEnum.values()).toList();
        return ResponseEntity.ok(data);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).ROLE, T(gmky.core.enumeration.ActionEnum).VIEW)")
    public ResponseEntity<List<RoleTypeEnum>> getRoleType() {
        var data = Arrays.stream(RoleTypeEnum.values()).toList();
        return ResponseEntity.ok(data);
    }

    @Override
    @PreAuthorize("checkPermission(T(gmky.core.enumeration.ResourceCodeEnum).ROLE, T(gmky.core.enumeration.ActionEnum).VIEW)")
    public ResponseEntity<List<UserStatusEnum>> getUserStatus() {
        var data = Arrays.stream(UserStatusEnum.values()).toList();
        return ResponseEntity.ok(data);
    }
}
