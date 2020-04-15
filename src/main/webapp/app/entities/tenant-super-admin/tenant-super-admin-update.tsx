import React, { useState, useEffect } from 'react';
import './tenant-super-admin.scss';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';


import { ITenant } from 'app/shared/model/tenant.model';
import { getEntities as getTenants } from 'app/entities/tenant/tenant.reducer';
import { getEntity, updateEntity, createEntity, reset } from './tenant-super-admin.reducer';
import { ITenantSuperAdmin } from 'app/shared/model/tenant-super-admin.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITenantSuperAdminUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TenantSuperAdminUpdate = (props: ITenantSuperAdminUpdateProps) => {
  const [tenantId, setTenantId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { tenantSuperAdminEntity, tenants, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/tenant-super-admin' + props.location.search);
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
        ...tenantSuperAdminEntity,
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
      <Row>
      <Col md="4" className="pad">
        <span className="createTenantSA" />
      </Col>
      <Col md="8">
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="lmPortal2App.tenantSuperAdmin.home.createOrEditLabel">
            {/* <Translate contentKey="lmPortal2App.tenantSuperAdmin.home.createOrEditLabel">Create or edit a TenantSuperAdmin</Translate> */}
          Inscription
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : tenantSuperAdminEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="tenant-super-admin-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="tenant-super-admin-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
             

              <AvGroup>
                <Label id="firstNameTenantSuperAdminLabel" for="tenant-super-admin-firstNameTenantSuperAdmin">
                  <Translate contentKey="lmPortal2App.tenantSuperAdmin.firstNameTenantSuperAdmin">First Name Tenant Super Admin</Translate>
                </Label>
                <AvInput   id="tenant-super-admin-firstNameTenantSuperAdmin" type="text" name="firstNameTenantSuperAdmin" />
              </AvGroup>
              <AvGroup>
                <Label id="lastNameTenantSuperAdminLabel" for="tenant-super-admin-lastNameTenantSuperAdmin">
                  <Translate contentKey="lmPortal2App.tenantSuperAdmin.lastNameTenantSuperAdmin">Last Name Tenant Super Admin</Translate>
                </Label>
                <AvField id="tenant-super-admin-lastNameTenantSuperAdmin" type="text" name="lastNameTenantSuperAdmin" />
              </AvGroup>
              <AvGroup>
                <Label id="userNameTenantSuperAdminLabel" for="tenant-super-admin-userNameTenantSuperAdmin">
                  <Translate contentKey="lmPortal2App.tenantSuperAdmin.userNameTenantSuperAdmin">User Name Tenant Super Admin</Translate>
                </Label>
                <AvField id="tenant-super-admin-userNameTenantSuperAdmin" type="text" name="userNameTenantSuperAdmin" />
              </AvGroup>
              <AvGroup>
                <Label id="emailTenantSuperAdminLabel" for="tenant-super-admin-emailTenantSuperAdmin">
                  <Translate contentKey="lmPortal2App.tenantSuperAdmin.emailTenantSuperAdmin">Email Tenant Super Admin</Translate>
                </Label>
                <AvField id="tenant-super-admin-emailTenantSuperAdmin" type="text" name="emailTenantSuperAdmin" />
              </AvGroup>
              <AvGroup>
                <Label id="passwordTenantSuperAdminLabel" for="tenant-super-admin-passwordTenantSuperAdmin">
                  <Translate contentKey="lmPortal2App.tenantSuperAdmin.passwordTenantSuperAdmin">Password Tenant Super Admin</Translate>
                </Label>
                <AvField id="tenant-super-admin-passwordTenantSuperAdmin" type="password" name="passwordTenantSuperAdmin" />
              </AvGroup>
              <AvGroup>
                <Label for="tenant-super-admin-tenant">
                  <Translate contentKey="lmPortal2App.tenantSuperAdmin.tenant">Tenant</Translate>
                </Label>
                <AvInput id="tenant-super-admin-tenant" type="select" className="form-control" name="tenantId">
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
              <Button tag={Link} id="cancel-save" to="/tenant-super-admin" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  {/* <Translate contentKey="entity.action.back">Retour</Translate> */}Retour
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                {/* <Translate contentKey="entity.action.save">Save</Translate> */}Enregistrer
              </Button>
            </AvForm>
          )}
        </Col>
        
      </Row>
              

      </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  tenants: storeState.tenant.entities,
  tenantSuperAdminEntity: storeState.tenantSuperAdmin.entity,
  loading: storeState.tenantSuperAdmin.loading,
  updating: storeState.tenantSuperAdmin.updating,
  updateSuccess: storeState.tenantSuperAdmin.updateSuccess
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

export default connect(mapStateToProps, mapDispatchToProps)(TenantSuperAdminUpdate);
