package com.lm.portal.service.impl;

import com.lm.portal.domain.TenantSuperAdmin;
import com.lm.portal.service.TenantService;
import com.lm.portal.domain.Tenant;
import com.lm.portal.repository.TenantRepository;
import com.lm.portal.service.TenantSuperAdminService;
import com.lm.portal.service.dto.*;
import com.lm.portal.service.mapper.TenantMapper;
import com.lm.portal.service.mapper.TenantSuperAdminMapper;
import com.lm.portal.service.utilities.GenerateTenantId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Tenant}.
 */
@Service
@Transactional
public class TenantServiceImpl implements TenantService {

    private final Logger log = LoggerFactory.getLogger(TenantServiceImpl.class);

    private final TenantRepository tenantRepository;

    private final TenantMapper tenantMapper;

    private final TenantSuperAdminService  tenantSuperAdminService;

    private final TenantSuperAdminMapper  tenantSuperAdminMapper ;

    public TenantServiceImpl(TenantRepository tenantRepository, TenantMapper tenantMapper ,
                             TenantSuperAdminService  tenantSuperAdminService ,
                             TenantSuperAdminMapper  tenantSuperAdminMapper) {
        this.tenantRepository = tenantRepository;
        this.tenantMapper = tenantMapper;
        this.tenantSuperAdminService = tenantSuperAdminService;
        this.tenantSuperAdminMapper = tenantSuperAdminMapper ;
    }

    /**
     * Save a tenant.
     *
     * @param tenantDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TenantDTO save(TenantDTO tenantDTO) {
        log.debug("Request to save Tenant : {}", tenantDTO);
        Tenant tenant = tenantMapper.toEntity(tenantDTO);
        tenant = tenantRepository.save(tenant);
        return tenantMapper.toDto(tenant);
    }

    /**
     * Get all the tenants.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TenantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tenants");
        return tenantRepository.findAll(pageable)
            .map(tenantMapper::toDto);
    }

    /**
     * Get one tenant by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TenantDTO> findOne(Long id) {
        log.debug("Request to get Tenant : {}", id);
        return tenantRepository.findById(id)
            .map(tenantMapper::toDto);
    }

    /**
     * Delete the tenant by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tenant : {}", id);
        tenantRepository.deleteById(id);
    }

    @Override
    public TenantWithAdminResponseDTO createTenantWithAdmin(TenantWithAdminDTO  tenantWithAdminDTO){

        TenantWithAdminResponseDTO _response = new TenantWithAdminResponseDTO();
        OperationStatus  status = new OperationStatus();
        status.setHasError(true);
        status.setFonctionalError("");
        status.setSystemError("");
        log.debug("REST request to create tenant with it admin : {}", tenantWithAdminDTO);

        if(tenantWithAdminDTO == null) {
            status.setFonctionalError("request must not be null");
            _response.setStatus(status);
            return _response;
        }

        if(tenantWithAdminDTO.getTenantDTO() == null){
            status.setFonctionalError("tenant must contain not be null");
            _response.setStatus(status);
            return _response;
        }

        if(tenantWithAdminDTO.getTenantSuperAdminInfo() == null){
            status.setFonctionalError("admin info  must not be null");
            _response.setStatus(status);
            return _response;
        }
        if(tenantWithAdminDTO.getTenantSuperAdminInfo().getUserNameTenantSuperAdmin() == null
            || tenantWithAdminDTO.getTenantSuperAdminInfo().getUserNameTenantSuperAdmin().isEmpty()
            || tenantWithAdminDTO.getTenantSuperAdminInfo().getPasswordTenantSuperAdmin() == null
            || tenantWithAdminDTO.getTenantSuperAdminInfo().getPasswordTenantSuperAdmin().isEmpty()){
            status.setFonctionalError("admin username or password must not be empty or null");
            _response.setStatus(status);
            return _response;
        }
        try {

            TenantSuperAdminDTO    saveTenantSuperAdminResponse =   tenantSuperAdminService
                .save(tenantWithAdminDTO.getTenantSuperAdminInfo());
            String tenant_Id = GenerateTenantId.getInstance().GenerateId();
            tenantWithAdminDTO.getTenantDTO().setIdTenant(tenant_Id);
            Tenant tenantTosave =  tenantMapper.toEntity(tenantWithAdminDTO.getTenantDTO()) ;
            tenantTosave.addTenantSuperAdmin(tenantSuperAdminMapper.toEntity(saveTenantSuperAdminResponse)) ;
            TenantDTO saveTenantResponse = this.save(tenantWithAdminDTO.getTenantDTO());
            status.setHasError(false);
            TenantWithAdminDTO tenantWithAdmin = new TenantWithAdminDTO();
            tenantWithAdmin.setTenantDTO(saveTenantResponse);
            tenantWithAdmin.setTenantSuperAdminInfo(saveTenantSuperAdminResponse);
            _response.setTenantWithAdmin(tenantWithAdmin);
            _response.setStatus(status);
            return _response;
        }
        catch(Exception ex){
            status.setSystemError(ex.getMessage());
            _response.setStatus(status);
            return _response;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public  TenantWithAdminResponseDTO updateTenantAdmin(Long id , TenantSuperAdminDTO tenantSuperAdminDTO){
        TenantWithAdminResponseDTO _response = new TenantWithAdminResponseDTO();
        OperationStatus  status = new OperationStatus();
        status.setHasError(true);
        status.setFonctionalError("");
        status.setSystemError("");
        if(id==null){
            status.setFonctionalError("tenant id must not be null");
            _response.setStatus(status);
            return _response;
        }
        Optional<Tenant> optional_tenat = tenantRepository.findById(id);
        if(!optional_tenat.isPresent()){
            status.setFonctionalError("could not find tenant");
            _response.setStatus(status);
            return _response;
        }
        Tenant tenant = optional_tenat.get() ;
        Optional<TenantSuperAdmin> optional_tenantSuperAdmin = tenant.getTenantSuperAdmins().stream().findFirst();
        if(!optional_tenantSuperAdmin.isPresent()){
            status.setFonctionalError("tenant admin could not be found");
            _response.setStatus(status);
            return _response;
        }
        TenantSuperAdmin  tenantSuperAdmin = optional_tenantSuperAdmin.get() ;
        tenantSuperAdminDTO.setId(tenantSuperAdmin.getId());
        try{
            TenantSuperAdminDTO _tenantSuperAdminDTO = tenantSuperAdminService.save(tenantSuperAdminDTO);
            TenantDTO _tenantResponse = tenantMapper.toDto(tenant);
            status.setHasError(false);
            TenantWithAdminDTO tenantWithAdmin = new TenantWithAdminDTO();
            tenantWithAdmin.setTenantDTO(_tenantResponse);
            tenantWithAdmin.setTenantSuperAdminInfo(_tenantSuperAdminDTO);
            return _response;
        }
        catch(Exception ex){
            status.setSystemError(ex.getMessage());
            _response.setStatus(status);
            return _response;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public TenantWithAdminResponseDTO getTenantWithTenantInfo( Long id) {
        TenantWithAdminResponseDTO _response = new TenantWithAdminResponseDTO();
        OperationStatus  status = new OperationStatus();
        status.setHasError(true);
        status.setFonctionalError("");
        status.setSystemError("");
        if(id==null){
            status.setFonctionalError("tenant id must not be null");
            _response.setStatus(status);
            return _response;
        }
        Optional<Tenant> optional_tenat = tenantRepository.findById(id);
        if(!optional_tenat.isPresent()){
            status.setFonctionalError("could not find tenant");
            _response.setStatus(status);
            return _response;
        }
        Tenant tenant = optional_tenat.get() ;
        Optional<TenantSuperAdmin> optional_tenantSuperAdmin = tenant.getTenantSuperAdmins().stream().findFirst();
        TenantDTO _tenantResponse = tenantMapper.toDto(tenant);
        status.setHasError(false);
        TenantWithAdminDTO tenantWithAdmin = new TenantWithAdminDTO();
        tenantWithAdmin.setTenantDTO(_tenantResponse);
        if(optional_tenantSuperAdmin.isPresent())
        tenantWithAdmin.setTenantSuperAdminInfo(tenantSuperAdminMapper.toDto(optional_tenantSuperAdmin.get()));
        return _response;
    }
}
