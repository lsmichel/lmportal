package com.lm.portal.service;

import com.lm.portal.service.dto.TenantUserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.lm.portal.domain.TenantUser}.
 */
public interface TenantUserService {

    /**
     * Save a tenantUser.
     *
     * @param tenantUserDTO the entity to save.
     * @return the persisted entity.
     */
    TenantUserDTO save(TenantUserDTO tenantUserDTO);

    /**
     * Get all the tenantUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TenantUserDTO> findAll(Pageable pageable);

    /**
     * Get the "id" tenantUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TenantUserDTO> findOne(Long id);

    /**
     * Delete the "id" tenantUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
