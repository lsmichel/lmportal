package com.lm.portal.web.rest;

import com.lm.portal.service.TenantSuperAdminService;
import com.lm.portal.web.rest.errors.BadRequestAlertException;
import com.lm.portal.service.dto.TenantSuperAdminDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lm.portal.domain.TenantSuperAdmin}.
 */
@RestController
@RequestMapping("/api")
public class TenantSuperAdminResource {

    private final Logger log = LoggerFactory.getLogger(TenantSuperAdminResource.class);

    private static final String ENTITY_NAME = "tenantSuperAdmin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenantSuperAdminService tenantSuperAdminService;

    public TenantSuperAdminResource(TenantSuperAdminService tenantSuperAdminService) {
        this.tenantSuperAdminService = tenantSuperAdminService;
    }

    /**
     * {@code POST  /tenant-super-admins} : Create a new tenantSuperAdmin.
     *
     * @param tenantSuperAdminDTO the tenantSuperAdminDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenantSuperAdminDTO, or with status {@code 400 (Bad Request)} if the tenantSuperAdmin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tenant-super-admins")
    public ResponseEntity<TenantSuperAdminDTO> createTenantSuperAdmin(@RequestBody TenantSuperAdminDTO tenantSuperAdminDTO) throws URISyntaxException {
        log.debug("REST request to save TenantSuperAdmin : {}", tenantSuperAdminDTO);
        if (tenantSuperAdminDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenantSuperAdmin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenantSuperAdminDTO result = tenantSuperAdminService.save(tenantSuperAdminDTO);
        return ResponseEntity.created(new URI("/api/tenant-super-admins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tenant-super-admins} : Updates an existing tenantSuperAdmin.
     *
     * @param tenantSuperAdminDTO the tenantSuperAdminDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenantSuperAdminDTO,
     * or with status {@code 400 (Bad Request)} if the tenantSuperAdminDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenantSuperAdminDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tenant-super-admins")
    public ResponseEntity<TenantSuperAdminDTO> updateTenantSuperAdmin(@RequestBody TenantSuperAdminDTO tenantSuperAdminDTO) throws URISyntaxException {
        log.debug("REST request to update TenantSuperAdmin : {}", tenantSuperAdminDTO);
        if (tenantSuperAdminDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TenantSuperAdminDTO result = tenantSuperAdminService.save(tenantSuperAdminDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenantSuperAdminDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tenant-super-admins} : get all the tenantSuperAdmins.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenantSuperAdmins in body.
     */
    @GetMapping("/tenant-super-admins")
    public ResponseEntity<List<TenantSuperAdminDTO>> getAllTenantSuperAdmins(Pageable pageable) {
        log.debug("REST request to get a page of TenantSuperAdmins");
        Page<TenantSuperAdminDTO> page = tenantSuperAdminService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tenant-super-admins/:id} : get the "id" tenantSuperAdmin.
     *
     * @param id the id of the tenantSuperAdminDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenantSuperAdminDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tenant-super-admins/{id}")
    public ResponseEntity<TenantSuperAdminDTO> getTenantSuperAdmin(@PathVariable Long id) {
        log.debug("REST request to get TenantSuperAdmin : {}", id);
        Optional<TenantSuperAdminDTO> tenantSuperAdminDTO = tenantSuperAdminService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenantSuperAdminDTO);
    }

    /**
     * {@code DELETE  /tenant-super-admins/:id} : delete the "id" tenantSuperAdmin.
     *
     * @param id the id of the tenantSuperAdminDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tenant-super-admins/{id}")
    public ResponseEntity<Void> deleteTenantSuperAdmin(@PathVariable Long id) {
        log.debug("REST request to delete TenantSuperAdmin : {}", id);
        tenantSuperAdminService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
