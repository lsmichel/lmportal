import { ITenantDto } from 'app/shared/CustomModel/tenant-dto-model';
import { ITenantSuperAdminInfo } from 'app/shared/CustomModel/tenant-superAdmin-info.model';

export interface ITenantWithAdmin {
  tenantDTO?: ITenantDto;
  tenantSuperAdminInfo?: ITenantSuperAdminInfo;
}

export const defaultValue: Readonly<ITenantWithAdmin> = {};
