package com.lm.portal.web.rest;

import com.lm.portal.LmPortal2App;
import com.lm.portal.domain.Tenant;
import com.lm.portal.repository.TenantRepository;
import com.lm.portal.service.TenantService;
import com.lm.portal.service.dto.TenantDTO;
import com.lm.portal.service.mapper.TenantMapper;

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
