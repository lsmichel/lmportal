import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { DynamicFooter } from './footer-tenant';
import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tenant.reducer';
import { ITenant } from 'app/shared/model/tenant.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITenantDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TenantDetail = (props: ITenantDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tenantEntity } = props;
  return (
    <>
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="lmPortal2App.tenant.detail.title">Tenant</Translate> [<b>{tenantEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="idTenant">
              <Translate contentKey="lmPortal2App.tenant.idTenant">Id Tenant</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.idTenant}</dd>
          <dt>
            <span id="logoTenat">
              <Translate contentKey="lmPortal2App.tenant.logoTenat">Logo Tenat</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.logoTenat}</dd>
          <dt>
            <span id="spaceNameTenant">
              <Translate contentKey="lmPortal2App.tenant.spaceNameTenant">Space Name Tenant</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.spaceNameTenant}</dd>
          <dt>
            <span id="phoneNumberTenant">
              <Translate contentKey="lmPortal2App.tenant.phoneNumberTenant">Phone Number Tenant</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.phoneNumberTenant}</dd>
          <dt>
            <span id="webSiteTenant">
              <Translate contentKey="lmPortal2App.tenant.webSiteTenant">Web Site Tenant</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.webSiteTenant}</dd>
          <dt>
            <span id="emailTenant">
              <Translate contentKey="lmPortal2App.tenant.emailTenant">Email Tenant</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.emailTenant}</dd>
          <dt>
            <span id="facebookTenant">
              <Translate contentKey="lmPortal2App.tenant.facebookTenant">Facebook Tenant</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.facebookTenant}</dd>
          <dt>
            <span id="twitterTenant">
              <Translate contentKey="lmPortal2App.tenant.twitterTenant">Twitter Tenant</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.twitterTenant}</dd>
          <dt>
            <span id="instagramTenant">
              <Translate contentKey="lmPortal2App.tenant.instagramTenant">Instagram Tenant</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.instagramTenant}</dd>
          <dt>
            <span id="youtubeTenant">
              <Translate contentKey="lmPortal2App.tenant.youtubeTenant">Youtube Tenant</Translate>
            </span>
          </dt>
          <dd>{tenantEntity.youtubeTenant}</dd>
        </dl>
        <Button tag={Link} to="/tenant" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tenant/${tenantEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
      <Row/>  
    </Row>
    <nav  className="navbar bottom navbar-light bg-dark">
  <a className="navbar-brand" href="#">Fixed bottom</a>
</nav>
   <DynamicFooter faceBookTenant={tenantEntity.facebookTenant}
  youtubeTenant={tenantEntity.youtubeTenant}
  instagramTenant={tenantEntity.instagramTenant}
  twitterTenant={tenantEntity.twitterTenant}
  /> 
    </>
  );
};

const mapStateToProps = ({ tenant }: IRootState) => ({
  tenantEntity: tenant.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TenantDetail);
