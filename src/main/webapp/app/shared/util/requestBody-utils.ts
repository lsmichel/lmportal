export const CreateTenantWithAdmin = entity => {
  const queryBody = {
    tenantDTO: {
      emailTenant: entity.emailTenant,
      facebookTenant: entity.facebookTenant,
      instagramTenant: entity.instagramTenant,
      logoTenant: entity.logoTenant,
      phoneNumberTenant: entity.phoneNumberTenant,
      spaceNameTenant: entity.spaceNameTenant,
      twitterTenant: entity.twitterTenant,
      webSiteTenant: entity.webSiteTenant,
      youtubeTenant: entity.youtubeTenant
    },
    tenantSuperAdminInfo: {
      emailTenantSuperAdmin: entity.emailTenantSuperAdmin,
      firstNameTenantSuperAdmin: entity.firstNameTenantSuperAdmin,
      lastNameTenantSuperAdmin: entity.lastNameTenantSuperAdmin,
      passwordTenantSuperAdmin: entity.passwordTenantSuperAdmin,
      userNameTenantSuperAdmin: entity.userNameTenantSuperAdmin
    }
  };

  return queryBody;
};
