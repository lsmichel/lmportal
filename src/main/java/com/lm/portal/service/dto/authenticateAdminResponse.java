package com.lm.portal.service.dto;

public class authenticateAdminResponse {
    private TenantWithAdminDTO tenantWithAdmin ;
    private  OperationStatus status ;

    public TenantWithAdminDTO getTenantWithAdmin() {
        return tenantWithAdmin;
    }

    public void setTenantWithAdmin(TenantWithAdminDTO tenantWithAdmin) {
        this.tenantWithAdmin = tenantWithAdmin;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public void setStatus(OperationStatus status) {
        this.status = status;
    }
}
