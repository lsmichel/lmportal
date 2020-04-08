export interface ITenantSuperAdmin {
  id?: number;
  firstNameTenantSuperAdmin?: string;
  lastNameTenantSuperAdmin?: string;
  userNameTenantSuperAdmin?: string;
  emailTenantSuperAdmin?: string;
  passwordTenantSuperAdmin?: string;
  tenantId?: number;
}

export const defaultValue: Readonly<ITenantSuperAdmin> = {};
