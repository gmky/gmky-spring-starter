package gmky.core.config;

import gmky.core.GmkyCoreConfiguration;
import gmky.core.mapper.PrivilegeGroupMapper;
import gmky.core.mapper.PrivilegeMapper;
import gmky.core.mapper.RoleMapper;
import gmky.core.mapper.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(GmkyCoreConfiguration.class)
public class MapperConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public PrivilegeGroupMapper privilegeGroupMapper() {
        return Mappers.getMapper(PrivilegeGroupMapper.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public PrivilegeMapper privilegeMapper() {
        return Mappers.getMapper(PrivilegeMapper.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public RoleMapper roleMapper() {
        return Mappers.getMapper(RoleMapper.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }
}
