package com.lm.portal.web.rest;

import com.lm.portal.service.TenantUserService;
import com.lm.portal.web.rest.errors.BadRequestAlertException;
import com.lm.portal.service.dto.TenantUserDTO;

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
 * REST controller for managing {@link com.lm.portal.domain.TenantUser}.
 */
@RestController
@RequestMapping("/api")
public class TenantUserResource {

    private final Logger log = LoggerFactory.getLogger(TenantUserResource.class);

    private static final String ENTITY_NAME = "tenantUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenantUserService tenantUserService;

    public TenantUserResource(TenantUserService tenantUserService) {
        this.tenantUserService = tenantUserService;
    }

    /**
     * {@code POST  /tenant-users} : Create a new tenantUser.
     *
     * @param tenantUserDTO the tenantUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenantUserDTO, or with status {@code 400 (Bad Request)} if the tenantUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tenant-users")
    public ResponseEntity<TenantUserDTO> createTenantUser(@RequestBody TenantUserDTO tenantUserDTO) throws URISyntaxException {
        log.debug("REST request to save TenantUser : {}", tenantUserDTO);
        if (tenantUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenantUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TenantUserDTO result = tenantUserService.save(tenantUserDTO);
        return ResponseEntity.created(new URI("/api/tenant-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tenant-users} : Updates an existing tenantUser.
     *
     * @param tenantUserDTO the tenantUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenantUserDTO,
     * or with status {@code 400 (Bad Request)} if the tenantUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenantUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tenant-users")
    public ResponseEntity<TenantUserDTO> updateTenantUser(@RequestBody TenantUserDTO tenantUserDTO) throws URISyntaxException {
        log.debug("REST request to update TenantUser : {}", tenantUserDTO);
        if (tenantUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TenantUserDTO result = tenantUserService.save(tenantUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tenantUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tenant-users} : get all the tenantUsers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenantUsers in body.
     */
    @GetMapping("/tenant-users")
    public ResponseEntity<List<TenantUserDTO>> getAllTenantUsers(Pageable pageable) {
        log.debug("REST request to get a page of TenantUsers");
        Page<TenantUserDTO> page = tenantUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tenant-users/:id} : get the "id" tenantUser.
     *
     * @param id the id of the tenantUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenantUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tenant-users/{id}")
    public ResponseEntity<TenantUserDTO> getTenantUser(@PathVariable Long id) {
        log.debug("REST request to get TenantUser : {}", id);
        Optional<TenantUserDTO> tenantUserDTO = tenantUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenantUserDTO);
    }

    /**
     * {@code DELETE  /tenant-users/:id} : delete the "id" tenantUser.
     *
     * @param id the id of the tenantUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tenant-users/{id}")
    public ResponseEntity<Void> deleteTenantUser(@PathVariable Long id) {
        log.debug("REST request to delete TenantUser : {}", id);
        tenantUserService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
