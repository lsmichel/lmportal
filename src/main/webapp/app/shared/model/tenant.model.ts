import { ITenantSuperAdmin } from 'app/shared/model/tenant-super-admin.model';
import { ITenantUser } from 'app/shared/model/tenant-user.model';
import { ISite } from 'app/shared/model/site.model';

export interface ITenant {
  id?: number;
  idTenant?: string;
  logoTenat?: string;
  spaceNameTenant?: string;
  phoneNumberTenant?: string;
  webSiteTenant?: string;
  emailTenant?: string;
  facebookTenant?: string;
  twitterTenant?: string;
  instagramTenant?: string;
  youtubeTenant?: string;
  tenantSuperAdmins?: ITenantSuperAdmin[];
  tenantUsers?: ITenantUser[];
  sites?: ISite[];
}

export const defaultValue: Readonly<ITenant> = {};
