package com.lm.portal.web.rest;

import com.lm.portal.LmPortal2App;
import com.lm.portal.domain.Tenant;
import com.lm.portal.domain.TenantSuperAdmin;
import com.lm.portal.domain.TenantUser;
import com.lm.portal.domain.Site;
import com.lm.portal.repository.TenantRepository;
import com.lm.portal.service.TenantService;
import com.lm.portal.service.dto.TenantDTO;
import com.lm.portal.service.mapper.TenantMapper;
import com.lm.portal.service.dto.TenantCriteria;
import com.lm.portal.service.TenantQueryService;

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
 * Integration tests for the {@link TenantResource} REST controller.
 */
@SpringBootTest(classes = LmPortal2App.class)

@AutoConfigureMockMvc
@WithMockUser
public class TenantResourceIT {

    private static final String DEFAULT_ID_TENANT = "AAAAAAAAAA";
    private static final String UPDATED_ID_TENANT = "BBBBBBBBBB";

    private static final String DEFAULT_LOGO_TENAT = "AAAAAAAAAA";
    private static final String UPDATED_LOGO_TENAT = "BBBBBBBBBB";

    private static final String DEFAULT_SPACE_NAME_TENANT = "AAAAAAAAAA";
    private static final String UPDATED_SPACE_NAME_TENANT = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER_TENANT = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER_TENANT = "BBBBBBBBBB";

    private static final String DEFAULT_WEB_SITE_TENANT = "AAAAAAAAAA";
    private static final String UPDATED_WEB_SITE_TENANT = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_TENANT = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_TENANT = "BBBBBBBBBB";

    private static final String DEFAULT_FACEBOOK_TENANT = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOK_TENANT = "BBBBBBBBBB";

    private static final String DEFAULT_TWITTER_TENANT = "AAAAAAAAAA";
    private static final String UPDATED_TWITTER_TENANT = "BBBBBBBBBB";

    private static final String DEFAULT_INSTAGRAM_TENANT = "AAAAAAAAAA";
    private static final String UPDATED_INSTAGRAM_TENANT = "BBBBBBBBBB";

    private static final String DEFAULT_YOUTUBE_TENANT = "AAAAAAAAAA";
    private static final String UPDATED_YOUTUBE_TENANT = "BBBBBBBBBB";

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private TenantMapper tenantMapper;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private TenantQueryService tenantQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenantMockMvc;

    private Tenant tenant;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tenant createEntity(EntityManager em) {
        Tenant tenant = new Tenant()
            .idTenant(DEFAULT_ID_TENANT)
            .logoTenat(DEFAULT_LOGO_TENAT)
            .spaceNameTenant(DEFAULT_SPACE_NAME_TENANT)
            .phoneNumberTenant(DEFAULT_PHONE_NUMBER_TENANT)
            .webSiteTenant(DEFAULT_WEB_SITE_TENANT)
            .emailTenant(DEFAULT_EMAIL_TENANT)
            .facebookTenant(DEFAULT_FACEBOOK_TENANT)
            .twitterTenant(DEFAULT_TWITTER_TENANT)
            .instagramTenant(DEFAULT_INSTAGRAM_TENANT)
            .youtubeTenant(DEFAULT_YOUTUBE_TENANT);
        return tenant;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tenant createUpdatedEntity(EntityManager em) {
        Tenant tenant = new Tenant()
            .idTenant(UPDATED_ID_TENANT)
            .logoTenat(UPDATED_LOGO_TENAT)
            .spaceNameTenant(UPDATED_SPACE_NAME_TENANT)
            .phoneNumberTenant(UPDATED_PHONE_NUMBER_TENANT)
            .webSiteTenant(UPDATED_WEB_SITE_TENANT)
            .emailTenant(UPDATED_EMAIL_TENANT)
            .facebookTenant(UPDATED_FACEBOOK_TENANT)
            .twitterTenant(UPDATED_TWITTER_TENANT)
            .instagramTenant(UPDATED_INSTAGRAM_TENANT)
            .youtubeTenant(UPDATED_YOUTUBE_TENANT);
        return tenant;
    }

    @BeforeEach
    public void initTest() {
        tenant = createEntity(em);
    }

    @Test
    @Transactional
    public void createTenant() throws Exception {
        int databaseSizeBeforeCreate = tenantRepository.findAll().size();

        // Create the Tenant
        TenantDTO tenantDTO = tenantMapper.toDto(tenant);
        restTenantMockMvc.perform(post("/api/tenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tenantDTO)))
            .andExpect(status().isCreated());

        // Validate the Tenant in the database
        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeCreate + 1);
        Tenant testTenant = tenantList.get(tenantList.size() - 1);
        assertThat(testTenant.getIdTenant()).isEqualTo(DEFAULT_ID_TENANT);
        assertThat(testTenant.getLogoTenat()).isEqualTo(DEFAULT_LOGO_TENAT);
        assertThat(testTenant.getSpaceNameTenant()).isEqualTo(DEFAULT_SPACE_NAME_TENANT);
        assertThat(testTenant.getPhoneNumberTenant()).isEqualTo(DEFAULT_PHONE_NUMBER_TENANT);
        assertThat(testTenant.getWebSiteTenant()).isEqualTo(DEFAULT_WEB_SITE_TENANT);
        assertThat(testTenant.getEmailTenant()).isEqualTo(DEFAULT_EMAIL_TENANT);
        assertThat(testTenant.getFacebookTenant()).isEqualTo(DEFAULT_FACEBOOK_TENANT);
        assertThat(testTenant.getTwitterTenant()).isEqualTo(DEFAULT_TWITTER_TENANT);
        assertThat(testTenant.getInstagramTenant()).isEqualTo(DEFAULT_INSTAGRAM_TENANT);
        assertThat(testTenant.getYoutubeTenant()).isEqualTo(DEFAULT_YOUTUBE_TENANT);
    }

    @Test
    @Transactional
    public void createTenantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tenantRepository.findAll().size();

        // Create the Tenant with an existing ID
        tenant.setId(1L);
        TenantDTO tenantDTO = tenantMapper.toDto(tenant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenantMockMvc.perform(post("/api/tenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tenantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tenant in the database
        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTenants() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList
        restTenantMockMvc.perform(get("/api/tenants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenant.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTenant").value(hasItem(DEFAULT_ID_TENANT)))
            .andExpect(jsonPath("$.[*].logoTenat").value(hasItem(DEFAULT_LOGO_TENAT)))
            .andExpect(jsonPath("$.[*].spaceNameTenant").value(hasItem(DEFAULT_SPACE_NAME_TENANT)))
            .andExpect(jsonPath("$.[*].phoneNumberTenant").value(hasItem(DEFAULT_PHONE_NUMBER_TENANT)))
            .andExpect(jsonPath("$.[*].webSiteTenant").value(hasItem(DEFAULT_WEB_SITE_TENANT)))
            .andExpect(jsonPath("$.[*].emailTenant").value(hasItem(DEFAULT_EMAIL_TENANT)))
            .andExpect(jsonPath("$.[*].facebookTenant").value(hasItem(DEFAULT_FACEBOOK_TENANT)))
            .andExpect(jsonPath("$.[*].twitterTenant").value(hasItem(DEFAULT_TWITTER_TENANT)))
            .andExpect(jsonPath("$.[*].instagramTenant").value(hasItem(DEFAULT_INSTAGRAM_TENANT)))
            .andExpect(jsonPath("$.[*].youtubeTenant").value(hasItem(DEFAULT_YOUTUBE_TENANT)));
    }
    
    @Test
    @Transactional
    public void getTenant() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get the tenant
        restTenantMockMvc.perform(get("/api/tenants/{id}", tenant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenant.getId().intValue()))
            .andExpect(jsonPath("$.idTenant").value(DEFAULT_ID_TENANT))
            .andExpect(jsonPath("$.logoTenat").value(DEFAULT_LOGO_TENAT))
            .andExpect(jsonPath("$.spaceNameTenant").value(DEFAULT_SPACE_NAME_TENANT))
            .andExpect(jsonPath("$.phoneNumberTenant").value(DEFAULT_PHONE_NUMBER_TENANT))
            .andExpect(jsonPath("$.webSiteTenant").value(DEFAULT_WEB_SITE_TENANT))
            .andExpect(jsonPath("$.emailTenant").value(DEFAULT_EMAIL_TENANT))
            .andExpect(jsonPath("$.facebookTenant").value(DEFAULT_FACEBOOK_TENANT))
            .andExpect(jsonPath("$.twitterTenant").value(DEFAULT_TWITTER_TENANT))
            .andExpect(jsonPath("$.instagramTenant").value(DEFAULT_INSTAGRAM_TENANT))
            .andExpect(jsonPath("$.youtubeTenant").value(DEFAULT_YOUTUBE_TENANT));
    }


    @Test
    @Transactional
    public void getTenantsByIdFiltering() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        Long id = tenant.getId();

        defaultTenantShouldBeFound("id.equals=" + id);
        defaultTenantShouldNotBeFound("id.notEquals=" + id);

        defaultTenantShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTenantShouldNotBeFound("id.greaterThan=" + id);

        defaultTenantShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTenantShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTenantsByIdTenantIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where idTenant equals to DEFAULT_ID_TENANT
        defaultTenantShouldBeFound("idTenant.equals=" + DEFAULT_ID_TENANT);

        // Get all the tenantList where idTenant equals to UPDATED_ID_TENANT
        defaultTenantShouldNotBeFound("idTenant.equals=" + UPDATED_ID_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByIdTenantIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where idTenant not equals to DEFAULT_ID_TENANT
        defaultTenantShouldNotBeFound("idTenant.notEquals=" + DEFAULT_ID_TENANT);

        // Get all the tenantList where idTenant not equals to UPDATED_ID_TENANT
        defaultTenantShouldBeFound("idTenant.notEquals=" + UPDATED_ID_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByIdTenantIsInShouldWork() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where idTenant in DEFAULT_ID_TENANT or UPDATED_ID_TENANT
        defaultTenantShouldBeFound("idTenant.in=" + DEFAULT_ID_TENANT + "," + UPDATED_ID_TENANT);

        // Get all the tenantList where idTenant equals to UPDATED_ID_TENANT
        defaultTenantShouldNotBeFound("idTenant.in=" + UPDATED_ID_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByIdTenantIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where idTenant is not null
        defaultTenantShouldBeFound("idTenant.specified=true");

        // Get all the tenantList where idTenant is null
        defaultTenantShouldNotBeFound("idTenant.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantsByIdTenantContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where idTenant contains DEFAULT_ID_TENANT
        defaultTenantShouldBeFound("idTenant.contains=" + DEFAULT_ID_TENANT);

        // Get all the tenantList where idTenant contains UPDATED_ID_TENANT
        defaultTenantShouldNotBeFound("idTenant.contains=" + UPDATED_ID_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByIdTenantNotContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where idTenant does not contain DEFAULT_ID_TENANT
        defaultTenantShouldNotBeFound("idTenant.doesNotContain=" + DEFAULT_ID_TENANT);

        // Get all the tenantList where idTenant does not contain UPDATED_ID_TENANT
        defaultTenantShouldBeFound("idTenant.doesNotContain=" + UPDATED_ID_TENANT);
    }


    @Test
    @Transactional
    public void getAllTenantsByLogoTenatIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where logoTenat equals to DEFAULT_LOGO_TENAT
        defaultTenantShouldBeFound("logoTenat.equals=" + DEFAULT_LOGO_TENAT);

        // Get all the tenantList where logoTenat equals to UPDATED_LOGO_TENAT
        defaultTenantShouldNotBeFound("logoTenat.equals=" + UPDATED_LOGO_TENAT);
    }

    @Test
    @Transactional
    public void getAllTenantsByLogoTenatIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where logoTenat not equals to DEFAULT_LOGO_TENAT
        defaultTenantShouldNotBeFound("logoTenat.notEquals=" + DEFAULT_LOGO_TENAT);

        // Get all the tenantList where logoTenat not equals to UPDATED_LOGO_TENAT
        defaultTenantShouldBeFound("logoTenat.notEquals=" + UPDATED_LOGO_TENAT);
    }

    @Test
    @Transactional
    public void getAllTenantsByLogoTenatIsInShouldWork() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where logoTenat in DEFAULT_LOGO_TENAT or UPDATED_LOGO_TENAT
        defaultTenantShouldBeFound("logoTenat.in=" + DEFAULT_LOGO_TENAT + "," + UPDATED_LOGO_TENAT);

        // Get all the tenantList where logoTenat equals to UPDATED_LOGO_TENAT
        defaultTenantShouldNotBeFound("logoTenat.in=" + UPDATED_LOGO_TENAT);
    }

    @Test
    @Transactional
    public void getAllTenantsByLogoTenatIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where logoTenat is not null
        defaultTenantShouldBeFound("logoTenat.specified=true");

        // Get all the tenantList where logoTenat is null
        defaultTenantShouldNotBeFound("logoTenat.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantsByLogoTenatContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where logoTenat contains DEFAULT_LOGO_TENAT
        defaultTenantShouldBeFound("logoTenat.contains=" + DEFAULT_LOGO_TENAT);

        // Get all the tenantList where logoTenat contains UPDATED_LOGO_TENAT
        defaultTenantShouldNotBeFound("logoTenat.contains=" + UPDATED_LOGO_TENAT);
    }

    @Test
    @Transactional
    public void getAllTenantsByLogoTenatNotContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where logoTenat does not contain DEFAULT_LOGO_TENAT
        defaultTenantShouldNotBeFound("logoTenat.doesNotContain=" + DEFAULT_LOGO_TENAT);

        // Get all the tenantList where logoTenat does not contain UPDATED_LOGO_TENAT
        defaultTenantShouldBeFound("logoTenat.doesNotContain=" + UPDATED_LOGO_TENAT);
    }


    @Test
    @Transactional
    public void getAllTenantsBySpaceNameTenantIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where spaceNameTenant equals to DEFAULT_SPACE_NAME_TENANT
        defaultTenantShouldBeFound("spaceNameTenant.equals=" + DEFAULT_SPACE_NAME_TENANT);

        // Get all the tenantList where spaceNameTenant equals to UPDATED_SPACE_NAME_TENANT
        defaultTenantShouldNotBeFound("spaceNameTenant.equals=" + UPDATED_SPACE_NAME_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsBySpaceNameTenantIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where spaceNameTenant not equals to DEFAULT_SPACE_NAME_TENANT
        defaultTenantShouldNotBeFound("spaceNameTenant.notEquals=" + DEFAULT_SPACE_NAME_TENANT);

        // Get all the tenantList where spaceNameTenant not equals to UPDATED_SPACE_NAME_TENANT
        defaultTenantShouldBeFound("spaceNameTenant.notEquals=" + UPDATED_SPACE_NAME_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsBySpaceNameTenantIsInShouldWork() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where spaceNameTenant in DEFAULT_SPACE_NAME_TENANT or UPDATED_SPACE_NAME_TENANT
        defaultTenantShouldBeFound("spaceNameTenant.in=" + DEFAULT_SPACE_NAME_TENANT + "," + UPDATED_SPACE_NAME_TENANT);

        // Get all the tenantList where spaceNameTenant equals to UPDATED_SPACE_NAME_TENANT
        defaultTenantShouldNotBeFound("spaceNameTenant.in=" + UPDATED_SPACE_NAME_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsBySpaceNameTenantIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where spaceNameTenant is not null
        defaultTenantShouldBeFound("spaceNameTenant.specified=true");

        // Get all the tenantList where spaceNameTenant is null
        defaultTenantShouldNotBeFound("spaceNameTenant.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantsBySpaceNameTenantContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where spaceNameTenant contains DEFAULT_SPACE_NAME_TENANT
        defaultTenantShouldBeFound("spaceNameTenant.contains=" + DEFAULT_SPACE_NAME_TENANT);

        // Get all the tenantList where spaceNameTenant contains UPDATED_SPACE_NAME_TENANT
        defaultTenantShouldNotBeFound("spaceNameTenant.contains=" + UPDATED_SPACE_NAME_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsBySpaceNameTenantNotContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where spaceNameTenant does not contain DEFAULT_SPACE_NAME_TENANT
        defaultTenantShouldNotBeFound("spaceNameTenant.doesNotContain=" + DEFAULT_SPACE_NAME_TENANT);

        // Get all the tenantList where spaceNameTenant does not contain UPDATED_SPACE_NAME_TENANT
        defaultTenantShouldBeFound("spaceNameTenant.doesNotContain=" + UPDATED_SPACE_NAME_TENANT);
    }


    @Test
    @Transactional
    public void getAllTenantsByPhoneNumberTenantIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where phoneNumberTenant equals to DEFAULT_PHONE_NUMBER_TENANT
        defaultTenantShouldBeFound("phoneNumberTenant.equals=" + DEFAULT_PHONE_NUMBER_TENANT);

        // Get all the tenantList where phoneNumberTenant equals to UPDATED_PHONE_NUMBER_TENANT
        defaultTenantShouldNotBeFound("phoneNumberTenant.equals=" + UPDATED_PHONE_NUMBER_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByPhoneNumberTenantIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where phoneNumberTenant not equals to DEFAULT_PHONE_NUMBER_TENANT
        defaultTenantShouldNotBeFound("phoneNumberTenant.notEquals=" + DEFAULT_PHONE_NUMBER_TENANT);

        // Get all the tenantList where phoneNumberTenant not equals to UPDATED_PHONE_NUMBER_TENANT
        defaultTenantShouldBeFound("phoneNumberTenant.notEquals=" + UPDATED_PHONE_NUMBER_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByPhoneNumberTenantIsInShouldWork() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where phoneNumberTenant in DEFAULT_PHONE_NUMBER_TENANT or UPDATED_PHONE_NUMBER_TENANT
        defaultTenantShouldBeFound("phoneNumberTenant.in=" + DEFAULT_PHONE_NUMBER_TENANT + "," + UPDATED_PHONE_NUMBER_TENANT);

        // Get all the tenantList where phoneNumberTenant equals to UPDATED_PHONE_NUMBER_TENANT
        defaultTenantShouldNotBeFound("phoneNumberTenant.in=" + UPDATED_PHONE_NUMBER_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByPhoneNumberTenantIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where phoneNumberTenant is not null
        defaultTenantShouldBeFound("phoneNumberTenant.specified=true");

        // Get all the tenantList where phoneNumberTenant is null
        defaultTenantShouldNotBeFound("phoneNumberTenant.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantsByPhoneNumberTenantContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where phoneNumberTenant contains DEFAULT_PHONE_NUMBER_TENANT
        defaultTenantShouldBeFound("phoneNumberTenant.contains=" + DEFAULT_PHONE_NUMBER_TENANT);

        // Get all the tenantList where phoneNumberTenant contains UPDATED_PHONE_NUMBER_TENANT
        defaultTenantShouldNotBeFound("phoneNumberTenant.contains=" + UPDATED_PHONE_NUMBER_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByPhoneNumberTenantNotContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where phoneNumberTenant does not contain DEFAULT_PHONE_NUMBER_TENANT
        defaultTenantShouldNotBeFound("phoneNumberTenant.doesNotContain=" + DEFAULT_PHONE_NUMBER_TENANT);

        // Get all the tenantList where phoneNumberTenant does not contain UPDATED_PHONE_NUMBER_TENANT
        defaultTenantShouldBeFound("phoneNumberTenant.doesNotContain=" + UPDATED_PHONE_NUMBER_TENANT);
    }


    @Test
    @Transactional
    public void getAllTenantsByWebSiteTenantIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where webSiteTenant equals to DEFAULT_WEB_SITE_TENANT
        defaultTenantShouldBeFound("webSiteTenant.equals=" + DEFAULT_WEB_SITE_TENANT);

        // Get all the tenantList where webSiteTenant equals to UPDATED_WEB_SITE_TENANT
        defaultTenantShouldNotBeFound("webSiteTenant.equals=" + UPDATED_WEB_SITE_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByWebSiteTenantIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where webSiteTenant not equals to DEFAULT_WEB_SITE_TENANT
        defaultTenantShouldNotBeFound("webSiteTenant.notEquals=" + DEFAULT_WEB_SITE_TENANT);

        // Get all the tenantList where webSiteTenant not equals to UPDATED_WEB_SITE_TENANT
        defaultTenantShouldBeFound("webSiteTenant.notEquals=" + UPDATED_WEB_SITE_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByWebSiteTenantIsInShouldWork() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where webSiteTenant in DEFAULT_WEB_SITE_TENANT or UPDATED_WEB_SITE_TENANT
        defaultTenantShouldBeFound("webSiteTenant.in=" + DEFAULT_WEB_SITE_TENANT + "," + UPDATED_WEB_SITE_TENANT);

        // Get all the tenantList where webSiteTenant equals to UPDATED_WEB_SITE_TENANT
        defaultTenantShouldNotBeFound("webSiteTenant.in=" + UPDATED_WEB_SITE_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByWebSiteTenantIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where webSiteTenant is not null
        defaultTenantShouldBeFound("webSiteTenant.specified=true");

        // Get all the tenantList where webSiteTenant is null
        defaultTenantShouldNotBeFound("webSiteTenant.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantsByWebSiteTenantContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where webSiteTenant contains DEFAULT_WEB_SITE_TENANT
        defaultTenantShouldBeFound("webSiteTenant.contains=" + DEFAULT_WEB_SITE_TENANT);

        // Get all the tenantList where webSiteTenant contains UPDATED_WEB_SITE_TENANT
        defaultTenantShouldNotBeFound("webSiteTenant.contains=" + UPDATED_WEB_SITE_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByWebSiteTenantNotContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where webSiteTenant does not contain DEFAULT_WEB_SITE_TENANT
        defaultTenantShouldNotBeFound("webSiteTenant.doesNotContain=" + DEFAULT_WEB_SITE_TENANT);

        // Get all the tenantList where webSiteTenant does not contain UPDATED_WEB_SITE_TENANT
        defaultTenantShouldBeFound("webSiteTenant.doesNotContain=" + UPDATED_WEB_SITE_TENANT);
    }


    @Test
    @Transactional
    public void getAllTenantsByEmailTenantIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where emailTenant equals to DEFAULT_EMAIL_TENANT
        defaultTenantShouldBeFound("emailTenant.equals=" + DEFAULT_EMAIL_TENANT);

        // Get all the tenantList where emailTenant equals to UPDATED_EMAIL_TENANT
        defaultTenantShouldNotBeFound("emailTenant.equals=" + UPDATED_EMAIL_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByEmailTenantIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where emailTenant not equals to DEFAULT_EMAIL_TENANT
        defaultTenantShouldNotBeFound("emailTenant.notEquals=" + DEFAULT_EMAIL_TENANT);

        // Get all the tenantList where emailTenant not equals to UPDATED_EMAIL_TENANT
        defaultTenantShouldBeFound("emailTenant.notEquals=" + UPDATED_EMAIL_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByEmailTenantIsInShouldWork() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where emailTenant in DEFAULT_EMAIL_TENANT or UPDATED_EMAIL_TENANT
        defaultTenantShouldBeFound("emailTenant.in=" + DEFAULT_EMAIL_TENANT + "," + UPDATED_EMAIL_TENANT);

        // Get all the tenantList where emailTenant equals to UPDATED_EMAIL_TENANT
        defaultTenantShouldNotBeFound("emailTenant.in=" + UPDATED_EMAIL_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByEmailTenantIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where emailTenant is not null
        defaultTenantShouldBeFound("emailTenant.specified=true");

        // Get all the tenantList where emailTenant is null
        defaultTenantShouldNotBeFound("emailTenant.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantsByEmailTenantContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where emailTenant contains DEFAULT_EMAIL_TENANT
        defaultTenantShouldBeFound("emailTenant.contains=" + DEFAULT_EMAIL_TENANT);

        // Get all the tenantList where emailTenant contains UPDATED_EMAIL_TENANT
        defaultTenantShouldNotBeFound("emailTenant.contains=" + UPDATED_EMAIL_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByEmailTenantNotContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where emailTenant does not contain DEFAULT_EMAIL_TENANT
        defaultTenantShouldNotBeFound("emailTenant.doesNotContain=" + DEFAULT_EMAIL_TENANT);

        // Get all the tenantList where emailTenant does not contain UPDATED_EMAIL_TENANT
        defaultTenantShouldBeFound("emailTenant.doesNotContain=" + UPDATED_EMAIL_TENANT);
    }


    @Test
    @Transactional
    public void getAllTenantsByFacebookTenantIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where facebookTenant equals to DEFAULT_FACEBOOK_TENANT
        defaultTenantShouldBeFound("facebookTenant.equals=" + DEFAULT_FACEBOOK_TENANT);

        // Get all the tenantList where facebookTenant equals to UPDATED_FACEBOOK_TENANT
        defaultTenantShouldNotBeFound("facebookTenant.equals=" + UPDATED_FACEBOOK_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByFacebookTenantIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where facebookTenant not equals to DEFAULT_FACEBOOK_TENANT
        defaultTenantShouldNotBeFound("facebookTenant.notEquals=" + DEFAULT_FACEBOOK_TENANT);

        // Get all the tenantList where facebookTenant not equals to UPDATED_FACEBOOK_TENANT
        defaultTenantShouldBeFound("facebookTenant.notEquals=" + UPDATED_FACEBOOK_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByFacebookTenantIsInShouldWork() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where facebookTenant in DEFAULT_FACEBOOK_TENANT or UPDATED_FACEBOOK_TENANT
        defaultTenantShouldBeFound("facebookTenant.in=" + DEFAULT_FACEBOOK_TENANT + "," + UPDATED_FACEBOOK_TENANT);

        // Get all the tenantList where facebookTenant equals to UPDATED_FACEBOOK_TENANT
        defaultTenantShouldNotBeFound("facebookTenant.in=" + UPDATED_FACEBOOK_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByFacebookTenantIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where facebookTenant is not null
        defaultTenantShouldBeFound("facebookTenant.specified=true");

        // Get all the tenantList where facebookTenant is null
        defaultTenantShouldNotBeFound("facebookTenant.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantsByFacebookTenantContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where facebookTenant contains DEFAULT_FACEBOOK_TENANT
        defaultTenantShouldBeFound("facebookTenant.contains=" + DEFAULT_FACEBOOK_TENANT);

        // Get all the tenantList where facebookTenant contains UPDATED_FACEBOOK_TENANT
        defaultTenantShouldNotBeFound("facebookTenant.contains=" + UPDATED_FACEBOOK_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByFacebookTenantNotContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where facebookTenant does not contain DEFAULT_FACEBOOK_TENANT
        defaultTenantShouldNotBeFound("facebookTenant.doesNotContain=" + DEFAULT_FACEBOOK_TENANT);

        // Get all the tenantList where facebookTenant does not contain UPDATED_FACEBOOK_TENANT
        defaultTenantShouldBeFound("facebookTenant.doesNotContain=" + UPDATED_FACEBOOK_TENANT);
    }


    @Test
    @Transactional
    public void getAllTenantsByTwitterTenantIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where twitterTenant equals to DEFAULT_TWITTER_TENANT
        defaultTenantShouldBeFound("twitterTenant.equals=" + DEFAULT_TWITTER_TENANT);

        // Get all the tenantList where twitterTenant equals to UPDATED_TWITTER_TENANT
        defaultTenantShouldNotBeFound("twitterTenant.equals=" + UPDATED_TWITTER_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByTwitterTenantIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where twitterTenant not equals to DEFAULT_TWITTER_TENANT
        defaultTenantShouldNotBeFound("twitterTenant.notEquals=" + DEFAULT_TWITTER_TENANT);

        // Get all the tenantList where twitterTenant not equals to UPDATED_TWITTER_TENANT
        defaultTenantShouldBeFound("twitterTenant.notEquals=" + UPDATED_TWITTER_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByTwitterTenantIsInShouldWork() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where twitterTenant in DEFAULT_TWITTER_TENANT or UPDATED_TWITTER_TENANT
        defaultTenantShouldBeFound("twitterTenant.in=" + DEFAULT_TWITTER_TENANT + "," + UPDATED_TWITTER_TENANT);

        // Get all the tenantList where twitterTenant equals to UPDATED_TWITTER_TENANT
        defaultTenantShouldNotBeFound("twitterTenant.in=" + UPDATED_TWITTER_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByTwitterTenantIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where twitterTenant is not null
        defaultTenantShouldBeFound("twitterTenant.specified=true");

        // Get all the tenantList where twitterTenant is null
        defaultTenantShouldNotBeFound("twitterTenant.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantsByTwitterTenantContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where twitterTenant contains DEFAULT_TWITTER_TENANT
        defaultTenantShouldBeFound("twitterTenant.contains=" + DEFAULT_TWITTER_TENANT);

        // Get all the tenantList where twitterTenant contains UPDATED_TWITTER_TENANT
        defaultTenantShouldNotBeFound("twitterTenant.contains=" + UPDATED_TWITTER_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByTwitterTenantNotContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where twitterTenant does not contain DEFAULT_TWITTER_TENANT
        defaultTenantShouldNotBeFound("twitterTenant.doesNotContain=" + DEFAULT_TWITTER_TENANT);

        // Get all the tenantList where twitterTenant does not contain UPDATED_TWITTER_TENANT
        defaultTenantShouldBeFound("twitterTenant.doesNotContain=" + UPDATED_TWITTER_TENANT);
    }


    @Test
    @Transactional
    public void getAllTenantsByInstagramTenantIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where instagramTenant equals to DEFAULT_INSTAGRAM_TENANT
        defaultTenantShouldBeFound("instagramTenant.equals=" + DEFAULT_INSTAGRAM_TENANT);

        // Get all the tenantList where instagramTenant equals to UPDATED_INSTAGRAM_TENANT
        defaultTenantShouldNotBeFound("instagramTenant.equals=" + UPDATED_INSTAGRAM_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByInstagramTenantIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where instagramTenant not equals to DEFAULT_INSTAGRAM_TENANT
        defaultTenantShouldNotBeFound("instagramTenant.notEquals=" + DEFAULT_INSTAGRAM_TENANT);

        // Get all the tenantList where instagramTenant not equals to UPDATED_INSTAGRAM_TENANT
        defaultTenantShouldBeFound("instagramTenant.notEquals=" + UPDATED_INSTAGRAM_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByInstagramTenantIsInShouldWork() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where instagramTenant in DEFAULT_INSTAGRAM_TENANT or UPDATED_INSTAGRAM_TENANT
        defaultTenantShouldBeFound("instagramTenant.in=" + DEFAULT_INSTAGRAM_TENANT + "," + UPDATED_INSTAGRAM_TENANT);

        // Get all the tenantList where instagramTenant equals to UPDATED_INSTAGRAM_TENANT
        defaultTenantShouldNotBeFound("instagramTenant.in=" + UPDATED_INSTAGRAM_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByInstagramTenantIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where instagramTenant is not null
        defaultTenantShouldBeFound("instagramTenant.specified=true");

        // Get all the tenantList where instagramTenant is null
        defaultTenantShouldNotBeFound("instagramTenant.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantsByInstagramTenantContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where instagramTenant contains DEFAULT_INSTAGRAM_TENANT
        defaultTenantShouldBeFound("instagramTenant.contains=" + DEFAULT_INSTAGRAM_TENANT);

        // Get all the tenantList where instagramTenant contains UPDATED_INSTAGRAM_TENANT
        defaultTenantShouldNotBeFound("instagramTenant.contains=" + UPDATED_INSTAGRAM_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByInstagramTenantNotContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where instagramTenant does not contain DEFAULT_INSTAGRAM_TENANT
        defaultTenantShouldNotBeFound("instagramTenant.doesNotContain=" + DEFAULT_INSTAGRAM_TENANT);

        // Get all the tenantList where instagramTenant does not contain UPDATED_INSTAGRAM_TENANT
        defaultTenantShouldBeFound("instagramTenant.doesNotContain=" + UPDATED_INSTAGRAM_TENANT);
    }


    @Test
    @Transactional
    public void getAllTenantsByYoutubeTenantIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where youtubeTenant equals to DEFAULT_YOUTUBE_TENANT
        defaultTenantShouldBeFound("youtubeTenant.equals=" + DEFAULT_YOUTUBE_TENANT);

        // Get all the tenantList where youtubeTenant equals to UPDATED_YOUTUBE_TENANT
        defaultTenantShouldNotBeFound("youtubeTenant.equals=" + UPDATED_YOUTUBE_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByYoutubeTenantIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where youtubeTenant not equals to DEFAULT_YOUTUBE_TENANT
        defaultTenantShouldNotBeFound("youtubeTenant.notEquals=" + DEFAULT_YOUTUBE_TENANT);

        // Get all the tenantList where youtubeTenant not equals to UPDATED_YOUTUBE_TENANT
        defaultTenantShouldBeFound("youtubeTenant.notEquals=" + UPDATED_YOUTUBE_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByYoutubeTenantIsInShouldWork() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where youtubeTenant in DEFAULT_YOUTUBE_TENANT or UPDATED_YOUTUBE_TENANT
        defaultTenantShouldBeFound("youtubeTenant.in=" + DEFAULT_YOUTUBE_TENANT + "," + UPDATED_YOUTUBE_TENANT);

        // Get all the tenantList where youtubeTenant equals to UPDATED_YOUTUBE_TENANT
        defaultTenantShouldNotBeFound("youtubeTenant.in=" + UPDATED_YOUTUBE_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByYoutubeTenantIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where youtubeTenant is not null
        defaultTenantShouldBeFound("youtubeTenant.specified=true");

        // Get all the tenantList where youtubeTenant is null
        defaultTenantShouldNotBeFound("youtubeTenant.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantsByYoutubeTenantContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where youtubeTenant contains DEFAULT_YOUTUBE_TENANT
        defaultTenantShouldBeFound("youtubeTenant.contains=" + DEFAULT_YOUTUBE_TENANT);

        // Get all the tenantList where youtubeTenant contains UPDATED_YOUTUBE_TENANT
        defaultTenantShouldNotBeFound("youtubeTenant.contains=" + UPDATED_YOUTUBE_TENANT);
    }

    @Test
    @Transactional
    public void getAllTenantsByYoutubeTenantNotContainsSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        // Get all the tenantList where youtubeTenant does not contain DEFAULT_YOUTUBE_TENANT
        defaultTenantShouldNotBeFound("youtubeTenant.doesNotContain=" + DEFAULT_YOUTUBE_TENANT);

        // Get all the tenantList where youtubeTenant does not contain UPDATED_YOUTUBE_TENANT
        defaultTenantShouldBeFound("youtubeTenant.doesNotContain=" + UPDATED_YOUTUBE_TENANT);
    }


    @Test
    @Transactional
    public void getAllTenantsByTenantSuperAdminIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);
        TenantSuperAdmin tenantSuperAdmin = TenantSuperAdminResourceIT.createEntity(em);
        em.persist(tenantSuperAdmin);
        em.flush();
        tenant.addTenantSuperAdmin(tenantSuperAdmin);
        tenantRepository.saveAndFlush(tenant);
        Long tenantSuperAdminId = tenantSuperAdmin.getId();

        // Get all the tenantList where tenantSuperAdmin equals to tenantSuperAdminId
        defaultTenantShouldBeFound("tenantSuperAdminId.equals=" + tenantSuperAdminId);

        // Get all the tenantList where tenantSuperAdmin equals to tenantSuperAdminId + 1
        defaultTenantShouldNotBeFound("tenantSuperAdminId.equals=" + (tenantSuperAdminId + 1));
    }


    @Test
    @Transactional
    public void getAllTenantsByTenantUserIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);
        TenantUser tenantUser = TenantUserResourceIT.createEntity(em);
        em.persist(tenantUser);
        em.flush();
        tenant.addTenantUser(tenantUser);
        tenantRepository.saveAndFlush(tenant);
        Long tenantUserId = tenantUser.getId();

        // Get all the tenantList where tenantUser equals to tenantUserId
        defaultTenantShouldBeFound("tenantUserId.equals=" + tenantUserId);

        // Get all the tenantList where tenantUser equals to tenantUserId + 1
        defaultTenantShouldNotBeFound("tenantUserId.equals=" + (tenantUserId + 1));
    }


    @Test
    @Transactional
    public void getAllTenantsBySiteIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);
        Site site = SiteResourceIT.createEntity(em);
        em.persist(site);
        em.flush();
        tenant.addSite(site);
        tenantRepository.saveAndFlush(tenant);
        Long siteId = site.getId();

        // Get all the tenantList where site equals to siteId
        defaultTenantShouldBeFound("siteId.equals=" + siteId);

        // Get all the tenantList where site equals to siteId + 1
        defaultTenantShouldNotBeFound("siteId.equals=" + (siteId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTenantShouldBeFound(String filter) throws Exception {
        restTenantMockMvc.perform(get("/api/tenants?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenant.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTenant").value(hasItem(DEFAULT_ID_TENANT)))
            .andExpect(jsonPath("$.[*].logoTenat").value(hasItem(DEFAULT_LOGO_TENAT)))
            .andExpect(jsonPath("$.[*].spaceNameTenant").value(hasItem(DEFAULT_SPACE_NAME_TENANT)))
            .andExpect(jsonPath("$.[*].phoneNumberTenant").value(hasItem(DEFAULT_PHONE_NUMBER_TENANT)))
            .andExpect(jsonPath("$.[*].webSiteTenant").value(hasItem(DEFAULT_WEB_SITE_TENANT)))
            .andExpect(jsonPath("$.[*].emailTenant").value(hasItem(DEFAULT_EMAIL_TENANT)))
            .andExpect(jsonPath("$.[*].facebookTenant").value(hasItem(DEFAULT_FACEBOOK_TENANT)))
            .andExpect(jsonPath("$.[*].twitterTenant").value(hasItem(DEFAULT_TWITTER_TENANT)))
            .andExpect(jsonPath("$.[*].instagramTenant").value(hasItem(DEFAULT_INSTAGRAM_TENANT)))
            .andExpect(jsonPath("$.[*].youtubeTenant").value(hasItem(DEFAULT_YOUTUBE_TENANT)));

        // Check, that the count call also returns 1
        restTenantMockMvc.perform(get("/api/tenants/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTenantShouldNotBeFound(String filter) throws Exception {
        restTenantMockMvc.perform(get("/api/tenants?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTenantMockMvc.perform(get("/api/tenants/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingTenant() throws Exception {
        // Get the tenant
        restTenantMockMvc.perform(get("/api/tenants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTenant() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        int databaseSizeBeforeUpdate = tenantRepository.findAll().size();

        // Update the tenant
        Tenant updatedTenant = tenantRepository.findById(tenant.getId()).get();
        // Disconnect from session so that the updates on updatedTenant are not directly saved in db
        em.detach(updatedTenant);
        updatedTenant
            .idTenant(UPDATED_ID_TENANT)
            .logoTenat(UPDATED_LOGO_TENAT)
            .spaceNameTenant(UPDATED_SPACE_NAME_TENANT)
            .phoneNumberTenant(UPDATED_PHONE_NUMBER_TENANT)
            .webSiteTenant(UPDATED_WEB_SITE_TENANT)
            .emailTenant(UPDATED_EMAIL_TENANT)
            .facebookTenant(UPDATED_FACEBOOK_TENANT)
            .twitterTenant(UPDATED_TWITTER_TENANT)
            .instagramTenant(UPDATED_INSTAGRAM_TENANT)
            .youtubeTenant(UPDATED_YOUTUBE_TENANT);
        TenantDTO tenantDTO = tenantMapper.toDto(updatedTenant);

        restTenantMockMvc.perform(put("/api/tenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tenantDTO)))
            .andExpect(status().isOk());

        // Validate the Tenant in the database
        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeUpdate);
        Tenant testTenant = tenantList.get(tenantList.size() - 1);
        assertThat(testTenant.getIdTenant()).isEqualTo(UPDATED_ID_TENANT);
        assertThat(testTenant.getLogoTenat()).isEqualTo(UPDATED_LOGO_TENAT);
        assertThat(testTenant.getSpaceNameTenant()).isEqualTo(UPDATED_SPACE_NAME_TENANT);
        assertThat(testTenant.getPhoneNumberTenant()).isEqualTo(UPDATED_PHONE_NUMBER_TENANT);
        assertThat(testTenant.getWebSiteTenant()).isEqualTo(UPDATED_WEB_SITE_TENANT);
        assertThat(testTenant.getEmailTenant()).isEqualTo(UPDATED_EMAIL_TENANT);
        assertThat(testTenant.getFacebookTenant()).isEqualTo(UPDATED_FACEBOOK_TENANT);
        assertThat(testTenant.getTwitterTenant()).isEqualTo(UPDATED_TWITTER_TENANT);
        assertThat(testTenant.getInstagramTenant()).isEqualTo(UPDATED_INSTAGRAM_TENANT);
        assertThat(testTenant.getYoutubeTenant()).isEqualTo(UPDATED_YOUTUBE_TENANT);
    }

    @Test
    @Transactional
    public void updateNonExistingTenant() throws Exception {
        int databaseSizeBeforeUpdate = tenantRepository.findAll().size();

        // Create the Tenant
        TenantDTO tenantDTO = tenantMapper.toDto(tenant);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenantMockMvc.perform(put("/api/tenants")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tenantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tenant in the database
        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTenant() throws Exception {
        // Initialize the database
        tenantRepository.saveAndFlush(tenant);

        int databaseSizeBeforeDelete = tenantRepository.findAll().size();

        // Delete the tenant
        restTenantMockMvc.perform(delete("/api/tenants/{id}", tenant.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tenant> tenantList = tenantRepository.findAll();
        assertThat(tenantList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
