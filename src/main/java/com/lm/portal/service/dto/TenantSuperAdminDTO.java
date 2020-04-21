package com.lm.portal.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.lm.portal.domain.TenantSuperAdmin} entity.
 */
public class TenantSuperAdminDTO implements Serializable {
    
    private Long id;

    private String firstNameTenantSuperAdmin;

    private String lastNameTenantSuperAdmin;

    private String userNameTenantSuperAdmin;

    private String emailTenantSuperAdmin;

    private String passwordTenantSuperAdmin;


    private Long tenantId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstNameTenantSuperAdmin() {
        return firstNameTenantSuperAdmin;
    }

    public void setFirstNameTenantSuperAdmin(String firstNameTenantSuperAdmin) {
        this.firstNameTenantSuperAdmin = firstNameTenantSuperAdmin;
    }

    public String getLastNameTenantSuperAdmin() {
        return lastNameTenantSuperAdmin;
    }

    public void setLastNameTenantSuperAdmin(String lastNameTenantSuperAdmin) {
        this.lastNameTenantSuperAdmin = lastNameTenantSuperAdmin;
    }

    public String getUserNameTenantSuperAdmin() {
        return userNameTenantSuperAdmin;
    }

    public void setUserNameTenantSuperAdmin(String userNameTenantSuperAdmin) {
        this.userNameTenantSuperAdmin = userNameTenantSuperAdmin;
    }

    public String getEmailTenantSuperAdmin() {
        return emailTenantSuperAdmin;
    }

    public void setEmailTenantSuperAdmin(String emailTenantSuperAdmin) {
        this.emailTenantSuperAdmin = emailTenantSuperAdmin;
    }

    public String getPasswordTenantSuperAdmin() {
        return passwordTenantSuperAdmin;
    }

    public void setPasswordTenantSuperAdmin(String passwordTenantSuperAdmin) {
        this.passwordTenantSuperAdmin = passwordTenantSuperAdmin;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TenantSuperAdminDTO tenantSuperAdminDTO = (TenantSuperAdminDTO) o;
        if (tenantSuperAdminDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tenantSuperAdminDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TenantSuperAdminDTO{" +
            "id=" + getId() +
            ", firstNameTenantSuperAdmin='" + getFirstNameTenantSuperAdmin() + "'" +
            ", lastNameTenantSuperAdmin='" + getLastNameTenantSuperAdmin() + "'" +
            ", userNameTenantSuperAdmin='" + getUserNameTenantSuperAdmin() + "'" +
            ", emailTenantSuperAdmin='" + getEmailTenantSuperAdmin() + "'" +
            ", passwordTenantSuperAdmin='" + getPasswordTenantSuperAdmin() + "'" +
            ", tenantId=" + getTenantId() +
            "}";
    }
}
