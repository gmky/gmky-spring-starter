package gmky.core.mapper;

import gmky.core.api.model.UpdateUserReq;
import gmky.core.api.model.UserDTO;
import gmky.core.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface UserMapper extends EntityMapper<UserDTO, User> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(@MappingTarget User target, UpdateUserReq req);
}
