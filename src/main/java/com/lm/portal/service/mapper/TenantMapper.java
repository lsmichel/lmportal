package com.lm.portal.service.mapper;


import com.lm.portal.domain.*;
import com.lm.portal.service.dto.TenantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tenant} and its DTO {@link TenantDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenantMapper extends EntityMapper<TenantDTO, Tenant> {


    @Mapping(target = "tenantSuperAdmins", ignore = true)
    @Mapping(target = "removeTenantSuperAdmin", ignore = true)
    @Mapping(target = "tenantUsers", ignore = true)
    @Mapping(target = "removeTenantUser", ignore = true)
    @Mapping(target = "sites", ignore = true)
    @Mapping(target = "removeSite", ignore = true)
    Tenant toEntity(TenantDTO tenantDTO);

    default Tenant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tenant tenant = new Tenant();
        tenant.setId(id);
        return tenant;
    }
}
