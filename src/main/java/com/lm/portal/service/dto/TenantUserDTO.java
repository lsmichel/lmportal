package com.lm.portal.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.lm.portal.domain.TenantUser} entity.
 */
public class TenantUserDTO implements Serializable {
    
    private Long id;

    private String firstNameTenantUser;

    private String lastNameTenantUser;

    private String userNameTenantUser;

    private String emailTenantUser;

    private String passwordTenantUser;


    private Long tenantId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstNameTenantUser() {
        return firstNameTenantUser;
    }

    public void setFirstNameTenantUser(String firstNameTenantUser) {
        this.firstNameTenantUser = firstNameTenantUser;
    }

    public String getLastNameTenantUser() {
        return lastNameTenantUser;
    }

    public void setLastNameTenantUser(String lastNameTenantUser) {
        this.lastNameTenantUser = lastNameTenantUser;
    }

    public String getUserNameTenantUser() {
        return userNameTenantUser;
    }

    public void setUserNameTenantUser(String userNameTenantUser) {
        this.userNameTenantUser = userNameTenantUser;
    }

    public String getEmailTenantUser() {
        return emailTenantUser;
    }

    public void setEmailTenantUser(String emailTenantUser) {
        this.emailTenantUser = emailTenantUser;
    }

    public String getPasswordTenantUser() {
        return passwordTenantUser;
    }

    public void setPasswordTenantUser(String passwordTenantUser) {
        this.passwordTenantUser = passwordTenantUser;
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

        TenantUserDTO tenantUserDTO = (TenantUserDTO) o;
        if (tenantUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tenantUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TenantUserDTO{" +
            "id=" + getId() +
            ", firstNameTenantUser='" + getFirstNameTenantUser() + "'" +
            ", lastNameTenantUser='" + getLastNameTenantUser() + "'" +
            ", userNameTenantUser='" + getUserNameTenantUser() + "'" +
            ", emailTenantUser='" + getEmailTenantUser() + "'" +
            ", passwordTenantUser='" + getPasswordTenantUser() + "'" +
            ", tenantId=" + getTenantId() +
            "}";
    }
}
