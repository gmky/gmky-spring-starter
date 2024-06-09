package gmky.core.mapper;

import gmky.core.api.model.PrivilegeDTO;
import gmky.core.entity.Privilege;
import gmky.core.repository.projection.PrivilegeRO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PrivilegeMapper extends EntityMapper<PrivilegeDTO, Privilege> {
    List<PrivilegeDTO> fromRO(List<PrivilegeRO> roList);

    PrivilegeDTO fromRO(PrivilegeRO ro);
}
