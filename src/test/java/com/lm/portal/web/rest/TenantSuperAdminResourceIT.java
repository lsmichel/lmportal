package com.lm.portal.web.rest;

import com.lm.portal.LmPortal2App;
import com.lm.portal.domain.TenantSuperAdmin;
import com.lm.portal.repository.TenantSuperAdminRepository;
import com.lm.portal.service.TenantSuperAdminService;
import com.lm.portal.service.dto.TenantSuperAdminDTO;
import com.lm.portal.service.mapper.TenantSuperAdminMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TenantSuperAdminResource} REST controller.
 */
@SpringBootTest(classes = LmPortal2App.class)

@AutoConfigureMockMvc
@WithMockUser
public class TenantSuperAdminResourceIT {

    private static final String DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME_TENANT_SUPER_ADMIN = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME_TENANT_SUPER_ADMIN = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME_TENANT_SUPER_ADMIN = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_TENANT_SUPER_ADMIN = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_TENANT_SUPER_ADMIN = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD_TENANT_SUPER_ADMIN = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD_TENANT_SUPER_ADMIN = "BBBBBBBBBB";

    @Autowired
    private TenantSuperAdminRepository tenantSuperAdminRepository;

    @Autowired
    private TenantSuperAdminMapper tenantSuperAdminMapper;

    @Autowired
    private TenantSuperAdminService tenantSuperAdminService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenantSuperAdminMockMvc;

    private TenantSuperAdmin tenantSuperAdmin;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenantSuperAdmin createEntity(EntityManager em) {
        TenantSuperAdmin tenantSuperAdmin = new TenantSuperAdmin()
            .firstNameTenantSuperAdmin(DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN)
            .lastNameTenantSuperAdmin(DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN)
            .userNameTenantSuperAdmin(DEFAULT_USER_NAME_TENANT_SUPER_ADMIN)
            .emailTenantSuperAdmin(DEFAULT_EMAIL_TENANT_SUPER_ADMIN)
            .passwordTenantSuperAdmin(DEFAULT_PASSWORD_TENANT_SUPER_ADMIN);
        return tenantSuperAdmin;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenantSuperAdmin createUpdatedEntity(EntityManager em) {
        TenantSuperAdmin tenantSuperAdmin = new TenantSuperAdmin()
            .firstNameTenantSuperAdmin(UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN)
            .lastNameTenantSuperAdmin(UPDATED_LAST_NAME_TENANT_SUPER_ADMIN)
            .userNameTenantSuperAdmin(UPDATED_USER_NAME_TENANT_SUPER_ADMIN)
            .emailTenantSuperAdmin(UPDATED_EMAIL_TENANT_SUPER_ADMIN)
            .passwordTenantSuperAdmin(UPDATED_PASSWORD_TENANT_SUPER_ADMIN);
        return tenantSuperAdmin;
    }

    @BeforeEach
    public void initTest() {
        tenantSuperAdmin = createEntity(em);
    }

    @Test
    @Transactional
    public void createTenantSuperAdmin() throws Exception {
        int databaseSizeBeforeCreate = tenantSuperAdminRepository.findAll().size();

        // Create the TenantSuperAdmin
        TenantSuperAdminDTO tenantSuperAdminDTO = tenantSuperAdminMapper.toDto(tenantSuperAdmin);
        restTenantSuperAdminMockMvc.perform(post("/api/tenant-super-admins")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tenantSuperAdminDTO)))
            .andExpect(status().isCreated());

        // Validate the TenantSuperAdmin in the database
        List<TenantSuperAdmin> tenantSuperAdminList = tenantSuperAdminRepository.findAll();
        assertThat(tenantSuperAdminList).hasSize(databaseSizeBeforeCreate + 1);
        TenantSuperAdmin testTenantSuperAdmin = tenantSuperAdminList.get(tenantSuperAdminList.size() - 1);
        assertThat(testTenantSuperAdmin.getFirstNameTenantSuperAdmin()).isEqualTo(DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN);
        assertThat(testTenantSuperAdmin.getLastNameTenantSuperAdmin()).isEqualTo(DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN);
        assertThat(testTenantSuperAdmin.getUserNameTenantSuperAdmin()).isEqualTo(DEFAULT_USER_NAME_TENANT_SUPER_ADMIN);
        assertThat(testTenantSuperAdmin.getEmailTenantSuperAdmin()).isEqualTo(DEFAULT_EMAIL_TENANT_SUPER_ADMIN);
        assertThat(testTenantSuperAdmin.getPasswordTenantSuperAdmin()).isEqualTo(DEFAULT_PASSWORD_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void createTenantSuperAdminWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tenantSuperAdminRepository.findAll().size();

        // Create the TenantSuperAdmin with an existing ID
        tenantSuperAdmin.setId(1L);
        TenantSuperAdminDTO tenantSuperAdminDTO = tenantSuperAdminMapper.toDto(tenantSuperAdmin);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenantSuperAdminMockMvc.perform(post("/api/tenant-super-admins")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tenantSuperAdminDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TenantSuperAdmin in the database
        List<TenantSuperAdmin> tenantSuperAdminList = tenantSuperAdminRepository.findAll();
        assertThat(tenantSuperAdminList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTenantSuperAdmins() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList
        restTenantSuperAdminMockMvc.perform(get("/api/tenant-super-admins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenantSuperAdmin.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstNameTenantSuperAdmin").value(hasItem(DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN)))
            .andExpect(jsonPath("$.[*].lastNameTenantSuperAdmin").value(hasItem(DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN)))
            .andExpect(jsonPath("$.[*].userNameTenantSuperAdmin").value(hasItem(DEFAULT_USER_NAME_TENANT_SUPER_ADMIN)))
            .andExpect(jsonPath("$.[*].emailTenantSuperAdmin").value(hasItem(DEFAULT_EMAIL_TENANT_SUPER_ADMIN)))
            .andExpect(jsonPath("$.[*].passwordTenantSuperAdmin").value(hasItem(DEFAULT_PASSWORD_TENANT_SUPER_ADMIN)));
    }
    
    @Test
    @Transactional
    public void getTenantSuperAdmin() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get the tenantSuperAdmin
        restTenantSuperAdminMockMvc.perform(get("/api/tenant-super-admins/{id}", tenantSuperAdmin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenantSuperAdmin.getId().intValue()))
            .andExpect(jsonPath("$.firstNameTenantSuperAdmin").value(DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN))
            .andExpect(jsonPath("$.lastNameTenantSuperAdmin").value(DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN))
            .andExpect(jsonPath("$.userNameTenantSuperAdmin").value(DEFAULT_USER_NAME_TENANT_SUPER_ADMIN))
            .andExpect(jsonPath("$.emailTenantSuperAdmin").value(DEFAULT_EMAIL_TENANT_SUPER_ADMIN))
            .andExpect(jsonPath("$.passwordTenantSuperAdmin").value(DEFAULT_PASSWORD_TENANT_SUPER_ADMIN));
    }

    @Test
    @Transactional
    public void getNonExistingTenantSuperAdmin() throws Exception {
        // Get the tenantSuperAdmin
        restTenantSuperAdminMockMvc.perform(get("/api/tenant-super-admins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTenantSuperAdmin() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        int databaseSizeBeforeUpdate = tenantSuperAdminRepository.findAll().size();

        // Update the tenantSuperAdmin
        TenantSuperAdmin updatedTenantSuperAdmin = tenantSuperAdminRepository.findById(tenantSuperAdmin.getId()).get();
        // Disconnect from session so that the updates on updatedTenantSuperAdmin are not directly saved in db
        em.detach(updatedTenantSuperAdmin);
        updatedTenantSuperAdmin
            .firstNameTenantSuperAdmin(UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN)
            .lastNameTenantSuperAdmin(UPDATED_LAST_NAME_TENANT_SUPER_ADMIN)
            .userNameTenantSuperAdmin(UPDATED_USER_NAME_TENANT_SUPER_ADMIN)
            .emailTenantSuperAdmin(UPDATED_EMAIL_TENANT_SUPER_ADMIN)
            .passwordTenantSuperAdmin(UPDATED_PASSWORD_TENANT_SUPER_ADMIN);
        TenantSuperAdminDTO tenantSuperAdminDTO = tenantSuperAdminMapper.toDto(updatedTenantSuperAdmin);

        restTenantSuperAdminMockMvc.perform(put("/api/tenant-super-admins")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tenantSuperAdminDTO)))
            .andExpect(status().isOk());

        // Validate the TenantSuperAdmin in the database
        List<TenantSuperAdmin> tenantSuperAdminList = tenantSuperAdminRepository.findAll();
        assertThat(tenantSuperAdminList).hasSize(databaseSizeBeforeUpdate);
        TenantSuperAdmin testTenantSuperAdmin = tenantSuperAdminList.get(tenantSuperAdminList.size() - 1);
        assertThat(testTenantSuperAdmin.getFirstNameTenantSuperAdmin()).isEqualTo(UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN);
        assertThat(testTenantSuperAdmin.getLastNameTenantSuperAdmin()).isEqualTo(UPDATED_LAST_NAME_TENANT_SUPER_ADMIN);
        assertThat(testTenantSuperAdmin.getUserNameTenantSuperAdmin()).isEqualTo(UPDATED_USER_NAME_TENANT_SUPER_ADMIN);
        assertThat(testTenantSuperAdmin.getEmailTenantSuperAdmin()).isEqualTo(UPDATED_EMAIL_TENANT_SUPER_ADMIN);
        assertThat(testTenantSuperAdmin.getPasswordTenantSuperAdmin()).isEqualTo(UPDATED_PASSWORD_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void updateNonExistingTenantSuperAdmin() throws Exception {
        int databaseSizeBeforeUpdate = tenantSuperAdminRepository.findAll().size();

        // Create the TenantSuperAdmin
        TenantSuperAdminDTO tenantSuperAdminDTO = tenantSuperAdminMapper.toDto(tenantSuperAdmin);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenantSuperAdminMockMvc.perform(put("/api/tenant-super-admins")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tenantSuperAdminDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TenantSuperAdmin in the database
        List<TenantSuperAdmin> tenantSuperAdminList = tenantSuperAdminRepository.findAll();
        assertThat(tenantSuperAdminList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTenantSuperAdmin() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        int databaseSizeBeforeDelete = tenantSuperAdminRepository.findAll().size();

        // Delete the tenantSuperAdmin
        restTenantSuperAdminMockMvc.perform(delete("/api/tenant-super-admins/{id}", tenantSuperAdmin.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenantSuperAdmin> tenantSuperAdminList = tenantSuperAdminRepository.findAll();
        assertThat(tenantSuperAdminList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
