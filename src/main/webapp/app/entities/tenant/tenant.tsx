import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './tenant.reducer';
import { ITenant } from 'app/shared/model/tenant.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ITenantProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Tenant = (props: ITenantProps) => {
  const [paginationState, setPaginationState] = useState(getSortState(props.location, ITEMS_PER_PAGE));

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
  };

  const sortEntities = () => {
    getAllEntities();
    props.history.push(
      `${props.location.pathname}?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`
    );
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage
    });

  const { tenantList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="tenant-heading">
        <Translate contentKey="lmPortal2App.tenant.home.title">Tenants</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="lmPortal2App.tenant.home.createLabel">Create new Tenant</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {tenantList && tenantList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idTenant')}>
                  <Translate contentKey="lmPortal2App.tenant.idTenant">Id Tenant</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('logoTenat')}>
                  <Translate contentKey="lmPortal2App.tenant.logoTenat">Logo Tenat</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('spaceNameTenant')}>
                  <Translate contentKey="lmPortal2App.tenant.spaceNameTenant">Space Name Tenant</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('phoneNumberTenant')}>
                  <Translate contentKey="lmPortal2App.tenant.phoneNumberTenant">Phone Number Tenant</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('webSiteTenant')}>
                  <Translate contentKey="lmPortal2App.tenant.webSiteTenant">Web Site Tenant</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('emailTenant')}>
                  <Translate contentKey="lmPortal2App.tenant.emailTenant">Email Tenant</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('facebookTenant')}>
                  <Translate contentKey="lmPortal2App.tenant.facebookTenant">Facebook Tenant</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('twitterTenant')}>
                  <Translate contentKey="lmPortal2App.tenant.twitterTenant">Twitter Tenant</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('instagramTenant')}>
                  <Translate contentKey="lmPortal2App.tenant.instagramTenant">Instagram Tenant</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('youtubeTenant')}>
                  <Translate contentKey="lmPortal2App.tenant.youtubeTenant">Youtube Tenant</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tenantList.map((tenant, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${tenant.id}`} color="link" size="sm">
                      {tenant.id}
                    </Button>
                  </td>
                  <td>{tenant.idTenant}</td>
                  <td>{tenant.logoTenat}</td>
                  <td>{tenant.spaceNameTenant}</td>
                  <td>{tenant.phoneNumberTenant}</td>
                  <td>{tenant.webSiteTenant}</td>
                  <td>{tenant.emailTenant}</td>
                  <td>{tenant.facebookTenant}</td>
                  <td>{tenant.twitterTenant}</td>
                  <td>{tenant.instagramTenant}</td>
                  <td>{tenant.youtubeTenant}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${tenant.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${tenant.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${tenant.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="lmPortal2App.tenant.home.notFound">No Tenants found</Translate>
            </div>
          )
        )}
      </div>
      <div className={tenantList && tenantList.length > 0 ? '' : 'd-none'}>
        <Row className="justify-content-center">
          <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
        </Row>
        <Row className="justify-content-center">
          <JhiPagination
            activePage={paginationState.activePage}
            onSelect={handlePagination}
            maxButtons={5}
            itemsPerPage={paginationState.itemsPerPage}
            totalItems={props.totalItems}
          />
        </Row>
      </div>
    </div>
  );
};

const mapStateToProps = ({ tenant }: IRootState) => ({
  tenantList: tenant.entities,
  loading: tenant.loading,
  totalItems: tenant.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Tenant);
