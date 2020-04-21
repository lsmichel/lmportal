import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITenant } from 'app/shared/model/tenant.model';
import { getEntities as getTenants } from 'app/entities/tenant/tenant.reducer';
import { getEntity, updateEntity, createEntity, reset } from './tenant-user.reducer';
import { ITenantUser } from 'app/shared/model/tenant-user.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITenantUserUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TenantUserUpdate = (props: ITenantUserUpdateProps) => {
  const [tenantId, setTenantId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { tenantUserEntity, tenants, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/tenant-user' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTenants();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...tenantUserEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="lmPortal2App.tenantUser.home.createOrEditLabel">
            <Translate contentKey="lmPortal2App.tenantUser.home.createOrEditLabel">Create or edit a TenantUser</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : tenantUserEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="tenant-user-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="tenant-user-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="firstNameTenantUserLabel" for="tenant-user-firstNameTenantUser">
                  <Translate contentKey="lmPortal2App.tenantUser.firstNameTenantUser">First Name Tenant User</Translate>
                </Label>
                <AvField id="tenant-user-firstNameTenantUser" type="text" name="firstNameTenantUser" />
              </AvGroup>
              <AvGroup>
                <Label id="lastNameTenantUserLabel" for="tenant-user-lastNameTenantUser">
                  <Translate contentKey="lmPortal2App.tenantUser.lastNameTenantUser">Last Name Tenant User</Translate>
                </Label>
                <AvField id="tenant-user-lastNameTenantUser" type="text" name="lastNameTenantUser" />
              </AvGroup>
              <AvGroup>
                <Label id="userNameTenantUserLabel" for="tenant-user-userNameTenantUser">
                  <Translate contentKey="lmPortal2App.tenantUser.userNameTenantUser">User Name Tenant User</Translate>
                </Label>
                <AvField id="tenant-user-userNameTenantUser" type="text" name="userNameTenantUser" />
              </AvGroup>
              <AvGroup>
                <Label id="emailTenantUserLabel" for="tenant-user-emailTenantUser">
                  <Translate contentKey="lmPortal2App.tenantUser.emailTenantUser">Email Tenant User</Translate>
                </Label>
                <AvField id="tenant-user-emailTenantUser" type="text" name="emailTenantUser" />
              </AvGroup>
              <AvGroup>
                <Label id="passwordTenantUserLabel" for="tenant-user-passwordTenantUser">
                  <Translate contentKey="lmPortal2App.tenantUser.passwordTenantUser">Password Tenant User</Translate>
                </Label>
                <AvField id="tenant-user-passwordTenantUser" type="text" name="passwordTenantUser" />
              </AvGroup>
              <AvGroup>
                <Label for="tenant-user-tenant">
                  <Translate contentKey="lmPortal2App.tenantUser.tenant">Tenant</Translate>
                </Label>
                <AvInput id="tenant-user-tenant" type="select" className="form-control" name="tenantId">
                  <option value="" key="0" />
                  {tenants
                    ? tenants.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/tenant-user" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  tenants: storeState.tenant.entities,
  tenantUserEntity: storeState.tenantUser.entity,
  loading: storeState.tenantUser.loading,
  updating: storeState.tenantUser.updating,
  updateSuccess: storeState.tenantUser.updateSuccess
});

const mapDispatchToProps = {
  getTenants,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TenantUserUpdate);
