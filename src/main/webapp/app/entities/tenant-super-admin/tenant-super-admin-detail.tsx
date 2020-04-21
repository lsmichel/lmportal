import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tenant-super-admin.reducer';
import { ITenantSuperAdmin } from 'app/shared/model/tenant-super-admin.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITenantSuperAdminDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TenantSuperAdminDetail = (props: ITenantSuperAdminDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tenantSuperAdminEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="lmPortal2App.tenantSuperAdmin.detail.title">TenantSuperAdmin</Translate> [
          <b>{tenantSuperAdminEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="firstNameTenantSuperAdmin">
              <Translate contentKey="lmPortal2App.tenantSuperAdmin.firstNameTenantSuperAdmin">First Name Tenant Super Admin</Translate>
            </span>
          </dt>
          <dd>{tenantSuperAdminEntity.firstNameTenantSuperAdmin}</dd>
          <dt>
            <span id="lastNameTenantSuperAdmin">
              <Translate contentKey="lmPortal2App.tenantSuperAdmin.lastNameTenantSuperAdmin">Last Name Tenant Super Admin</Translate>
            </span>
          </dt>
          <dd>{tenantSuperAdminEntity.lastNameTenantSuperAdmin}</dd>
          <dt>
            <span id="userNameTenantSuperAdmin">
              <Translate contentKey="lmPortal2App.tenantSuperAdmin.userNameTenantSuperAdmin">User Name Tenant Super Admin</Translate>
            </span>
          </dt>
          <dd>{tenantSuperAdminEntity.userNameTenantSuperAdmin}</dd>
          <dt>
            <span id="emailTenantSuperAdmin">
              <Translate contentKey="lmPortal2App.tenantSuperAdmin.emailTenantSuperAdmin">Email Tenant Super Admin</Translate>
            </span>
          </dt>
          <dd>{tenantSuperAdminEntity.emailTenantSuperAdmin}</dd>
          <dt>
            <span id="passwordTenantSuperAdmin">
              <Translate contentKey="lmPortal2App.tenantSuperAdmin.passwordTenantSuperAdmin">Password Tenant Super Admin</Translate>
            </span>
          </dt>
          <dd>{tenantSuperAdminEntity.passwordTenantSuperAdmin}</dd>
          <dt>
            <Translate contentKey="lmPortal2App.tenantSuperAdmin.tenant">Tenant</Translate>
          </dt>
          <dd>{tenantSuperAdminEntity.tenantId ? tenantSuperAdminEntity.tenantId : ''}</dd>
        </dl>
        <Button tag={Link} to="/tenant-super-admin" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tenant-super-admin/${tenantSuperAdminEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ tenantSuperAdmin }: IRootState) => ({
  tenantSuperAdminEntity: tenantSuperAdmin.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TenantSuperAdminDetail);
