package com.lm.portal.web.rest;

import com.lm.portal.LmPortal2App;
import com.lm.portal.domain.TenantUser;
import com.lm.portal.repository.TenantUserRepository;
import com.lm.portal.service.TenantUserService;
import com.lm.portal.service.dto.TenantUserDTO;
import com.lm.portal.service.mapper.TenantUserMapper;

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
 * Integration tests for the {@link TenantUserResource} REST controller.
 */
@SpringBootTest(classes = LmPortal2App.class)

@AutoConfigureMockMvc
@WithMockUser
public class TenantUserResourceIT {

    private static final String DEFAULT_FIRST_NAME_TENANT_USER = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME_TENANT_USER = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME_TENANT_USER = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME_TENANT_USER = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME_TENANT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME_TENANT_USER = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_TENANT_USER = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_TENANT_USER = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD_TENANT_USER = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD_TENANT_USER = "BBBBBBBBBB";

    @Autowired
    private TenantUserRepository tenantUserRepository;

    @Autowired
    private TenantUserMapper tenantUserMapper;

    @Autowired
    private TenantUserService tenantUserService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenantUserMockMvc;

    private TenantUser tenantUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenantUser createEntity(EntityManager em) {
        TenantUser tenantUser = new TenantUser()
            .firstNameTenantUser(DEFAULT_FIRST_NAME_TENANT_USER)
            .lastNameTenantUser(DEFAULT_LAST_NAME_TENANT_USER)
            .userNameTenantUser(DEFAULT_USER_NAME_TENANT_USER)
            .emailTenantUser(DEFAULT_EMAIL_TENANT_USER)
            .passwordTenantUser(DEFAULT_PASSWORD_TENANT_USER);
        return tenantUser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenantUser createUpdatedEntity(EntityManager em) {
        TenantUser tenantUser = new TenantUser()
            .firstNameTenantUser(UPDATED_FIRST_NAME_TENANT_USER)
            .lastNameTenantUser(UPDATED_LAST_NAME_TENANT_USER)
            .userNameTenantUser(UPDATED_USER_NAME_TENANT_USER)
            .emailTenantUser(UPDATED_EMAIL_TENANT_USER)
            .passwordTenantUser(UPDATED_PASSWORD_TENANT_USER);
        return tenantUser;
    }

    @BeforeEach
    public void initTest() {
        tenantUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createTenantUser() throws Exception {
        int databaseSizeBeforeCreate = tenantUserRepository.findAll().size();

        // Create the TenantUser
        TenantUserDTO tenantUserDTO = tenantUserMapper.toDto(tenantUser);
        restTenantUserMockMvc.perform(post("/api/tenant-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tenantUserDTO)))
            .andExpect(status().isCreated());

        // Validate the TenantUser in the database
        List<TenantUser> tenantUserList = tenantUserRepository.findAll();
        assertThat(tenantUserList).hasSize(databaseSizeBeforeCreate + 1);
        TenantUser testTenantUser = tenantUserList.get(tenantUserList.size() - 1);
        assertThat(testTenantUser.getFirstNameTenantUser()).isEqualTo(DEFAULT_FIRST_NAME_TENANT_USER);
        assertThat(testTenantUser.getLastNameTenantUser()).isEqualTo(DEFAULT_LAST_NAME_TENANT_USER);
        assertThat(testTenantUser.getUserNameTenantUser()).isEqualTo(DEFAULT_USER_NAME_TENANT_USER);
        assertThat(testTenantUser.getEmailTenantUser()).isEqualTo(DEFAULT_EMAIL_TENANT_USER);
        assertThat(testTenantUser.getPasswordTenantUser()).isEqualTo(DEFAULT_PASSWORD_TENANT_USER);
    }

    @Test
    @Transactional
    public void createTenantUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tenantUserRepository.findAll().size();

        // Create the TenantUser with an existing ID
        tenantUser.setId(1L);
        TenantUserDTO tenantUserDTO = tenantUserMapper.toDto(tenantUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenantUserMockMvc.perform(post("/api/tenant-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tenantUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TenantUser in the database
        List<TenantUser> tenantUserList = tenantUserRepository.findAll();
        assertThat(tenantUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTenantUsers() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList
        restTenantUserMockMvc.perform(get("/api/tenant-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenantUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstNameTenantUser").value(hasItem(DEFAULT_FIRST_NAME_TENANT_USER)))
            .andExpect(jsonPath("$.[*].lastNameTenantUser").value(hasItem(DEFAULT_LAST_NAME_TENANT_USER)))
            .andExpect(jsonPath("$.[*].userNameTenantUser").value(hasItem(DEFAULT_USER_NAME_TENANT_USER)))
            .andExpect(jsonPath("$.[*].emailTenantUser").value(hasItem(DEFAULT_EMAIL_TENANT_USER)))
            .andExpect(jsonPath("$.[*].passwordTenantUser").value(hasItem(DEFAULT_PASSWORD_TENANT_USER)));
    }
    
    @Test
    @Transactional
    public void getTenantUser() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get the tenantUser
        restTenantUserMockMvc.perform(get("/api/tenant-users/{id}", tenantUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tenantUser.getId().intValue()))
            .andExpect(jsonPath("$.firstNameTenantUser").value(DEFAULT_FIRST_NAME_TENANT_USER))
            .andExpect(jsonPath("$.lastNameTenantUser").value(DEFAULT_LAST_NAME_TENANT_USER))
            .andExpect(jsonPath("$.userNameTenantUser").value(DEFAULT_USER_NAME_TENANT_USER))
            .andExpect(jsonPath("$.emailTenantUser").value(DEFAULT_EMAIL_TENANT_USER))
            .andExpect(jsonPath("$.passwordTenantUser").value(DEFAULT_PASSWORD_TENANT_USER));
    }

    @Test
    @Transactional
    public void getNonExistingTenantUser() throws Exception {
        // Get the tenantUser
        restTenantUserMockMvc.perform(get("/api/tenant-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTenantUser() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        int databaseSizeBeforeUpdate = tenantUserRepository.findAll().size();

        // Update the tenantUser
        TenantUser updatedTenantUser = tenantUserRepository.findById(tenantUser.getId()).get();
        // Disconnect from session so that the updates on updatedTenantUser are not directly saved in db
        em.detach(updatedTenantUser);
        updatedTenantUser
            .firstNameTenantUser(UPDATED_FIRST_NAME_TENANT_USER)
            .lastNameTenantUser(UPDATED_LAST_NAME_TENANT_USER)
            .userNameTenantUser(UPDATED_USER_NAME_TENANT_USER)
            .emailTenantUser(UPDATED_EMAIL_TENANT_USER)
            .passwordTenantUser(UPDATED_PASSWORD_TENANT_USER);
        TenantUserDTO tenantUserDTO = tenantUserMapper.toDto(updatedTenantUser);

        restTenantUserMockMvc.perform(put("/api/tenant-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tenantUserDTO)))
            .andExpect(status().isOk());

        // Validate the TenantUser in the database
        List<TenantUser> tenantUserList = tenantUserRepository.findAll();
        assertThat(tenantUserList).hasSize(databaseSizeBeforeUpdate);
        TenantUser testTenantUser = tenantUserList.get(tenantUserList.size() - 1);
        assertThat(testTenantUser.getFirstNameTenantUser()).isEqualTo(UPDATED_FIRST_NAME_TENANT_USER);
        assertThat(testTenantUser.getLastNameTenantUser()).isEqualTo(UPDATED_LAST_NAME_TENANT_USER);
        assertThat(testTenantUser.getUserNameTenantUser()).isEqualTo(UPDATED_USER_NAME_TENANT_USER);
        assertThat(testTenantUser.getEmailTenantUser()).isEqualTo(UPDATED_EMAIL_TENANT_USER);
        assertThat(testTenantUser.getPasswordTenantUser()).isEqualTo(UPDATED_PASSWORD_TENANT_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingTenantUser() throws Exception {
        int databaseSizeBeforeUpdate = tenantUserRepository.findAll().size();

        // Create the TenantUser
        TenantUserDTO tenantUserDTO = tenantUserMapper.toDto(tenantUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenantUserMockMvc.perform(put("/api/tenant-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tenantUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TenantUser in the database
        List<TenantUser> tenantUserList = tenantUserRepository.findAll();
        assertThat(tenantUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTenantUser() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        int databaseSizeBeforeDelete = tenantUserRepository.findAll().size();

        // Delete the tenantUser
        restTenantUserMockMvc.perform(delete("/api/tenant-users/{id}", tenantUser.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TenantUser> tenantUserList = tenantUserRepository.findAll();
        assertThat(tenantUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
