package gmky.core.service.impl;

import gmky.core.api.model.CreatePrivilegeGroupReq;
import gmky.core.api.model.PrivilegeGroupDTO;
import gmky.core.exception.BadRequestException;
import gmky.core.exception.NotFoundException;
import gmky.core.mapper.PrivilegeGroupMapper;
import gmky.core.repository.PrivilegeGroupRepository;
import gmky.core.repository.PrivilegeRepository;
import gmky.core.service.PrivilegeGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static gmky.core.enumeration.CommonExceptionEnum.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrivilegeGroupServiceImpl implements PrivilegeGroupService {
    private final PrivilegeRepository privilegeRepository;
    private final PrivilegeGroupRepository pgRepository;
    private final PrivilegeGroupMapper pgMapper;

    @Override
    public PrivilegeGroupDTO findById(Long id) {
        var pg = pgRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PG_NOT_FOUND));
        return pgMapper.toDto(pg);
    }

    @Override
    public Page<PrivilegeGroupDTO> findByRoleId(Long roleId, Pageable pageable) {
        var page = pgRepository.findByRoleId(roleId, pageable);
        return page.map(pgMapper::toDto);
    }

    @Override
    public Page<PrivilegeGroupDTO> search(String name, Pageable pageable) {
        var page = pgRepository.searchByName(name, pageable);
        return page.map(pgMapper::toDto);
    }

    @Override
    @Transactional
    public PrivilegeGroupDTO createPrivilegeGroup(CreatePrivilegeGroupReq req) {
        var existed = pgRepository.existsByNameIgnoreCase(req.getName());
        if (existed) throw new BadRequestException(PG_EXISTED);
        var pg = pgMapper.toEntity(req);
        var privileges = privilegeRepository.findAllById(req.getPrivilegeIds());
        pg.setPrivileges(new HashSet<>(privileges));
        return pgMapper.toDto(pgRepository.save(pg));
    }

    @Override
    @Transactional
    public PrivilegeGroupDTO attachPrivilege(Long pgId, List<Long> privilegeIds) {
        var pg = pgRepository.findById(pgId).orElseThrow(() -> new NotFoundException(PG_NOT_FOUND));
        var privileges = privilegeRepository.findAllById(privilegeIds);
        if (privileges.size() != privilegeIds.size()) throw new BadRequestException(INVALID_PRIVILEGE_IDS);
        pg.addPrivileges(privileges);
        pg = pgRepository.save(pg);
        return pgMapper.toDto(pg);
    }

    @Override
    @Transactional
    public PrivilegeGroupDTO detachPrivilege(Long pgId, List<Long> privilegeIds) {
        var pg = pgRepository.findById(pgId).orElseThrow(() -> new NotFoundException(PG_NOT_FOUND));
        var privileges = privilegeRepository.findAllById(privilegeIds);
        if (privileges.size() != privilegeIds.size()) throw new BadRequestException(INVALID_PRIVILEGE_IDS);
        pg.removePrivileges(privileges);
        pg = pgRepository.save(pg);
        return pgMapper.toDto(pg);
    }
}
