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
 * Criteria class for the {@link com.lm.portal.domain.Tenant} entity. This class is used
 * in {@link com.lm.portal.web.rest.TenantResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /tenants?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TenantCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter idTenant;

    private StringFilter logoTenat;

    private StringFilter spaceNameTenant;

    private StringFilter phoneNumberTenant;

    private StringFilter webSiteTenant;

    private StringFilter emailTenant;

    private StringFilter facebookTenant;

    private StringFilter twitterTenant;

    private StringFilter instagramTenant;

    private StringFilter youtubeTenant;

    private LongFilter tenantSuperAdminId;

    private LongFilter tenantUserId;

    private LongFilter siteId;

    public TenantCriteria() {
    }

    public TenantCriteria(TenantCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.idTenant = other.idTenant == null ? null : other.idTenant.copy();
        this.logoTenat = other.logoTenat == null ? null : other.logoTenat.copy();
        this.spaceNameTenant = other.spaceNameTenant == null ? null : other.spaceNameTenant.copy();
        this.phoneNumberTenant = other.phoneNumberTenant == null ? null : other.phoneNumberTenant.copy();
        this.webSiteTenant = other.webSiteTenant == null ? null : other.webSiteTenant.copy();
        this.emailTenant = other.emailTenant == null ? null : other.emailTenant.copy();
        this.facebookTenant = other.facebookTenant == null ? null : other.facebookTenant.copy();
        this.twitterTenant = other.twitterTenant == null ? null : other.twitterTenant.copy();
        this.instagramTenant = other.instagramTenant == null ? null : other.instagramTenant.copy();
        this.youtubeTenant = other.youtubeTenant == null ? null : other.youtubeTenant.copy();
        this.tenantSuperAdminId = other.tenantSuperAdminId == null ? null : other.tenantSuperAdminId.copy();
        this.tenantUserId = other.tenantUserId == null ? null : other.tenantUserId.copy();
        this.siteId = other.siteId == null ? null : other.siteId.copy();
    }

    @Override
    public TenantCriteria copy() {
        return new TenantCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getIdTenant() {
        return idTenant;
    }

    public void setIdTenant(StringFilter idTenant) {
        this.idTenant = idTenant;
    }

    public StringFilter getLogoTenat() {
        return logoTenat;
    }

    public void setLogoTenat(StringFilter logoTenat) {
        this.logoTenat = logoTenat;
    }

    public StringFilter getSpaceNameTenant() {
        return spaceNameTenant;
    }

    public void setSpaceNameTenant(StringFilter spaceNameTenant) {
        this.spaceNameTenant = spaceNameTenant;
    }

    public StringFilter getPhoneNumberTenant() {
        return phoneNumberTenant;
    }

    public void setPhoneNumberTenant(StringFilter phoneNumberTenant) {
        this.phoneNumberTenant = phoneNumberTenant;
    }

    public StringFilter getWebSiteTenant() {
        return webSiteTenant;
    }

    public void setWebSiteTenant(StringFilter webSiteTenant) {
        this.webSiteTenant = webSiteTenant;
    }

    public StringFilter getEmailTenant() {
        return emailTenant;
    }

    public void setEmailTenant(StringFilter emailTenant) {
        this.emailTenant = emailTenant;
    }

    public StringFilter getFacebookTenant() {
        return facebookTenant;
    }

    public void setFacebookTenant(StringFilter facebookTenant) {
        this.facebookTenant = facebookTenant;
    }

    public StringFilter getTwitterTenant() {
        return twitterTenant;
    }

    public void setTwitterTenant(StringFilter twitterTenant) {
        this.twitterTenant = twitterTenant;
    }

    public StringFilter getInstagramTenant() {
        return instagramTenant;
    }

    public void setInstagramTenant(StringFilter instagramTenant) {
        this.instagramTenant = instagramTenant;
    }

    public StringFilter getYoutubeTenant() {
        return youtubeTenant;
    }

    public void setYoutubeTenant(StringFilter youtubeTenant) {
        this.youtubeTenant = youtubeTenant;
    }

    public LongFilter getTenantSuperAdminId() {
        return tenantSuperAdminId;
    }

    public void setTenantSuperAdminId(LongFilter tenantSuperAdminId) {
        this.tenantSuperAdminId = tenantSuperAdminId;
    }

    public LongFilter getTenantUserId() {
        return tenantUserId;
    }

    public void setTenantUserId(LongFilter tenantUserId) {
        this.tenantUserId = tenantUserId;
    }

    public LongFilter getSiteId() {
        return siteId;
    }

    public void setSiteId(LongFilter siteId) {
        this.siteId = siteId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TenantCriteria that = (TenantCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(idTenant, that.idTenant) &&
            Objects.equals(logoTenat, that.logoTenat) &&
            Objects.equals(spaceNameTenant, that.spaceNameTenant) &&
            Objects.equals(phoneNumberTenant, that.phoneNumberTenant) &&
            Objects.equals(webSiteTenant, that.webSiteTenant) &&
            Objects.equals(emailTenant, that.emailTenant) &&
            Objects.equals(facebookTenant, that.facebookTenant) &&
            Objects.equals(twitterTenant, that.twitterTenant) &&
            Objects.equals(instagramTenant, that.instagramTenant) &&
            Objects.equals(youtubeTenant, that.youtubeTenant) &&
            Objects.equals(tenantSuperAdminId, that.tenantSuperAdminId) &&
            Objects.equals(tenantUserId, that.tenantUserId) &&
            Objects.equals(siteId, that.siteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        idTenant,
        logoTenat,
        spaceNameTenant,
        phoneNumberTenant,
        webSiteTenant,
        emailTenant,
        facebookTenant,
        twitterTenant,
        instagramTenant,
        youtubeTenant,
        tenantSuperAdminId,
        tenantUserId,
        siteId
        );
    }

    @Override
    public String toString() {
        return "TenantCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idTenant != null ? "idTenant=" + idTenant + ", " : "") +
                (logoTenat != null ? "logoTenat=" + logoTenat + ", " : "") +
                (spaceNameTenant != null ? "spaceNameTenant=" + spaceNameTenant + ", " : "") +
                (phoneNumberTenant != null ? "phoneNumberTenant=" + phoneNumberTenant + ", " : "") +
                (webSiteTenant != null ? "webSiteTenant=" + webSiteTenant + ", " : "") +
                (emailTenant != null ? "emailTenant=" + emailTenant + ", " : "") +
                (facebookTenant != null ? "facebookTenant=" + facebookTenant + ", " : "") +
                (twitterTenant != null ? "twitterTenant=" + twitterTenant + ", " : "") +
                (instagramTenant != null ? "instagramTenant=" + instagramTenant + ", " : "") +
                (youtubeTenant != null ? "youtubeTenant=" + youtubeTenant + ", " : "") +
                (tenantSuperAdminId != null ? "tenantSuperAdminId=" + tenantSuperAdminId + ", " : "") +
                (tenantUserId != null ? "tenantUserId=" + tenantUserId + ", " : "") +
                (siteId != null ? "siteId=" + siteId + ", " : "") +
            "}";
    }

}
