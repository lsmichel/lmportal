package com.lm.portal.web.rest;

import com.lm.portal.LmPortal2App;
import com.lm.portal.domain.TenantSuperAdmin;
import com.lm.portal.domain.Tenant;
import com.lm.portal.repository.TenantSuperAdminRepository;
import com.lm.portal.service.TenantSuperAdminService;
import com.lm.portal.service.dto.TenantSuperAdminDTO;
import com.lm.portal.service.mapper.TenantSuperAdminMapper;
import com.lm.portal.service.dto.TenantSuperAdminCriteria;
import com.lm.portal.service.TenantSuperAdminQueryService;

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
    private TenantSuperAdminQueryService tenantSuperAdminQueryService;

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
    public void getTenantSuperAdminsByIdFiltering() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        Long id = tenantSuperAdmin.getId();

        defaultTenantSuperAdminShouldBeFound("id.equals=" + id);
        defaultTenantSuperAdminShouldNotBeFound("id.notEquals=" + id);

        defaultTenantSuperAdminShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTenantSuperAdminShouldNotBeFound("id.greaterThan=" + id);

        defaultTenantSuperAdminShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTenantSuperAdminShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTenantSuperAdminsByFirstNameTenantSuperAdminIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where firstNameTenantSuperAdmin equals to DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("firstNameTenantSuperAdmin.equals=" + DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where firstNameTenantSuperAdmin equals to UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("firstNameTenantSuperAdmin.equals=" + UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByFirstNameTenantSuperAdminIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where firstNameTenantSuperAdmin not equals to DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("firstNameTenantSuperAdmin.notEquals=" + DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where firstNameTenantSuperAdmin not equals to UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("firstNameTenantSuperAdmin.notEquals=" + UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByFirstNameTenantSuperAdminIsInShouldWork() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where firstNameTenantSuperAdmin in DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN or UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("firstNameTenantSuperAdmin.in=" + DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN + "," + UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where firstNameTenantSuperAdmin equals to UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("firstNameTenantSuperAdmin.in=" + UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByFirstNameTenantSuperAdminIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where firstNameTenantSuperAdmin is not null
        defaultTenantSuperAdminShouldBeFound("firstNameTenantSuperAdmin.specified=true");

        // Get all the tenantSuperAdminList where firstNameTenantSuperAdmin is null
        defaultTenantSuperAdminShouldNotBeFound("firstNameTenantSuperAdmin.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantSuperAdminsByFirstNameTenantSuperAdminContainsSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where firstNameTenantSuperAdmin contains DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("firstNameTenantSuperAdmin.contains=" + DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where firstNameTenantSuperAdmin contains UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("firstNameTenantSuperAdmin.contains=" + UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByFirstNameTenantSuperAdminNotContainsSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where firstNameTenantSuperAdmin does not contain DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("firstNameTenantSuperAdmin.doesNotContain=" + DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where firstNameTenantSuperAdmin does not contain UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("firstNameTenantSuperAdmin.doesNotContain=" + UPDATED_FIRST_NAME_TENANT_SUPER_ADMIN);
    }


    @Test
    @Transactional
    public void getAllTenantSuperAdminsByLastNameTenantSuperAdminIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where lastNameTenantSuperAdmin equals to DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("lastNameTenantSuperAdmin.equals=" + DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where lastNameTenantSuperAdmin equals to UPDATED_LAST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("lastNameTenantSuperAdmin.equals=" + UPDATED_LAST_NAME_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByLastNameTenantSuperAdminIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where lastNameTenantSuperAdmin not equals to DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("lastNameTenantSuperAdmin.notEquals=" + DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where lastNameTenantSuperAdmin not equals to UPDATED_LAST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("lastNameTenantSuperAdmin.notEquals=" + UPDATED_LAST_NAME_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByLastNameTenantSuperAdminIsInShouldWork() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where lastNameTenantSuperAdmin in DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN or UPDATED_LAST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("lastNameTenantSuperAdmin.in=" + DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN + "," + UPDATED_LAST_NAME_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where lastNameTenantSuperAdmin equals to UPDATED_LAST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("lastNameTenantSuperAdmin.in=" + UPDATED_LAST_NAME_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByLastNameTenantSuperAdminIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where lastNameTenantSuperAdmin is not null
        defaultTenantSuperAdminShouldBeFound("lastNameTenantSuperAdmin.specified=true");

        // Get all the tenantSuperAdminList where lastNameTenantSuperAdmin is null
        defaultTenantSuperAdminShouldNotBeFound("lastNameTenantSuperAdmin.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantSuperAdminsByLastNameTenantSuperAdminContainsSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where lastNameTenantSuperAdmin contains DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("lastNameTenantSuperAdmin.contains=" + DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where lastNameTenantSuperAdmin contains UPDATED_LAST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("lastNameTenantSuperAdmin.contains=" + UPDATED_LAST_NAME_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByLastNameTenantSuperAdminNotContainsSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where lastNameTenantSuperAdmin does not contain DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("lastNameTenantSuperAdmin.doesNotContain=" + DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where lastNameTenantSuperAdmin does not contain UPDATED_LAST_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("lastNameTenantSuperAdmin.doesNotContain=" + UPDATED_LAST_NAME_TENANT_SUPER_ADMIN);
    }


    @Test
    @Transactional
    public void getAllTenantSuperAdminsByUserNameTenantSuperAdminIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where userNameTenantSuperAdmin equals to DEFAULT_USER_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("userNameTenantSuperAdmin.equals=" + DEFAULT_USER_NAME_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where userNameTenantSuperAdmin equals to UPDATED_USER_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("userNameTenantSuperAdmin.equals=" + UPDATED_USER_NAME_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByUserNameTenantSuperAdminIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where userNameTenantSuperAdmin not equals to DEFAULT_USER_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("userNameTenantSuperAdmin.notEquals=" + DEFAULT_USER_NAME_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where userNameTenantSuperAdmin not equals to UPDATED_USER_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("userNameTenantSuperAdmin.notEquals=" + UPDATED_USER_NAME_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByUserNameTenantSuperAdminIsInShouldWork() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where userNameTenantSuperAdmin in DEFAULT_USER_NAME_TENANT_SUPER_ADMIN or UPDATED_USER_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("userNameTenantSuperAdmin.in=" + DEFAULT_USER_NAME_TENANT_SUPER_ADMIN + "," + UPDATED_USER_NAME_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where userNameTenantSuperAdmin equals to UPDATED_USER_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("userNameTenantSuperAdmin.in=" + UPDATED_USER_NAME_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByUserNameTenantSuperAdminIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where userNameTenantSuperAdmin is not null
        defaultTenantSuperAdminShouldBeFound("userNameTenantSuperAdmin.specified=true");

        // Get all the tenantSuperAdminList where userNameTenantSuperAdmin is null
        defaultTenantSuperAdminShouldNotBeFound("userNameTenantSuperAdmin.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantSuperAdminsByUserNameTenantSuperAdminContainsSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where userNameTenantSuperAdmin contains DEFAULT_USER_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("userNameTenantSuperAdmin.contains=" + DEFAULT_USER_NAME_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where userNameTenantSuperAdmin contains UPDATED_USER_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("userNameTenantSuperAdmin.contains=" + UPDATED_USER_NAME_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByUserNameTenantSuperAdminNotContainsSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where userNameTenantSuperAdmin does not contain DEFAULT_USER_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("userNameTenantSuperAdmin.doesNotContain=" + DEFAULT_USER_NAME_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where userNameTenantSuperAdmin does not contain UPDATED_USER_NAME_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("userNameTenantSuperAdmin.doesNotContain=" + UPDATED_USER_NAME_TENANT_SUPER_ADMIN);
    }


    @Test
    @Transactional
    public void getAllTenantSuperAdminsByEmailTenantSuperAdminIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where emailTenantSuperAdmin equals to DEFAULT_EMAIL_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("emailTenantSuperAdmin.equals=" + DEFAULT_EMAIL_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where emailTenantSuperAdmin equals to UPDATED_EMAIL_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("emailTenantSuperAdmin.equals=" + UPDATED_EMAIL_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByEmailTenantSuperAdminIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where emailTenantSuperAdmin not equals to DEFAULT_EMAIL_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("emailTenantSuperAdmin.notEquals=" + DEFAULT_EMAIL_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where emailTenantSuperAdmin not equals to UPDATED_EMAIL_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("emailTenantSuperAdmin.notEquals=" + UPDATED_EMAIL_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByEmailTenantSuperAdminIsInShouldWork() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where emailTenantSuperAdmin in DEFAULT_EMAIL_TENANT_SUPER_ADMIN or UPDATED_EMAIL_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("emailTenantSuperAdmin.in=" + DEFAULT_EMAIL_TENANT_SUPER_ADMIN + "," + UPDATED_EMAIL_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where emailTenantSuperAdmin equals to UPDATED_EMAIL_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("emailTenantSuperAdmin.in=" + UPDATED_EMAIL_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByEmailTenantSuperAdminIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where emailTenantSuperAdmin is not null
        defaultTenantSuperAdminShouldBeFound("emailTenantSuperAdmin.specified=true");

        // Get all the tenantSuperAdminList where emailTenantSuperAdmin is null
        defaultTenantSuperAdminShouldNotBeFound("emailTenantSuperAdmin.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantSuperAdminsByEmailTenantSuperAdminContainsSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where emailTenantSuperAdmin contains DEFAULT_EMAIL_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("emailTenantSuperAdmin.contains=" + DEFAULT_EMAIL_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where emailTenantSuperAdmin contains UPDATED_EMAIL_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("emailTenantSuperAdmin.contains=" + UPDATED_EMAIL_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByEmailTenantSuperAdminNotContainsSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where emailTenantSuperAdmin does not contain DEFAULT_EMAIL_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("emailTenantSuperAdmin.doesNotContain=" + DEFAULT_EMAIL_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where emailTenantSuperAdmin does not contain UPDATED_EMAIL_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("emailTenantSuperAdmin.doesNotContain=" + UPDATED_EMAIL_TENANT_SUPER_ADMIN);
    }


    @Test
    @Transactional
    public void getAllTenantSuperAdminsByPasswordTenantSuperAdminIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where passwordTenantSuperAdmin equals to DEFAULT_PASSWORD_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("passwordTenantSuperAdmin.equals=" + DEFAULT_PASSWORD_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where passwordTenantSuperAdmin equals to UPDATED_PASSWORD_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("passwordTenantSuperAdmin.equals=" + UPDATED_PASSWORD_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByPasswordTenantSuperAdminIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where passwordTenantSuperAdmin not equals to DEFAULT_PASSWORD_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("passwordTenantSuperAdmin.notEquals=" + DEFAULT_PASSWORD_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where passwordTenantSuperAdmin not equals to UPDATED_PASSWORD_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("passwordTenantSuperAdmin.notEquals=" + UPDATED_PASSWORD_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByPasswordTenantSuperAdminIsInShouldWork() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where passwordTenantSuperAdmin in DEFAULT_PASSWORD_TENANT_SUPER_ADMIN or UPDATED_PASSWORD_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("passwordTenantSuperAdmin.in=" + DEFAULT_PASSWORD_TENANT_SUPER_ADMIN + "," + UPDATED_PASSWORD_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where passwordTenantSuperAdmin equals to UPDATED_PASSWORD_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("passwordTenantSuperAdmin.in=" + UPDATED_PASSWORD_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByPasswordTenantSuperAdminIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where passwordTenantSuperAdmin is not null
        defaultTenantSuperAdminShouldBeFound("passwordTenantSuperAdmin.specified=true");

        // Get all the tenantSuperAdminList where passwordTenantSuperAdmin is null
        defaultTenantSuperAdminShouldNotBeFound("passwordTenantSuperAdmin.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantSuperAdminsByPasswordTenantSuperAdminContainsSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where passwordTenantSuperAdmin contains DEFAULT_PASSWORD_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("passwordTenantSuperAdmin.contains=" + DEFAULT_PASSWORD_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where passwordTenantSuperAdmin contains UPDATED_PASSWORD_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("passwordTenantSuperAdmin.contains=" + UPDATED_PASSWORD_TENANT_SUPER_ADMIN);
    }

    @Test
    @Transactional
    public void getAllTenantSuperAdminsByPasswordTenantSuperAdminNotContainsSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);

        // Get all the tenantSuperAdminList where passwordTenantSuperAdmin does not contain DEFAULT_PASSWORD_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldNotBeFound("passwordTenantSuperAdmin.doesNotContain=" + DEFAULT_PASSWORD_TENANT_SUPER_ADMIN);

        // Get all the tenantSuperAdminList where passwordTenantSuperAdmin does not contain UPDATED_PASSWORD_TENANT_SUPER_ADMIN
        defaultTenantSuperAdminShouldBeFound("passwordTenantSuperAdmin.doesNotContain=" + UPDATED_PASSWORD_TENANT_SUPER_ADMIN);
    }


    @Test
    @Transactional
    public void getAllTenantSuperAdminsByTenantIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);
        Tenant tenant = TenantResourceIT.createEntity(em);
        em.persist(tenant);
        em.flush();
        tenantSuperAdmin.setTenant(tenant);
        tenantSuperAdminRepository.saveAndFlush(tenantSuperAdmin);
        Long tenantId = tenant.getId();

        // Get all the tenantSuperAdminList where tenant equals to tenantId
        defaultTenantSuperAdminShouldBeFound("tenantId.equals=" + tenantId);

        // Get all the tenantSuperAdminList where tenant equals to tenantId + 1
        defaultTenantSuperAdminShouldNotBeFound("tenantId.equals=" + (tenantId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTenantSuperAdminShouldBeFound(String filter) throws Exception {
        restTenantSuperAdminMockMvc.perform(get("/api/tenant-super-admins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenantSuperAdmin.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstNameTenantSuperAdmin").value(hasItem(DEFAULT_FIRST_NAME_TENANT_SUPER_ADMIN)))
            .andExpect(jsonPath("$.[*].lastNameTenantSuperAdmin").value(hasItem(DEFAULT_LAST_NAME_TENANT_SUPER_ADMIN)))
            .andExpect(jsonPath("$.[*].userNameTenantSuperAdmin").value(hasItem(DEFAULT_USER_NAME_TENANT_SUPER_ADMIN)))
            .andExpect(jsonPath("$.[*].emailTenantSuperAdmin").value(hasItem(DEFAULT_EMAIL_TENANT_SUPER_ADMIN)))
            .andExpect(jsonPath("$.[*].passwordTenantSuperAdmin").value(hasItem(DEFAULT_PASSWORD_TENANT_SUPER_ADMIN)));

        // Check, that the count call also returns 1
        restTenantSuperAdminMockMvc.perform(get("/api/tenant-super-admins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTenantSuperAdminShouldNotBeFound(String filter) throws Exception {
        restTenantSuperAdminMockMvc.perform(get("/api/tenant-super-admins?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTenantSuperAdminMockMvc.perform(get("/api/tenant-super-admins/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
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
