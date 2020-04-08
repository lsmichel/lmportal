package com.lm.portal.service.impl;

import com.lm.portal.service.TenantSuperAdminService;
import com.lm.portal.domain.TenantSuperAdmin;
import com.lm.portal.repository.TenantSuperAdminRepository;
import com.lm.portal.service.dto.TenantSuperAdminDTO;
import com.lm.portal.service.mapper.TenantSuperAdminMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TenantSuperAdmin}.
 */
@Service
@Transactional
public class TenantSuperAdminServiceImpl implements TenantSuperAdminService {

    private final Logger log = LoggerFactory.getLogger(TenantSuperAdminServiceImpl.class);

    private final TenantSuperAdminRepository tenantSuperAdminRepository;

    private final TenantSuperAdminMapper tenantSuperAdminMapper;

    public TenantSuperAdminServiceImpl(TenantSuperAdminRepository tenantSuperAdminRepository, TenantSuperAdminMapper tenantSuperAdminMapper) {
        this.tenantSuperAdminRepository = tenantSuperAdminRepository;
        this.tenantSuperAdminMapper = tenantSuperAdminMapper;
    }

    /**
     * Save a tenantSuperAdmin.
     *
     * @param tenantSuperAdminDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TenantSuperAdminDTO save(TenantSuperAdminDTO tenantSuperAdminDTO) {
        log.debug("Request to save TenantSuperAdmin : {}", tenantSuperAdminDTO);
        TenantSuperAdmin tenantSuperAdmin = tenantSuperAdminMapper.toEntity(tenantSuperAdminDTO);
        tenantSuperAdmin = tenantSuperAdminRepository.save(tenantSuperAdmin);
        return tenantSuperAdminMapper.toDto(tenantSuperAdmin);
    }

    /**
     * Get all the tenantSuperAdmins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TenantSuperAdminDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TenantSuperAdmins");
        return tenantSuperAdminRepository.findAll(pageable)
            .map(tenantSuperAdminMapper::toDto);
    }

    /**
     * Get one tenantSuperAdmin by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TenantSuperAdminDTO> findOne(Long id) {
        log.debug("Request to get TenantSuperAdmin : {}", id);
        return tenantSuperAdminRepository.findById(id)
            .map(tenantSuperAdminMapper::toDto);
    }

    /**
     * Delete the tenantSuperAdmin by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenantSuperAdmin : {}", id);
        tenantSuperAdminRepository.deleteById(id);
    }
}
