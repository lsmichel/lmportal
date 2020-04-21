package com.lm.portal.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.lm.portal.domain.Tenant} entity.
 */
public class TenantDTO implements Serializable {
    
    private Long id;

    private String idTenant;

    private String logoTenat;

    private String spaceNameTenant;

    private String phoneNumberTenant;

    private String webSiteTenant;

    private String emailTenant;

    private String facebookTenant;

    private String twitterTenant;

    private String instagramTenant;

    private String youtubeTenant;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdTenant() {
        return idTenant;
    }

    public void setIdTenant(String idTenant) {
        this.idTenant = idTenant;
    }

    public String getLogoTenat() {
        return logoTenat;
    }

    public void setLogoTenat(String logoTenat) {
        this.logoTenat = logoTenat;
    }

    public String getSpaceNameTenant() {
        return spaceNameTenant;
    }

    public void setSpaceNameTenant(String spaceNameTenant) {
        this.spaceNameTenant = spaceNameTenant;
    }

    public String getPhoneNumberTenant() {
        return phoneNumberTenant;
    }

    public void setPhoneNumberTenant(String phoneNumberTenant) {
        this.phoneNumberTenant = phoneNumberTenant;
    }

    public String getWebSiteTenant() {
        return webSiteTenant;
    }

    public void setWebSiteTenant(String webSiteTenant) {
        this.webSiteTenant = webSiteTenant;
    }

    public String getEmailTenant() {
        return emailTenant;
    }

    public void setEmailTenant(String emailTenant) {
        this.emailTenant = emailTenant;
    }

    public String getFacebookTenant() {
        return facebookTenant;
    }

    public void setFacebookTenant(String facebookTenant) {
        this.facebookTenant = facebookTenant;
    }

    public String getTwitterTenant() {
        return twitterTenant;
    }

    public void setTwitterTenant(String twitterTenant) {
        this.twitterTenant = twitterTenant;
    }

    public String getInstagramTenant() {
        return instagramTenant;
    }

    public void setInstagramTenant(String instagramTenant) {
        this.instagramTenant = instagramTenant;
    }

    public String getYoutubeTenant() {
        return youtubeTenant;
    }

    public void setYoutubeTenant(String youtubeTenant) {
        this.youtubeTenant = youtubeTenant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TenantDTO tenantDTO = (TenantDTO) o;
        if (tenantDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tenantDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TenantDTO{" +
            "id=" + getId() +
            ", idTenant='" + getIdTenant() + "'" +
            ", logoTenat='" + getLogoTenat() + "'" +
            ", spaceNameTenant='" + getSpaceNameTenant() + "'" +
            ", phoneNumberTenant='" + getPhoneNumberTenant() + "'" +
            ", webSiteTenant='" + getWebSiteTenant() + "'" +
            ", emailTenant='" + getEmailTenant() + "'" +
            ", facebookTenant='" + getFacebookTenant() + "'" +
            ", twitterTenant='" + getTwitterTenant() + "'" +
            ", instagramTenant='" + getInstagramTenant() + "'" +
            ", youtubeTenant='" + getYoutubeTenant() + "'" +
            "}";
    }
}
