import React, { useState, useEffect } from 'react';
import './tenant.scss';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label,Input } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './tenant.reducer';
import { ITenant } from 'app/shared/model/tenant.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITenantUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TenantUpdate = (props: ITenantUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { tenantEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/tenant' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...tenantEntity,
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
        <span className="createTenant" />
      </Col>        
      <Col md="8">
      <Row className="justify-content-left">
        <Col md="12">
          <h2 id="lmPortal2App.tenant.home.createOrEditLabel">
            {/* <Translate contentKey="lmPortal2App.tenant.home.createOrEditLabel">Create or edit a Tenant</Translate> */}
            Cr&eacute;e votre site internet <strong className="texteAcc">professionnel</strong>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-left">
        <Col md="12">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : tenantEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="tenant-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="tenant-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Row>&nbsp;</Row>
               {/* <Label id="idTenantLabel" for="tenant-idTenant">
                  <Translate contentKey="lmPortal2App.tenant.idTenant">Id Tenant</Translate>
                </Label> 
              <AvField id="tenant-idTenant" type="text" name="idTenant" /> */}
              </AvGroup> 
      
              <AvGroup>
                <Label id="spaceNameTenantLabel" for="tenant-spaceNameTenant">
                  {/* <Translate contentKey="lmPortal2App.tenant.spaceNameTenant"> */}Nom de votre espace{/* </Translate> */}
                </Label>
                <AvField 
                id="tenant-spaceNameTenant" 
                type="text" 
                placeholder="monEntreprise"
                name="spaceNameTenant" 
                required
                errorMessage="Renseigner le nom de votre espace"
                validate={{
                  required: {value: true, errorMessage: 'Renseignez le nom de votre espace'},
                  minLength: {value: 3, errorMessage: 'La longueur du nom de votre espace  doit être comprise entre 3 et 12 caractères'},
                  maxLength: {value: 12, errorMessage: 'La longueur du nom de votre espace  doit être comprise entre 3 et 12 caractères'}
                }}
                
                
                />
              </AvGroup>
              <AvGroup>
                <Label id="phoneNumberTenantLabel" for="tenant-phoneNumberTenant">
                  {/* <Translate contentKey="lmPortal2App.tenant.phoneNumberTenant"> */}Num&eacute;ro de telephone{/* </Translate> */}
                </Label>
                <AvField id="tenant-phoneNumberTenant" type="text" name="phoneNumberTenant" 
                placeholder="+225xxxxxxxx"
                required
                errorMessage='Renseignez un numéro de téléphone valide'
                validate={{
                  required: {value: true, errorMessage: 'Renseignez un numéro de téléphone valide'},
                 pattern: {value:'([+][0-9]{10,16})$', errorMessage: "Entrez un numéro de télépnone valide ajouter l'indicatif (+225)" }
                }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="webSiteTenantLabel" for="tenant-webSiteTenant">
                  {/* <Translate contentKey="lmPortal2App.tenant.webSiteTenant"></Translate> */}
                  Renseigner votre site institutionnel
                </Label>
                <AvField id="tenant-webSiteTenant" type="text" name="webSiteTenant" 
                placeholder="www.monsiteinstitutionel.com"
                required={false}
                errorMessage="Entrez un site correcte"
                valide={{
                  required: {value: false, errorMessage:""},
                    pattern:{value:"^(http://www.|https://www.|http://|https://)?[a-z0-9]+([-.]{1}[a-z0-9]+)*.[a-z]{2,5}(:[0-9]{1,5})?(/.*)?$",
                    errorMessage: "Entrez un site correcte" 
                  }

                }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="emailTenantLabel" for="tenant-emailTenant">
                  {/* <Translate contentKey="lmPortal2App.tenant.emailTenant"> */}Email{/* </Translate> */}
                </Label>
                <AvField 
                 id="tenant-emailTenant" 
                 type="email" 
                 placeholder="exemple@mailbox.com"
                 name="emailTenant" 
                 required
                 errorMessage="Renseignez une adresse Email valide"
                 validate={{
                  required:  {value: true, errorMessage: 'Renseignez votre adresse Email'},
                  minLength: {value: 6, errorMessage: 'La longueur de votre adresse Email  doit être comprise entre 6 et 16 caractères'},
                  maxLength: {value: 32, errorMessage: 'La longueur de votre adresse Email  doit être comprise entre 6 et 16 caractères'}
                    }}
                 />
              </AvGroup>

              <AvGroup>
                <Label id="logoTenatLabel" for="tenant-logoTenat">
                  {/* <Translate contentKey="lmPortal2App.tenant.logoTenat"> */}Logo {/* </Translate> */}
                </Label>
                <AvInput id="tenant-logoTenat" type="text" name="logoTenat" /> {/* file type us required */}
              </AvGroup>
              <AvGroup>
                <Label id="facebookTenantLabel" for="tenant-facebookTenant">
                  {/* <Translate contentKey="lmPortal2App.tenant.facebookTenant"> */}Lien facebook {/* </Translate> */}
                </Label>
                <AvField id="tenant-facebookTenant" type="text" name="facebookTenant" />
              </AvGroup>
              <AvGroup>
                <Label id="twitterTenantLabel" for="tenant-twitterTenant">
                  {/* <Translate contentKey="lmPortal2App.tenant.twitterTenant">Twitter Tenant</Translate> */}
                  Lien Twitter 
                </Label>
                <AvField 
                id="tenant-twitterTenant"
                 type="text" 
                 name="twitterTenant" />
              </AvGroup>
              <AvGroup>
                <Label id="instagramTenantLabel" for="tenant-instagramTenant">
                  {/* <Translate contentKey="lmPortal2App.tenant.instagramTenant"></Translate> */}
                  Lien instagram
                </Label>
                <AvField id="tenant-instagramTenant" type="text" name="instagramTenant" />
              </AvGroup>
              <AvGroup>
                <Label id="youtubeTenantLabel" for="tenant-youtubeTenant">
                  {/* <Translate contentKey="lmPortal2App.tenant.youtubeTenant"></Translate> */}
                  Lien youtube 
                </Label>
                <AvField id="tenant-youtubeTenant" type="text" name="youtubeTenant" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/tenant" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  {/* <Translate contentKey="entity.action.back">Back</Translate> */}
                  Retour
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
      </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  tenantEntity: storeState.tenant.entity,
  loading: storeState.tenant.loading,
  updating: storeState.tenant.updating,
  updateSuccess: storeState.tenant.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TenantUpdate);
