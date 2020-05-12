import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

 import Site from './';
// import SiteDetail from './site-detail';
import {TenantWithAdminUpdate} from './tenantWithAdmin-create';
// import SiteDeleteDialog from './site-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      {/* <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SiteDeleteDialog} /> */}
      <ErrorBoundaryRoute exact path={`${match.url}/inscription`} component={TenantWithAdminUpdate} />
      {/* <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SiteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SiteDetail} />
      <ErrorBoundaryRoute path={match.url} component={Site} /> */}
    </Switch>
  </>
);

export default Routes;
