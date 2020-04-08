package com.lm.portal.service.impl;

import com.lm.portal.service.TenantUserService;
import com.lm.portal.domain.TenantUser;
import com.lm.portal.repository.TenantUserRepository;
import com.lm.portal.service.dto.TenantUserDTO;
import com.lm.portal.service.mapper.TenantUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TenantUser}.
 */
@Service
@Transactional
public class TenantUserServiceImpl implements TenantUserService {

    private final Logger log = LoggerFactory.getLogger(TenantUserServiceImpl.class);

    private final TenantUserRepository tenantUserRepository;

    private final TenantUserMapper tenantUserMapper;

    public TenantUserServiceImpl(TenantUserRepository tenantUserRepository, TenantUserMapper tenantUserMapper) {
        this.tenantUserRepository = tenantUserRepository;
        this.tenantUserMapper = tenantUserMapper;
    }

    /**
     * Save a tenantUser.
     *
     * @param tenantUserDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TenantUserDTO save(TenantUserDTO tenantUserDTO) {
        log.debug("Request to save TenantUser : {}", tenantUserDTO);
        TenantUser tenantUser = tenantUserMapper.toEntity(tenantUserDTO);
        tenantUser = tenantUserRepository.save(tenantUser);
        return tenantUserMapper.toDto(tenantUser);
    }

    /**
     * Get all the tenantUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TenantUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TenantUsers");
        return tenantUserRepository.findAll(pageable)
            .map(tenantUserMapper::toDto);
    }

    /**
     * Get one tenantUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TenantUserDTO> findOne(Long id) {
        log.debug("Request to get TenantUser : {}", id);
        return tenantUserRepository.findById(id)
            .map(tenantUserMapper::toDto);
    }

    /**
     * Delete the tenantUser by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TenantUser : {}", id);
        tenantUserRepository.deleteById(id);
    }
}
