package com.lm.portal.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Tenant.
 */
@Entity
@Table(name = "tenant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_tenant")
    private String idTenant;

    @Column(name = "logo_tenat")
    private String logoTenat;

    @Column(name = "space_name_tenant")
    private String spaceNameTenant;

    @Column(name = "phone_number_tenant")
    private String phoneNumberTenant;

    @Column(name = "web_site_tenant")
    private String webSiteTenant;

    @Column(name = "email_tenant")
    private String emailTenant;

    @Column(name = "facebook_tenant")
    private String facebookTenant;

    @Column(name = "twitter_tenant")
    private String twitterTenant;

    @Column(name = "instagram_tenant")
    private String instagramTenant;

    @Column(name = "youtube_tenant")
    private String youtubeTenant;

    @OneToMany(mappedBy = "tenant")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TenantSuperAdmin> tenantSuperAdmins = new HashSet<>();

    @OneToMany(mappedBy = "tenant")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TenantUser> tenantUsers = new HashSet<>();

    @OneToMany(mappedBy = "tenant")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Site> sites = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdTenant() {
        return idTenant;
    }

    public Tenant idTenant(String idTenant) {
        this.idTenant = idTenant;
        return this;
    }

    public void setIdTenant(String idTenant) {
        this.idTenant = idTenant;
    }

    public String getLogoTenat() {
        return logoTenat;
    }

    public Tenant logoTenat(String logoTenat) {
        this.logoTenat = logoTenat;
        return this;
    }

    public void setLogoTenat(String logoTenat) {
        this.logoTenat = logoTenat;
    }

    public String getSpaceNameTenant() {
        return spaceNameTenant;
    }

    public Tenant spaceNameTenant(String spaceNameTenant) {
        this.spaceNameTenant = spaceNameTenant;
        return this;
    }

    public void setSpaceNameTenant(String spaceNameTenant) {
        this.spaceNameTenant = spaceNameTenant;
    }

    public String getPhoneNumberTenant() {
        return phoneNumberTenant;
    }

    public Tenant phoneNumberTenant(String phoneNumberTenant) {
        this.phoneNumberTenant = phoneNumberTenant;
        return this;
    }

    public void setPhoneNumberTenant(String phoneNumberTenant) {
        this.phoneNumberTenant = phoneNumberTenant;
    }

    public String getWebSiteTenant() {
        return webSiteTenant;
    }

    public Tenant webSiteTenant(String webSiteTenant) {
        this.webSiteTenant = webSiteTenant;
        return this;
    }

    public void setWebSiteTenant(String webSiteTenant) {
        this.webSiteTenant = webSiteTenant;
    }

    public String getEmailTenant() {
        return emailTenant;
    }

    public Tenant emailTenant(String emailTenant) {
        this.emailTenant = emailTenant;
        return this;
    }

    public void setEmailTenant(String emailTenant) {
        this.emailTenant = emailTenant;
    }

    public String getFacebookTenant() {
        return facebookTenant;
    }

    public Tenant facebookTenant(String facebookTenant) {
        this.facebookTenant = facebookTenant;
        return this;
    }

    public void setFacebookTenant(String facebookTenant) {
        this.facebookTenant = facebookTenant;
    }

    public String getTwitterTenant() {
        return twitterTenant;
    }

    public Tenant twitterTenant(String twitterTenant) {
        this.twitterTenant = twitterTenant;
        return this;
    }

    public void setTwitterTenant(String twitterTenant) {
        this.twitterTenant = twitterTenant;
    }

    public String getInstagramTenant() {
        return instagramTenant;
    }

    public Tenant instagramTenant(String instagramTenant) {
        this.instagramTenant = instagramTenant;
        return this;
    }

    public void setInstagramTenant(String instagramTenant) {
        this.instagramTenant = instagramTenant;
    }

    public String getYoutubeTenant() {
        return youtubeTenant;
    }

    public Tenant youtubeTenant(String youtubeTenant) {
        this.youtubeTenant = youtubeTenant;
        return this;
    }

    public void setYoutubeTenant(String youtubeTenant) {
        this.youtubeTenant = youtubeTenant;
    }

    public Set<TenantSuperAdmin> getTenantSuperAdmins() {
        return tenantSuperAdmins;
    }

    public Tenant tenantSuperAdmins(Set<TenantSuperAdmin> tenantSuperAdmins) {
        this.tenantSuperAdmins = tenantSuperAdmins;
        return this;
    }

    public Tenant addTenantSuperAdmin(TenantSuperAdmin tenantSuperAdmin) {
        this.tenantSuperAdmins.add(tenantSuperAdmin);
        tenantSuperAdmin.setTenant(this);
        return this;
    }

    public Tenant removeTenantSuperAdmin(TenantSuperAdmin tenantSuperAdmin) {
        this.tenantSuperAdmins.remove(tenantSuperAdmin);
        tenantSuperAdmin.setTenant(null);
        return this;
    }

    public void setTenantSuperAdmins(Set<TenantSuperAdmin> tenantSuperAdmins) {
        this.tenantSuperAdmins = tenantSuperAdmins;
    }

    public Set<TenantUser> getTenantUsers() {
        return tenantUsers;
    }

    public Tenant tenantUsers(Set<TenantUser> tenantUsers) {
        this.tenantUsers = tenantUsers;
        return this;
    }

    public Tenant addTenantUser(TenantUser tenantUser) {
        this.tenantUsers.add(tenantUser);
        tenantUser.setTenant(this);
        return this;
    }

    public Tenant removeTenantUser(TenantUser tenantUser) {
        this.tenantUsers.remove(tenantUser);
        tenantUser.setTenant(null);
        return this;
    }

    public void setTenantUsers(Set<TenantUser> tenantUsers) {
        this.tenantUsers = tenantUsers;
    }

    public Set<Site> getSites() {
        return sites;
    }

    public Tenant sites(Set<Site> sites) {
        this.sites = sites;
        return this;
    }

    public Tenant addSite(Site site) {
        this.sites.add(site);
        site.setTenant(this);
        return this;
    }

    public Tenant removeSite(Site site) {
        this.sites.remove(site);
        site.setTenant(null);
        return this;
    }

    public void setSites(Set<Site> sites) {
        this.sites = sites;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tenant)) {
            return false;
        }
        return id != null && id.equals(((Tenant) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Tenant{" +
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
