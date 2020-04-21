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
 * Criteria class for the {@link com.lm.portal.domain.TenantUser} entity. This class is used
 * in {@link com.lm.portal.web.rest.TenantUserResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /tenant-users?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TenantUserCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter firstNameTenantUser;

    private StringFilter lastNameTenantUser;

    private StringFilter userNameTenantUser;

    private StringFilter emailTenantUser;

    private StringFilter passwordTenantUser;

    private LongFilter tenantId;

    public TenantUserCriteria() {
    }

    public TenantUserCriteria(TenantUserCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.firstNameTenantUser = other.firstNameTenantUser == null ? null : other.firstNameTenantUser.copy();
        this.lastNameTenantUser = other.lastNameTenantUser == null ? null : other.lastNameTenantUser.copy();
        this.userNameTenantUser = other.userNameTenantUser == null ? null : other.userNameTenantUser.copy();
        this.emailTenantUser = other.emailTenantUser == null ? null : other.emailTenantUser.copy();
        this.passwordTenantUser = other.passwordTenantUser == null ? null : other.passwordTenantUser.copy();
        this.tenantId = other.tenantId == null ? null : other.tenantId.copy();
    }

    @Override
    public TenantUserCriteria copy() {
        return new TenantUserCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getFirstNameTenantUser() {
        return firstNameTenantUser;
    }

    public void setFirstNameTenantUser(StringFilter firstNameTenantUser) {
        this.firstNameTenantUser = firstNameTenantUser;
    }

    public StringFilter getLastNameTenantUser() {
        return lastNameTenantUser;
    }

    public void setLastNameTenantUser(StringFilter lastNameTenantUser) {
        this.lastNameTenantUser = lastNameTenantUser;
    }

    public StringFilter getUserNameTenantUser() {
        return userNameTenantUser;
    }

    public void setUserNameTenantUser(StringFilter userNameTenantUser) {
        this.userNameTenantUser = userNameTenantUser;
    }

    public StringFilter getEmailTenantUser() {
        return emailTenantUser;
    }

    public void setEmailTenantUser(StringFilter emailTenantUser) {
        this.emailTenantUser = emailTenantUser;
    }

    public StringFilter getPasswordTenantUser() {
        return passwordTenantUser;
    }

    public void setPasswordTenantUser(StringFilter passwordTenantUser) {
        this.passwordTenantUser = passwordTenantUser;
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
        final TenantUserCriteria that = (TenantUserCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(firstNameTenantUser, that.firstNameTenantUser) &&
            Objects.equals(lastNameTenantUser, that.lastNameTenantUser) &&
            Objects.equals(userNameTenantUser, that.userNameTenantUser) &&
            Objects.equals(emailTenantUser, that.emailTenantUser) &&
            Objects.equals(passwordTenantUser, that.passwordTenantUser) &&
            Objects.equals(tenantId, that.tenantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        firstNameTenantUser,
        lastNameTenantUser,
        userNameTenantUser,
        emailTenantUser,
        passwordTenantUser,
        tenantId
        );
    }

    @Override
    public String toString() {
        return "TenantUserCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (firstNameTenantUser != null ? "firstNameTenantUser=" + firstNameTenantUser + ", " : "") +
                (lastNameTenantUser != null ? "lastNameTenantUser=" + lastNameTenantUser + ", " : "") +
                (userNameTenantUser != null ? "userNameTenantUser=" + userNameTenantUser + ", " : "") +
                (emailTenantUser != null ? "emailTenantUser=" + emailTenantUser + ", " : "") +
                (passwordTenantUser != null ? "passwordTenantUser=" + passwordTenantUser + ", " : "") +
                (tenantId != null ? "tenantId=" + tenantId + ", " : "") +
            "}";
    }

}
