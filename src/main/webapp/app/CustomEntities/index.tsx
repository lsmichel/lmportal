import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TenantWithAdmin from './tenantWithAdmin';
// import Tenant from './tenant';
// import TenantUser from './tenant-user';
// import Site from './site';
// import Location from './location';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}TenantWithAdmin`} component={TenantWithAdmin} />
      {/* <ErrorBoundaryRoute path={`${match.url}tenant`} component={Tenant} />
      <ErrorBoundaryRoute path={`${match.url}tenant-user`} component={TenantUser} />
      <ErrorBoundaryRoute path={`${match.url}site`} component={Site} />
      <ErrorBoundaryRoute path={`${match.url}location`} component={Location} /> */}
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
