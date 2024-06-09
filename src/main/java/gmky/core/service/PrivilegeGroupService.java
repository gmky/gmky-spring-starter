package gmky.core.service;

import gmky.core.api.model.CreatePrivilegeGroupReq;
import gmky.core.api.model.PrivilegeGroupDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrivilegeGroupService {
    PrivilegeGroupDTO findById(Long id);

    Page<PrivilegeGroupDTO> findByRoleId(Long roleId, Pageable pageable);

    Page<PrivilegeGroupDTO> search(String name, Pageable pageable);

    PrivilegeGroupDTO createPrivilegeGroup(CreatePrivilegeGroupReq req);

    PrivilegeGroupDTO attachPrivilege(Long pgId, List<Long> privilegeIds);

    PrivilegeGroupDTO detachPrivilege(Long pgId, List<Long> privilegeIds);
}
