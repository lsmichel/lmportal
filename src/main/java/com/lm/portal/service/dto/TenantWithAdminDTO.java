package com.lm.portal.service.dto;

public class TenantWithAdminDTO {
    private TenantDTO TenantDTO ;
    private TenantSuperAdminDTO TenantSuperAdminInfo ;

    public com.lm.portal.service.dto.TenantDTO getTenantDTO() {
        return TenantDTO;
    }

    public void setTenantDTO(com.lm.portal.service.dto.TenantDTO tenantDTO) {
        TenantDTO = tenantDTO;
    }

    public TenantSuperAdminDTO getTenantSuperAdminInfo() {
        return TenantSuperAdminInfo;
    }

    public void setTenantSuperAdminInfo(TenantSuperAdminDTO tenantSuperAdminInfo) {
        TenantSuperAdminInfo = tenantSuperAdminInfo;
    }
}
