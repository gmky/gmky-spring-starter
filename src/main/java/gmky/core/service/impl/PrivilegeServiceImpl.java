package gmky.core.service.impl;

import gmky.core.api.model.PrivilegeDTO;
import gmky.core.enumeration.ActionEnum;
import gmky.core.enumeration.ResourceCodeEnum;
import gmky.core.exception.NotFoundException;
import gmky.core.mapper.PrivilegeMapper;
import gmky.core.repository.PrivilegeRepository;
import gmky.core.service.PrivilegeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static gmky.core.enumeration.CommonExceptionEnum.PRIVILEGE_NOT_FOUND;


@Slf4j
@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {
    private final PrivilegeRepository privilegeRepository;
    private final PrivilegeMapper privilegeMapper;
    @Override
    public Page<PrivilegeDTO> searchByPgId(Long pgId, Pageable pageable) {
        var page = privilegeRepository.findByPgId(pgId, pageable);
        return page.map(privilegeMapper::toDto);
    }

    @Override
    public PrivilegeDTO findById(Long id) {
        var privilege = privilegeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRIVILEGE_NOT_FOUND));
        return privilegeMapper.toDto(privilege);
    }

    @Override
    public Page<PrivilegeDTO> searchPrivilege(ActionEnum action, ResourceCodeEnum resourceCode, Pageable pageable) {
        var page = privilegeRepository.searchByActionAndResourceCode(action, resourceCode, pageable);
        return page.map(privilegeMapper::toDto);
    }
}
