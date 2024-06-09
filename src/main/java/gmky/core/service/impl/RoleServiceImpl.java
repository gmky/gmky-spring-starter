package gmky.core.service.impl;

import gmky.core.api.model.CreateRoleReq;
import gmky.core.api.model.FilterRoleReq;
import gmky.core.api.model.RoleDTO;
import gmky.core.enumeration.RoleStatusEnum;
import gmky.core.exception.BadRequestException;
import gmky.core.exception.NotFoundException;
import gmky.core.mapper.RoleMapper;
import gmky.core.repository.PrivilegeGroupRepository;
import gmky.core.repository.RoleRepository;
import gmky.core.service.RoleService;
import gmky.core.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;

import static gmky.core.enumeration.CommonExceptionEnum.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PrivilegeGroupRepository pgRepository;
    private final RoleMapper roleMapper;

    @Override
    public Page<RoleDTO> filter(FilterRoleReq req, Pageable pageable) {
        var page = roleRepository.filter(req, pageable);
        return page.map(roleMapper::toDto);
    }

    @Override
    public RoleDTO findById(Long id) {
        var role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));
        return roleMapper.toDto(role);
    }

    @Override
    public void deactivateById(Long id) {
        var actor = SecurityUtil.getCurrentUsername();
        var role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));
        role.setDeactivatedBy(actor);
        role.setDeactivatedAt(Instant.now());
        roleRepository.save(role);
    }

    @Override
    public RoleDTO createRole(CreateRoleReq req) {
        var existed = roleRepository.existsByNameIgnoreCase(req.getName());
        if (existed) throw new BadRequestException(ROLE_EXISTED);
        var parentExisted = roleRepository.existsById(req.getParentId());
        if (!parentExisted) throw new BadRequestException(PARENT_ROLE_NOT_EXISTED);
        var role = roleMapper.toEntity(req);
        role.setStatus(RoleStatusEnum.ACTIVE);
        var pgs = pgRepository.findAllById(req.getPgIds());
        role.setPrivilegeGroups(new HashSet<>(pgs));
        role = roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    @Override
    @Transactional
    public RoleDTO attachPg(Long roleId, List<Long> pgIds) {
        var role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));
        var pgs = pgRepository.findAllById(pgIds);
        if (pgs.size() != pgIds.size()) throw new BadRequestException(INVALID_PG_IDS);
        role.addPg(pgs);
        role = roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    @Override
    @Transactional
    public RoleDTO detachPg(Long roleId, List<Long> pgIds) {
        var role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException(ROLE_NOT_FOUND));
        var pgs = pgRepository.findAllById(pgIds);
        if (pgs.size() != pgIds.size()) throw new BadRequestException(INVALID_PG_IDS);
        role.removePg(pgs);
        role = roleRepository.save(role);
        return roleMapper.toDto(role);
    }
}
