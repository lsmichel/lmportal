package com.lm.portal.service;

import com.lm.portal.service.dto.AuthenticateAdminDTO;
import com.lm.portal.service.dto.TenantSuperAdminDTO;

import com.lm.portal.service.dto.authenticateAdminResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.lm.portal.domain.TenantSuperAdmin}.
 */
public interface TenantSuperAdminService {

    /**
     * Save a tenantSuperAdmin.
     *
     * @param tenantSuperAdminDTO the entity to save.
     * @return the persisted entity.
     */
    TenantSuperAdminDTO save(TenantSuperAdminDTO tenantSuperAdminDTO);

    /**
     * Get all the tenantSuperAdmins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TenantSuperAdminDTO> findAll(Pageable pageable);

    /**
     * Get the "id" tenantSuperAdmin.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenantSuperAdminDTO> findOne(Long id);

    /**
     * Delete the "id" tenantSuperAdmin.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    authenticateAdminResponse  authenticateTenantSuperAdmin(AuthenticateAdminDTO  authenticateAdminDTO) ;
}
