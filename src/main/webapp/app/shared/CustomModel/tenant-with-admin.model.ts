import { ITenantDto } from 'app/shared/CustomModel/tenant-dto-model';
import { ITenantSuperAdminInfo } from 'app/shared/CustomModel/tenant-superAdmin-info.model';

export interface ITenantWithAdmin {
  tenantDTO?: string;
  // tenantSuperAdminInfo?: string;
}

export const defaultValue: Readonly<ITenantWithAdmin> = {};
// tenantDTO?: ITenantDto;
//   tenantSuperAdminInfo?: ITenantSuperAdminInfo;
