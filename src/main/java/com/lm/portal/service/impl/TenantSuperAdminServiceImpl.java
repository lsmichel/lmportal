package com.lm.portal.service.impl;

import com.lm.portal.service.TenantQueryService;
import com.lm.portal.service.TenantSuperAdminQueryService;
import com.lm.portal.service.TenantSuperAdminService;
import com.lm.portal.domain.TenantSuperAdmin;
import com.lm.portal.repository.TenantSuperAdminRepository;
import com.lm.portal.service.dto.*;
import com.lm.portal.service.mapper.TenantSuperAdminMapper;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List ;
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

    private final TenantSuperAdminQueryService  tenantSuperAdminQueryService ;

    private final TenantQueryService tenantQueryService ;

    public TenantSuperAdminServiceImpl(TenantSuperAdminRepository tenantSuperAdminRepository,
                                       TenantSuperAdminMapper tenantSuperAdminMapper ,
                                       TenantSuperAdminQueryService  tenantSuperAdminQueryService ,
                                       TenantQueryService tenantQueryService) {
        this.tenantSuperAdminRepository = tenantSuperAdminRepository;
        this.tenantSuperAdminMapper = tenantSuperAdminMapper;
        this.tenantSuperAdminQueryService=tenantSuperAdminQueryService;
        this.tenantQueryService = tenantQueryService;
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

    @Override
    @Transactional(readOnly = true)
    public  authenticateAdminResponse authenticateTenantSuperAdmin(AuthenticateAdminDTO authenticateAdminDTO){
        authenticateAdminResponse _response = new authenticateAdminResponse();
        OperationStatus status = new OperationStatus();
        status.setSystemError("");
        status.setHasError(true);
        status.setFonctionalError("");
        if(authenticateAdminDTO == null){
            status.setFonctionalError("request must not be null");
        }
        if(authenticateAdminDTO.getPassword()==null
            || authenticateAdminDTO.getPassword().isEmpty()
            || authenticateAdminDTO.getUsername()==null
            || authenticateAdminDTO.getUsername().isEmpty()){
            status.setFonctionalError("username or password must not be null or empty");
        }
        TenantSuperAdminCriteria criteria = new TenantSuperAdminCriteria() ;
        StringFilter usernameStringFilter = new StringFilter();
        usernameStringFilter.setEquals(authenticateAdminDTO.getUsername());
        StringFilter passwordStringFilter = new StringFilter();
        passwordStringFilter.setEquals(authenticateAdminDTO.getPassword());
        criteria.setUserNameTenantSuperAdmin(usernameStringFilter);
        criteria.setPasswordTenantSuperAdmin(passwordStringFilter);
        List<TenantSuperAdminDTO> _tenantSuperAdminQueryResponse = tenantSuperAdminQueryService.findByCriteria(criteria);
        if(_tenantSuperAdminQueryResponse==null || _tenantSuperAdminQueryResponse.isEmpty()){
            status.setFonctionalError("could not authenticate user");
        }
        else {
            TenantSuperAdminDTO _tenantSuperAdminDTO = _tenantSuperAdminQueryResponse.get(0);
            TenantCriteria _tenatCriteria = new TenantCriteria();
            LongFilter  _tenantAdminId = new  LongFilter();
            _tenantAdminId.setEquals(_tenantSuperAdminDTO.getId());
            _tenatCriteria.setTenantSuperAdminId(_tenantAdminId);
            List<TenantDTO> _tenantQueryResponse = tenantQueryService.findByCriteria(_tenatCriteria);
            if(_tenantQueryResponse==null || _tenantQueryResponse.isEmpty()){
                status.setFonctionalError("could not find admin tenant");
            }
            else {
                status.setHasError(false);
                TenantWithAdminDTO tenantWithAdmin = new TenantWithAdminDTO();
                tenantWithAdmin.setTenantSuperAdminInfo(_tenantSuperAdminDTO);
                tenantWithAdmin.setTenantDTO(_tenantQueryResponse.get(0));
                _response.setTenantWithAdmin(tenantWithAdmin);

            }
        }
        _response.setStatus(status);
        return _response;
    }
}
