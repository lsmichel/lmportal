package com.lm.portal.web.rest;

import com.lm.portal.service.TenantService;
import com.lm.portal.service.dto.*;
import com.lm.portal.web.rest.errors.BadRequestAlertException;
import com.lm.portal.service.TenantQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lm.portal.domain.Tenant}.
 */
@RestController
@RequestMapping("/api")
public class TenantResource {

    private final Logger log = LoggerFactory.getLogger(TenantResource.class);

    private static final String ENTITY_NAME = "tenant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenantService tenantService;

    private final TenantQueryService tenantQueryService;

    public TenantResource(TenantService tenantService, TenantQueryService tenantQueryService) {
        this.tenantService = tenantService;
        this.tenantQueryService = tenantQueryService;
    }

    /**
     * {@code POST  /tenants} : Create a new tenant.
     *
     * @param tenantDTO the tenantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenantDTO, or with status {@code 400 (Bad Request)} if the tenant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tenants")
    public ResponseEntity<TenantDTO> createTenant(@RequestBody TenantDTO tenantDTO) throws URISyntaxException {
        log.debug("REST request to save Tenant : {}", tenantDTO);
        if (tenantDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenantDTO result = tenantService.save(tenantDTO);
        return ResponseEntity.created(new URI("/api/tenants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/tenants-with-admin")
    public ResponseEntity<TenantWithAdminResponseDTO> createTenantWithAdmin(@RequestBody TenantWithAdminDTO   tenantWithAdminDTO) throws URISyntaxException {
        TenantWithAdminResponseDTO _response = tenantService.createTenantWithAdmin(tenantWithAdminDTO);
        return ResponseEntity.ok()
            .body(_response);
    }

    /**
     * {@code PUT  /tenants} : Updates an existing tenant.
     *
     * @param tenantDTO the tenantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenantDTO,
     * or with status {@code 400 (Bad Request)} if the tenantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tenants")
    public ResponseEntity<TenantDTO> updateTenant(@RequestBody TenantDTO tenantDTO) throws URISyntaxException {
        log.debug("REST request to update Tenant : {}", tenantDTO);
        if (tenantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TenantDTO result = tenantService.save(tenantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenantDTO.getId().toString()))
            .body(result);
    }

    @PutMapping("/tenants/{id}")
    public ResponseEntity<TenantWithAdminResponseDTO> updateTenantAdmin(@PathVariable("id") Long id , @RequestBody TenantSuperAdminDTO tenantSuperAdminDTO) throws URISyntaxException {
        TenantWithAdminResponseDTO _response = tenantService.updateTenantAdmin(id , tenantSuperAdminDTO);
        return ResponseEntity.ok()
            .body(_response);
    }

    /**
     * {@code GET  /tenants} : get all the tenants.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenants in body.
     */
    @GetMapping("/tenants")
    public ResponseEntity<List<TenantDTO>> getAllTenants(TenantCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Tenants by criteria: {}", criteria);
        Page<TenantDTO> page = tenantQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tenants/count} : count all the tenants.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/tenants/count")
    public ResponseEntity<Long> countTenants(TenantCriteria criteria) {
        log.debug("REST request to count Tenants by criteria: {}", criteria);
        return ResponseEntity.ok().body(tenantQueryService.countByCriteria(criteria));
    }



    /**
     * {@code GET  /tenants/:id} : get the "id" tenant.
     *
     * @param id the id of the tenantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tenants/{id}")
    public ResponseEntity<TenantDTO> getTenant(@PathVariable Long id) {
        log.debug("REST request to get Tenant : {}", id);
        Optional<TenantDTO> tenantDTO = tenantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenantDTO);
    }

    @GetMapping("/tenants_withAdminInfo/{id}")
    public ResponseEntity<TenantWithAdminResponseDTO> getTenantWithTenantInfo(@PathVariable Long id) {
        log.debug("REST request to get Tenant : {}", id);
        TenantWithAdminResponseDTO _response = tenantService.getTenantWithTenantInfo(id);
        return ResponseEntity.ok().body(_response);
    }

    /**
     * {@code DELETE  /tenants/:id} : delete the "id" tenant.
     *
     * @param id the id of the tenantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tenants/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable Long id) {
        log.debug("REST request to delete Tenant : {}", id);
        tenantService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
