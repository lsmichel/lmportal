package com.lm.portal.service.mapper;


import com.lm.portal.domain.*;
import com.lm.portal.service.dto.TenantSuperAdminDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenantSuperAdmin} and its DTO {@link TenantSuperAdminDTO}.
 */
@Mapper(componentModel = "spring", uses = {TenantMapper.class})
public interface TenantSuperAdminMapper extends EntityMapper<TenantSuperAdminDTO, TenantSuperAdmin> {

    @Mapping(source = "tenant.id", target = "tenantId")
    TenantSuperAdminDTO toDto(TenantSuperAdmin tenantSuperAdmin);

    @Mapping(source = "tenantId", target = "tenant")
    TenantSuperAdmin toEntity(TenantSuperAdminDTO tenantSuperAdminDTO);

    default TenantSuperAdmin fromId(Long id) {
        if (id == null) {
            return null;
        }
        TenantSuperAdmin tenantSuperAdmin = new TenantSuperAdmin();
        tenantSuperAdmin.setId(id);
        return tenantSuperAdmin;
    }
}
