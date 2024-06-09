package gmky.core.service;

import gmky.core.api.model.PrivilegeDTO;
import gmky.core.enumeration.ActionEnum;
import gmky.core.enumeration.ResourceCodeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PrivilegeService {
    Page<PrivilegeDTO> searchByPgId(Long pgId, Pageable pageable);

    PrivilegeDTO findById(Long id);

    Page<PrivilegeDTO> searchPrivilege(ActionEnum action, ResourceCodeEnum resourceCode, Pageable pageable);
}
