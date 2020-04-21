package com.lm.portal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TenantUser.
 */
@Entity
@Table(name = "tenant_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TenantUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name_tenant_user")
    private String firstNameTenantUser;

    @Column(name = "last_name_tenant_user")
    private String lastNameTenantUser;

    @Column(name = "user_name_tenant_user")
    private String userNameTenantUser;

    @Column(name = "email_tenant_user")
    private String emailTenantUser;

    @Column(name = "password_tenant_user")
    private String passwordTenantUser;

    @ManyToOne
    @JsonIgnoreProperties("tenantUsers")
    private Tenant tenant;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstNameTenantUser() {
        return firstNameTenantUser;
    }

    public TenantUser firstNameTenantUser(String firstNameTenantUser) {
        this.firstNameTenantUser = firstNameTenantUser;
        return this;
    }

    public void setFirstNameTenantUser(String firstNameTenantUser) {
        this.firstNameTenantUser = firstNameTenantUser;
    }

    public String getLastNameTenantUser() {
        return lastNameTenantUser;
    }

    public TenantUser lastNameTenantUser(String lastNameTenantUser) {
        this.lastNameTenantUser = lastNameTenantUser;
        return this;
    }

    public void setLastNameTenantUser(String lastNameTenantUser) {
        this.lastNameTenantUser = lastNameTenantUser;
    }

    public String getUserNameTenantUser() {
        return userNameTenantUser;
    }

    public TenantUser userNameTenantUser(String userNameTenantUser) {
        this.userNameTenantUser = userNameTenantUser;
        return this;
    }

    public void setUserNameTenantUser(String userNameTenantUser) {
        this.userNameTenantUser = userNameTenantUser;
    }

    public String getEmailTenantUser() {
        return emailTenantUser;
    }

    public TenantUser emailTenantUser(String emailTenantUser) {
        this.emailTenantUser = emailTenantUser;
        return this;
    }

    public void setEmailTenantUser(String emailTenantUser) {
        this.emailTenantUser = emailTenantUser;
    }

    public String getPasswordTenantUser() {
        return passwordTenantUser;
    }

    public TenantUser passwordTenantUser(String passwordTenantUser) {
        this.passwordTenantUser = passwordTenantUser;
        return this;
    }

    public void setPasswordTenantUser(String passwordTenantUser) {
        this.passwordTenantUser = passwordTenantUser;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public TenantUser tenant(Tenant tenant) {
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
        if (!(o instanceof TenantUser)) {
            return false;
        }
        return id != null && id.equals(((TenantUser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TenantUser{" +
            "id=" + getId() +
            ", firstNameTenantUser='" + getFirstNameTenantUser() + "'" +
            ", lastNameTenantUser='" + getLastNameTenantUser() + "'" +
            ", userNameTenantUser='" + getUserNameTenantUser() + "'" +
            ", emailTenantUser='" + getEmailTenantUser() + "'" +
            ", passwordTenantUser='" + getPasswordTenantUser() + "'" +
            "}";
    }
}
