package com.lm.portal.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.lm.portal.domain.TenantSuperAdmin} entity. This class is used
 * in {@link com.lm.portal.web.rest.TenantSuperAdminResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /tenant-super-admins?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TenantSuperAdminCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter firstNameTenantSuperAdmin;

    private StringFilter lastNameTenantSuperAdmin;

    private StringFilter userNameTenantSuperAdmin;

    private StringFilter emailTenantSuperAdmin;

    private StringFilter passwordTenantSuperAdmin;

    private LongFilter tenantId;

    public TenantSuperAdminCriteria() {
    }

    public TenantSuperAdminCriteria(TenantSuperAdminCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.firstNameTenantSuperAdmin = other.firstNameTenantSuperAdmin == null ? null : other.firstNameTenantSuperAdmin.copy();
        this.lastNameTenantSuperAdmin = other.lastNameTenantSuperAdmin == null ? null : other.lastNameTenantSuperAdmin.copy();
        this.userNameTenantSuperAdmin = other.userNameTenantSuperAdmin == null ? null : other.userNameTenantSuperAdmin.copy();
        this.emailTenantSuperAdmin = other.emailTenantSuperAdmin == null ? null : other.emailTenantSuperAdmin.copy();
        this.passwordTenantSuperAdmin = other.passwordTenantSuperAdmin == null ? null : other.passwordTenantSuperAdmin.copy();
        this.tenantId = other.tenantId == null ? null : other.tenantId.copy();
    }

    @Override
    public TenantSuperAdminCriteria copy() {
        return new TenantSuperAdminCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getFirstNameTenantSuperAdmin() {
        return firstNameTenantSuperAdmin;
    }

    public void setFirstNameTenantSuperAdmin(StringFilter firstNameTenantSuperAdmin) {
        this.firstNameTenantSuperAdmin = firstNameTenantSuperAdmin;
    }

    public StringFilter getLastNameTenantSuperAdmin() {
        return lastNameTenantSuperAdmin;
    }

    public void setLastNameTenantSuperAdmin(StringFilter lastNameTenantSuperAdmin) {
        this.lastNameTenantSuperAdmin = lastNameTenantSuperAdmin;
    }

    public StringFilter getUserNameTenantSuperAdmin() {
        return userNameTenantSuperAdmin;
    }

    public void setUserNameTenantSuperAdmin(StringFilter userNameTenantSuperAdmin) {
        this.userNameTenantSuperAdmin = userNameTenantSuperAdmin;
    }

    public StringFilter getEmailTenantSuperAdmin() {
        return emailTenantSuperAdmin;
    }

    public void setEmailTenantSuperAdmin(StringFilter emailTenantSuperAdmin) {
        this.emailTenantSuperAdmin = emailTenantSuperAdmin;
    }

    public StringFilter getPasswordTenantSuperAdmin() {
        return passwordTenantSuperAdmin;
    }

    public void setPasswordTenantSuperAdmin(StringFilter passwordTenantSuperAdmin) {
        this.passwordTenantSuperAdmin = passwordTenantSuperAdmin;
    }

    public LongFilter getTenantId() {
        return tenantId;
    }

    public void setTenantId(LongFilter tenantId) {
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
        final TenantSuperAdminCriteria that = (TenantSuperAdminCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(firstNameTenantSuperAdmin, that.firstNameTenantSuperAdmin) &&
            Objects.equals(lastNameTenantSuperAdmin, that.lastNameTenantSuperAdmin) &&
            Objects.equals(userNameTenantSuperAdmin, that.userNameTenantSuperAdmin) &&
            Objects.equals(emailTenantSuperAdmin, that.emailTenantSuperAdmin) &&
            Objects.equals(passwordTenantSuperAdmin, that.passwordTenantSuperAdmin) &&
            Objects.equals(tenantId, that.tenantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        firstNameTenantSuperAdmin,
        lastNameTenantSuperAdmin,
        userNameTenantSuperAdmin,
        emailTenantSuperAdmin,
        passwordTenantSuperAdmin,
        tenantId
        );
    }

    @Override
    public String toString() {
        return "TenantSuperAdminCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (firstNameTenantSuperAdmin != null ? "firstNameTenantSuperAdmin=" + firstNameTenantSuperAdmin + ", " : "") +
                (lastNameTenantSuperAdmin != null ? "lastNameTenantSuperAdmin=" + lastNameTenantSuperAdmin + ", " : "") +
                (userNameTenantSuperAdmin != null ? "userNameTenantSuperAdmin=" + userNameTenantSuperAdmin + ", " : "") +
                (emailTenantSuperAdmin != null ? "emailTenantSuperAdmin=" + emailTenantSuperAdmin + ", " : "") +
                (passwordTenantSuperAdmin != null ? "passwordTenantSuperAdmin=" + passwordTenantSuperAdmin + ", " : "") +
                (tenantId != null ? "tenantId=" + tenantId + ", " : "") +
            "}";
    }

}
