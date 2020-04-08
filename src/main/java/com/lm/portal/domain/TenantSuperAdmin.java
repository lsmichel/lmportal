package com.lm.portal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TenantSuperAdmin.
 */
@Entity
@Table(name = "tenant_super_admin")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TenantSuperAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name_tenant_super_admin")
    private String firstNameTenantSuperAdmin;

    @Column(name = "last_name_tenant_super_admin")
    private String lastNameTenantSuperAdmin;

    @Column(name = "user_name_tenant_super_admin")
    private String userNameTenantSuperAdmin;

    @Column(name = "email_tenant_super_admin")
    private String emailTenantSuperAdmin;

    @Column(name = "password_tenant_super_admin")
    private String passwordTenantSuperAdmin;

    @ManyToOne
    @JsonIgnoreProperties("tenantSuperAdmins")
    private Tenant tenant;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstNameTenantSuperAdmin() {
        return firstNameTenantSuperAdmin;
    }

    public TenantSuperAdmin firstNameTenantSuperAdmin(String firstNameTenantSuperAdmin) {
        this.firstNameTenantSuperAdmin = firstNameTenantSuperAdmin;
        return this;
    }

    public void setFirstNameTenantSuperAdmin(String firstNameTenantSuperAdmin) {
        this.firstNameTenantSuperAdmin = firstNameTenantSuperAdmin;
    }

    public String getLastNameTenantSuperAdmin() {
        return lastNameTenantSuperAdmin;
    }

    public TenantSuperAdmin lastNameTenantSuperAdmin(String lastNameTenantSuperAdmin) {
        this.lastNameTenantSuperAdmin = lastNameTenantSuperAdmin;
        return this;
    }

    public void setLastNameTenantSuperAdmin(String lastNameTenantSuperAdmin) {
        this.lastNameTenantSuperAdmin = lastNameTenantSuperAdmin;
    }

    public String getUserNameTenantSuperAdmin() {
        return userNameTenantSuperAdmin;
    }

    public TenantSuperAdmin userNameTenantSuperAdmin(String userNameTenantSuperAdmin) {
        this.userNameTenantSuperAdmin = userNameTenantSuperAdmin;
        return this;
    }

    public void setUserNameTenantSuperAdmin(String userNameTenantSuperAdmin) {
        this.userNameTenantSuperAdmin = userNameTenantSuperAdmin;
    }

    public String getEmailTenantSuperAdmin() {
        return emailTenantSuperAdmin;
    }

    public TenantSuperAdmin emailTenantSuperAdmin(String emailTenantSuperAdmin) {
        this.emailTenantSuperAdmin = emailTenantSuperAdmin;
        return this;
    }

    public void setEmailTenantSuperAdmin(String emailTenantSuperAdmin) {
        this.emailTenantSuperAdmin = emailTenantSuperAdmin;
    }

    public String getPasswordTenantSuperAdmin() {
        return passwordTenantSuperAdmin;
    }

    public TenantSuperAdmin passwordTenantSuperAdmin(String passwordTenantSuperAdmin) {
        this.passwordTenantSuperAdmin = passwordTenantSuperAdmin;
        return this;
    }

    public void setPasswordTenantSuperAdmin(String passwordTenantSuperAdmin) {
        this.passwordTenantSuperAdmin = passwordTenantSuperAdmin;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public TenantSuperAdmin tenant(Tenant tenant) {
        this.tenant = tenant;
        return this;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenantSuperAdmin)) {
            return false;
        }
        return id != null && id.equals(((TenantSuperAdmin) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TenantSuperAdmin{" +
            "id=" + getId() +
            ", firstNameTenantSuperAdmin='" + getFirstNameTenantSuperAdmin() + "'" +
            ", lastNameTenantSuperAdmin='" + getLastNameTenantSuperAdmin() + "'" +
            ", userNameTenantSuperAdmin='" + getUserNameTenantSuperAdmin() + "'" +
            ", emailTenantSuperAdmin='" + getEmailTenantSuperAdmin() + "'" +
            ", passwordTenantSuperAdmin='" + getPasswordTenantSuperAdmin() + "'" +
            "}";
    }
}
