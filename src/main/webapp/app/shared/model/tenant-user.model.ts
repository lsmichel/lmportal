export interface ITenantUser {
  id?: number;
  firstNameTenantUser?: string;
  lastNameTenantUser?: string;
  userNameTenantUser?: string;
  emailTenantUser?: string;
  passwordTenantUser?: string;
  tenantId?: number;
}

export const defaultValue: Readonly<ITenantUser> = {};
