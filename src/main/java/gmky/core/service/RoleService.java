package gmky.core.service;

import gmky.core.api.model.CreateRoleReq;
import gmky.core.api.model.FilterRoleReq;
import gmky.core.api.model.RoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    Page<RoleDTO> filter(FilterRoleReq req, Pageable pageable);

    RoleDTO findById(Long id);

    void deactivateById(Long id);

    RoleDTO createRole(CreateRoleReq req);

    RoleDTO attachPg(Long roleId, List<Long> pgIds);

    RoleDTO detachPg(Long roleId, List<Long> pgIds);
}
