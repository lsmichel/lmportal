package com.lm.portal.service.mapper;


import com.lm.portal.domain.*;
import com.lm.portal.service.dto.TenantUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenantUser} and its DTO {@link TenantUserDTO}.
 */
@Mapper(componentModel = "spring", uses = {TenantMapper.class})
public interface TenantUserMapper extends EntityMapper<TenantUserDTO, TenantUser> {

    @Mapping(source = "tenant.id", target = "tenantId")
    TenantUserDTO toDto(TenantUser tenantUser);

    @Mapping(source = "tenantId", target = "tenant")
    TenantUser toEntity(TenantUserDTO tenantUserDTO);

    default TenantUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        TenantUser tenantUser = new TenantUser();
        tenantUser.setId(id);
        return tenantUser;
    }
}
