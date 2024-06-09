package gmky.core.mapper;

import gmky.core.api.model.CreateRoleReq;
import gmky.core.api.model.RoleDTO;
import gmky.core.entity.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper extends EntityMapper<RoleDTO, Role> {
    Role toEntity(CreateRoleReq request);
}
