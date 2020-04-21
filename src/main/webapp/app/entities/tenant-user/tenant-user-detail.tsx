import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tenant-user.reducer';
import { ITenantUser } from 'app/shared/model/tenant-user.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITenantUserDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TenantUserDetail = (props: ITenantUserDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tenantUserEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="lmPortal2App.tenantUser.detail.title">TenantUser</Translate> [<b>{tenantUserEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="firstNameTenantUser">
              <Translate contentKey="lmPortal2App.tenantUser.firstNameTenantUser">First Name Tenant User</Translate>
            </span>
          </dt>
          <dd>{tenantUserEntity.firstNameTenantUser}</dd>
          <dt>
            <span id="lastNameTenantUser">
              <Translate contentKey="lmPortal2App.tenantUser.lastNameTenantUser">Last Name Tenant User</Translate>
            </span>
          </dt>
          <dd>{tenantUserEntity.lastNameTenantUser}</dd>
          <dt>
            <span id="userNameTenantUser">
              <Translate contentKey="lmPortal2App.tenantUser.userNameTenantUser">User Name Tenant User</Translate>
            </span>
          </dt>
          <dd>{tenantUserEntity.userNameTenantUser}</dd>
          <dt>
            <span id="emailTenantUser">
              <Translate contentKey="lmPortal2App.tenantUser.emailTenantUser">Email Tenant User</Translate>
            </span>
          </dt>
          <dd>{tenantUserEntity.emailTenantUser}</dd>
          <dt>
            <span id="passwordTenantUser">
              <Translate contentKey="lmPortal2App.tenantUser.passwordTenantUser">Password Tenant User</Translate>
            </span>
          </dt>
          <dd>{tenantUserEntity.passwordTenantUser}</dd>
          <dt>
            <Translate contentKey="lmPortal2App.tenantUser.tenant">Tenant</Translate>
          </dt>
          <dd>{tenantUserEntity.tenantId ? tenantUserEntity.tenantId : ''}</dd>
        </dl>
        <Button tag={Link} to="/tenant-user" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tenant-user/${tenantUserEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ tenantUser }: IRootState) => ({
  tenantUserEntity: tenantUser.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TenantUserDetail);
