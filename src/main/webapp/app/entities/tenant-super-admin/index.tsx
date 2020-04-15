import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TenantSuperAdmin from './tenant-super-admin';
import TenantSuperAdminDetail from './tenant-super-admin-detail';
import TenantSuperAdminUpdate from './tenant-super-admin-update';
import TenantSuperAdminDeleteDialog from './tenant-super-admin-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TenantSuperAdminDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TenantSuperAdminUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TenantSuperAdminUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TenantSuperAdminDetail} />
      <ErrorBoundaryRoute path={match.url} component={TenantSuperAdmin} />
    </Switch>
  </>
);

export default Routes;
