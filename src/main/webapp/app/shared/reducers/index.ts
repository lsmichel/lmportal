import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import tenantSuperAdmin, {
  TenantSuperAdminState
} from 'app/entities/tenant-super-admin/tenant-super-admin.reducer';
// prettier-ignore
import tenant, {
  TenantState
} from 'app/entities/tenant/tenant.reducer';
// prettier-ignore
import tenantUser, {
  TenantUserState
} from 'app/entities/tenant-user/tenant-user.reducer';
// prettier-ignore
import site, {
  SiteState
} from 'app/entities/site/site.reducer';
// prettier-ignore
import location, {
  LocationState
} from 'app/entities/location/location.reducer';

import tenantWithAdmin, { TenantWithAdminState } from 'app/CustomEntities/tenantWithAdmin/tenantWithAdmin.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly tenantSuperAdmin: TenantSuperAdminState;
  readonly tenant: TenantState;
  readonly tenantUser: TenantUserState;
  readonly site: SiteState;
  readonly location: LocationState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
  readonly tenantWithAdmin: TenantWithAdminState;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  tenantSuperAdmin,
  tenant,
  tenantUser,
  site,
  location,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
  tenantWithAdmin
});

export default rootReducer;
