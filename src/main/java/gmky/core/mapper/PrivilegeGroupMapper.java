package gmky.core.mapper;

import gmky.core.api.model.CreatePrivilegeGroupReq;
import gmky.core.api.model.PrivilegeGroupDTO;
import gmky.core.entity.PrivilegeGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PrivilegeGroupMapper extends EntityMapper<PrivilegeGroupDTO, PrivilegeGroup> {
    @Mapping(target = "privileges", ignore = true)
    PrivilegeGroup toEntity(CreatePrivilegeGroupReq request);
}
