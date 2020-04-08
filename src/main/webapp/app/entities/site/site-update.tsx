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
import { ILocation } from 'app/shared/model/location.model';
import { getEntities as getLocations } from 'app/entities/location/location.reducer';
import { getEntity, updateEntity, createEntity, reset } from './site.reducer';
import { ISite } from 'app/shared/model/site.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISiteUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SiteUpdate = (props: ISiteUpdateProps) => {
  const [tenantId, setTenantId] = useState('0');
  const [locationId, setLocationId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { siteEntity, tenants, locations, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/site' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getTenants();
    props.getLocations();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...siteEntity,
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
            <AvForm model={isNew ? {} : siteEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="site-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="site-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="libelleLabel" for="site-libelle">
                  <Translate contentKey="lmPortal2App.site.libelle">Libelle</Translate>
                </Label>
                <AvField id="site-libelle" type="text" name="libelle" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="site-description">
                  <Translate contentKey="lmPortal2App.site.description">Description</Translate>
                </Label>
                <AvField id="site-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label for="site-tenant">
                  <Translate contentKey="lmPortal2App.site.tenant">Tenant</Translate>
                </Label>
                <AvInput id="site-tenant" type="select" className="form-control" name="tenantId">
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
              <AvGroup>
                <Label for="site-location">
                  <Translate contentKey="lmPortal2App.site.location">Location</Translate>
                </Label>
                <AvInput id="site-location" type="select" className="form-control" name="locationId">
                  <option value="" key="0" />
                  {locations
                    ? locations.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/site" replace color="info">
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
  locations: storeState.location.entities,
  siteEntity: storeState.site.entity,
  loading: storeState.site.loading,
  updating: storeState.site.updating,
  updateSuccess: storeState.site.updateSuccess
});

const mapDispatchToProps = {
  getTenants,
  getLocations,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SiteUpdate);
