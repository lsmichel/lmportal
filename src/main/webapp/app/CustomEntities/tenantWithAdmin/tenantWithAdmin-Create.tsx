import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';
// import { getEntity, updateEntity, createEntity, reset } from './tenantWithAdmin.reducer';
import {  createEntity } from './tenantWithAdmin.reducer';
import { ITenantSuperAdminInfo } from 'app/shared/CustomModel/tenant-superAdmin-info.model';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';


export interface ITenantWithAdminProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TenantWithAdminUpdate = (props: ITenantWithAdminProps) => {
 

  const { tenantWithAdminEntity, loading } = props;

  const handleClose = () => {
    props.history.push('/inscription' );
  };

  useEffect(() => {
    // if (isNew) {
    //   props.reset();
    // } else {
    //   props.getEntity(props.match.params.id);
    // }

    // props.getTenants();
    // props.getLocations();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...tenantWithAdminEntity,
        ...values
      };
        props.createEntity(entity);
      }
    };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="lmPortal2App.site.home.createOrEditLabel">
            <Translate contentKey="lmPortal2App.site.home.createOrEditLabel">Create or edit a Site</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={{}} onSubmit={saveEntity}>
              <AvGroup>
                <Label id="lastNameTenantSuperAdmin" for="site-libelle">
                  {/* <Translate contentKey="lmPortal2App.site.libelle">Libelle</Translate> */}
                  Nom
                </Label>
                <AvField id="lastNameTenantSuperAdmin" type="text" name="lastNameTenantSuperAdmin" />
              </AvGroup>
              <AvGroup>
                <Label id="firstNameTenantSuperAdmin" for="firstNameTenantSuperAdmin">
                  {/* <Translate contentKey="lmPortal2App.site.description">Description</Translate> */}
                  Pr&eacute;nom
                </Label>
                <AvField id="firstNameTenantSuperAdmin" type="text" name="Pr&eacute;nom" />
              </AvGroup>
              <AvGroup>
                <Label for="emailTenantSuperAdmin">
                  {/* <Translate contentKey="lmPortal2App.site.tenant">Tenant</Translate> */}
                  Email
                </Label>
                <AvField id="emailTenantSuperAdmin" type="text" className="form-control" name="emailTenantSuperAdmin"/>
              </AvGroup>
              <AvGroup>
                <Label for="userNameTenantSuperAdmin">
                  {/* <Translate contentKey="lmPortal2App.site.location">Location</Translate> */}
                Nom d&apos;utilisateur
                </Label>
                <AvField id="emailTenantSuperAdmin" type="text" className="form-control" name="emailTenantSuperAdmin"/>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/homePage" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" >
              {/* <Button color="primary" id="save-entity" type="submit" disabled={createEntity}> */}

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
  tenantWithAdminEntity: storeState.tenantWithAdmin.entity,
  loading: storeState.site.loading,
  updating: storeState.tenantWithAdmin.updating,
  updateSuccess: storeState.site.updateSuccess
});

const mapDispatchToProps = {
  // getTenants,
  // getLocations,
  // getEntity,
  // updateEntity,
    createEntity
  // reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TenantWithAdminUpdate);
