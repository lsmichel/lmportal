import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ITenantSuperAdmin } from 'app/shared/model/tenant-super-admin.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './tenant-super-admin.reducer';

export interface ITenantSuperAdminDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TenantSuperAdminDeleteDialog = (props: ITenantSuperAdminDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/tenant-super-admin' + props.location.search);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.tenantSuperAdminEntity.id);
  };

  const { tenantSuperAdminEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="lmPortal2App.tenantSuperAdmin.delete.question">
        <Translate contentKey="lmPortal2App.tenantSuperAdmin.delete.question" interpolate={{ id: tenantSuperAdminEntity.id }}>
          Are you sure you want to delete this TenantSuperAdmin?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-tenantSuperAdmin" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ tenantSuperAdmin }: IRootState) => ({
  tenantSuperAdminEntity: tenantSuperAdmin.entity,
  updateSuccess: tenantSuperAdmin.updateSuccess
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TenantSuperAdminDeleteDialog);
