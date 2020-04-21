package com.lm.portal.web.rest;

import com.lm.portal.LmPortal2App;
import com.lm.portal.domain.TenantUser;
import com.lm.portal.domain.Tenant;
import com.lm.portal.repository.TenantUserRepository;
import com.lm.portal.service.TenantUserService;
import com.lm.portal.service.dto.TenantUserDTO;
import com.lm.portal.service.mapper.TenantUserMapper;
import com.lm.portal.service.dto.TenantUserCriteria;
import com.lm.portal.service.TenantUserQueryService;

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
    private TenantUserQueryService tenantUserQueryService;

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
    public void getTenantUsersByIdFiltering() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        Long id = tenantUser.getId();

        defaultTenantUserShouldBeFound("id.equals=" + id);
        defaultTenantUserShouldNotBeFound("id.notEquals=" + id);

        defaultTenantUserShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultTenantUserShouldNotBeFound("id.greaterThan=" + id);

        defaultTenantUserShouldBeFound("id.lessThanOrEqual=" + id);
        defaultTenantUserShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllTenantUsersByFirstNameTenantUserIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where firstNameTenantUser equals to DEFAULT_FIRST_NAME_TENANT_USER
        defaultTenantUserShouldBeFound("firstNameTenantUser.equals=" + DEFAULT_FIRST_NAME_TENANT_USER);

        // Get all the tenantUserList where firstNameTenantUser equals to UPDATED_FIRST_NAME_TENANT_USER
        defaultTenantUserShouldNotBeFound("firstNameTenantUser.equals=" + UPDATED_FIRST_NAME_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByFirstNameTenantUserIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where firstNameTenantUser not equals to DEFAULT_FIRST_NAME_TENANT_USER
        defaultTenantUserShouldNotBeFound("firstNameTenantUser.notEquals=" + DEFAULT_FIRST_NAME_TENANT_USER);

        // Get all the tenantUserList where firstNameTenantUser not equals to UPDATED_FIRST_NAME_TENANT_USER
        defaultTenantUserShouldBeFound("firstNameTenantUser.notEquals=" + UPDATED_FIRST_NAME_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByFirstNameTenantUserIsInShouldWork() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where firstNameTenantUser in DEFAULT_FIRST_NAME_TENANT_USER or UPDATED_FIRST_NAME_TENANT_USER
        defaultTenantUserShouldBeFound("firstNameTenantUser.in=" + DEFAULT_FIRST_NAME_TENANT_USER + "," + UPDATED_FIRST_NAME_TENANT_USER);

        // Get all the tenantUserList where firstNameTenantUser equals to UPDATED_FIRST_NAME_TENANT_USER
        defaultTenantUserShouldNotBeFound("firstNameTenantUser.in=" + UPDATED_FIRST_NAME_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByFirstNameTenantUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where firstNameTenantUser is not null
        defaultTenantUserShouldBeFound("firstNameTenantUser.specified=true");

        // Get all the tenantUserList where firstNameTenantUser is null
        defaultTenantUserShouldNotBeFound("firstNameTenantUser.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantUsersByFirstNameTenantUserContainsSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where firstNameTenantUser contains DEFAULT_FIRST_NAME_TENANT_USER
        defaultTenantUserShouldBeFound("firstNameTenantUser.contains=" + DEFAULT_FIRST_NAME_TENANT_USER);

        // Get all the tenantUserList where firstNameTenantUser contains UPDATED_FIRST_NAME_TENANT_USER
        defaultTenantUserShouldNotBeFound("firstNameTenantUser.contains=" + UPDATED_FIRST_NAME_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByFirstNameTenantUserNotContainsSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where firstNameTenantUser does not contain DEFAULT_FIRST_NAME_TENANT_USER
        defaultTenantUserShouldNotBeFound("firstNameTenantUser.doesNotContain=" + DEFAULT_FIRST_NAME_TENANT_USER);

        // Get all the tenantUserList where firstNameTenantUser does not contain UPDATED_FIRST_NAME_TENANT_USER
        defaultTenantUserShouldBeFound("firstNameTenantUser.doesNotContain=" + UPDATED_FIRST_NAME_TENANT_USER);
    }


    @Test
    @Transactional
    public void getAllTenantUsersByLastNameTenantUserIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where lastNameTenantUser equals to DEFAULT_LAST_NAME_TENANT_USER
        defaultTenantUserShouldBeFound("lastNameTenantUser.equals=" + DEFAULT_LAST_NAME_TENANT_USER);

        // Get all the tenantUserList where lastNameTenantUser equals to UPDATED_LAST_NAME_TENANT_USER
        defaultTenantUserShouldNotBeFound("lastNameTenantUser.equals=" + UPDATED_LAST_NAME_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByLastNameTenantUserIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where lastNameTenantUser not equals to DEFAULT_LAST_NAME_TENANT_USER
        defaultTenantUserShouldNotBeFound("lastNameTenantUser.notEquals=" + DEFAULT_LAST_NAME_TENANT_USER);

        // Get all the tenantUserList where lastNameTenantUser not equals to UPDATED_LAST_NAME_TENANT_USER
        defaultTenantUserShouldBeFound("lastNameTenantUser.notEquals=" + UPDATED_LAST_NAME_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByLastNameTenantUserIsInShouldWork() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where lastNameTenantUser in DEFAULT_LAST_NAME_TENANT_USER or UPDATED_LAST_NAME_TENANT_USER
        defaultTenantUserShouldBeFound("lastNameTenantUser.in=" + DEFAULT_LAST_NAME_TENANT_USER + "," + UPDATED_LAST_NAME_TENANT_USER);

        // Get all the tenantUserList where lastNameTenantUser equals to UPDATED_LAST_NAME_TENANT_USER
        defaultTenantUserShouldNotBeFound("lastNameTenantUser.in=" + UPDATED_LAST_NAME_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByLastNameTenantUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where lastNameTenantUser is not null
        defaultTenantUserShouldBeFound("lastNameTenantUser.specified=true");

        // Get all the tenantUserList where lastNameTenantUser is null
        defaultTenantUserShouldNotBeFound("lastNameTenantUser.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantUsersByLastNameTenantUserContainsSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where lastNameTenantUser contains DEFAULT_LAST_NAME_TENANT_USER
        defaultTenantUserShouldBeFound("lastNameTenantUser.contains=" + DEFAULT_LAST_NAME_TENANT_USER);

        // Get all the tenantUserList where lastNameTenantUser contains UPDATED_LAST_NAME_TENANT_USER
        defaultTenantUserShouldNotBeFound("lastNameTenantUser.contains=" + UPDATED_LAST_NAME_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByLastNameTenantUserNotContainsSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where lastNameTenantUser does not contain DEFAULT_LAST_NAME_TENANT_USER
        defaultTenantUserShouldNotBeFound("lastNameTenantUser.doesNotContain=" + DEFAULT_LAST_NAME_TENANT_USER);

        // Get all the tenantUserList where lastNameTenantUser does not contain UPDATED_LAST_NAME_TENANT_USER
        defaultTenantUserShouldBeFound("lastNameTenantUser.doesNotContain=" + UPDATED_LAST_NAME_TENANT_USER);
    }


    @Test
    @Transactional
    public void getAllTenantUsersByUserNameTenantUserIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where userNameTenantUser equals to DEFAULT_USER_NAME_TENANT_USER
        defaultTenantUserShouldBeFound("userNameTenantUser.equals=" + DEFAULT_USER_NAME_TENANT_USER);

        // Get all the tenantUserList where userNameTenantUser equals to UPDATED_USER_NAME_TENANT_USER
        defaultTenantUserShouldNotBeFound("userNameTenantUser.equals=" + UPDATED_USER_NAME_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByUserNameTenantUserIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where userNameTenantUser not equals to DEFAULT_USER_NAME_TENANT_USER
        defaultTenantUserShouldNotBeFound("userNameTenantUser.notEquals=" + DEFAULT_USER_NAME_TENANT_USER);

        // Get all the tenantUserList where userNameTenantUser not equals to UPDATED_USER_NAME_TENANT_USER
        defaultTenantUserShouldBeFound("userNameTenantUser.notEquals=" + UPDATED_USER_NAME_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByUserNameTenantUserIsInShouldWork() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where userNameTenantUser in DEFAULT_USER_NAME_TENANT_USER or UPDATED_USER_NAME_TENANT_USER
        defaultTenantUserShouldBeFound("userNameTenantUser.in=" + DEFAULT_USER_NAME_TENANT_USER + "," + UPDATED_USER_NAME_TENANT_USER);

        // Get all the tenantUserList where userNameTenantUser equals to UPDATED_USER_NAME_TENANT_USER
        defaultTenantUserShouldNotBeFound("userNameTenantUser.in=" + UPDATED_USER_NAME_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByUserNameTenantUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where userNameTenantUser is not null
        defaultTenantUserShouldBeFound("userNameTenantUser.specified=true");

        // Get all the tenantUserList where userNameTenantUser is null
        defaultTenantUserShouldNotBeFound("userNameTenantUser.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantUsersByUserNameTenantUserContainsSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where userNameTenantUser contains DEFAULT_USER_NAME_TENANT_USER
        defaultTenantUserShouldBeFound("userNameTenantUser.contains=" + DEFAULT_USER_NAME_TENANT_USER);

        // Get all the tenantUserList where userNameTenantUser contains UPDATED_USER_NAME_TENANT_USER
        defaultTenantUserShouldNotBeFound("userNameTenantUser.contains=" + UPDATED_USER_NAME_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByUserNameTenantUserNotContainsSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where userNameTenantUser does not contain DEFAULT_USER_NAME_TENANT_USER
        defaultTenantUserShouldNotBeFound("userNameTenantUser.doesNotContain=" + DEFAULT_USER_NAME_TENANT_USER);

        // Get all the tenantUserList where userNameTenantUser does not contain UPDATED_USER_NAME_TENANT_USER
        defaultTenantUserShouldBeFound("userNameTenantUser.doesNotContain=" + UPDATED_USER_NAME_TENANT_USER);
    }


    @Test
    @Transactional
    public void getAllTenantUsersByEmailTenantUserIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where emailTenantUser equals to DEFAULT_EMAIL_TENANT_USER
        defaultTenantUserShouldBeFound("emailTenantUser.equals=" + DEFAULT_EMAIL_TENANT_USER);

        // Get all the tenantUserList where emailTenantUser equals to UPDATED_EMAIL_TENANT_USER
        defaultTenantUserShouldNotBeFound("emailTenantUser.equals=" + UPDATED_EMAIL_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByEmailTenantUserIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where emailTenantUser not equals to DEFAULT_EMAIL_TENANT_USER
        defaultTenantUserShouldNotBeFound("emailTenantUser.notEquals=" + DEFAULT_EMAIL_TENANT_USER);

        // Get all the tenantUserList where emailTenantUser not equals to UPDATED_EMAIL_TENANT_USER
        defaultTenantUserShouldBeFound("emailTenantUser.notEquals=" + UPDATED_EMAIL_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByEmailTenantUserIsInShouldWork() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where emailTenantUser in DEFAULT_EMAIL_TENANT_USER or UPDATED_EMAIL_TENANT_USER
        defaultTenantUserShouldBeFound("emailTenantUser.in=" + DEFAULT_EMAIL_TENANT_USER + "," + UPDATED_EMAIL_TENANT_USER);

        // Get all the tenantUserList where emailTenantUser equals to UPDATED_EMAIL_TENANT_USER
        defaultTenantUserShouldNotBeFound("emailTenantUser.in=" + UPDATED_EMAIL_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByEmailTenantUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where emailTenantUser is not null
        defaultTenantUserShouldBeFound("emailTenantUser.specified=true");

        // Get all the tenantUserList where emailTenantUser is null
        defaultTenantUserShouldNotBeFound("emailTenantUser.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantUsersByEmailTenantUserContainsSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where emailTenantUser contains DEFAULT_EMAIL_TENANT_USER
        defaultTenantUserShouldBeFound("emailTenantUser.contains=" + DEFAULT_EMAIL_TENANT_USER);

        // Get all the tenantUserList where emailTenantUser contains UPDATED_EMAIL_TENANT_USER
        defaultTenantUserShouldNotBeFound("emailTenantUser.contains=" + UPDATED_EMAIL_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByEmailTenantUserNotContainsSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where emailTenantUser does not contain DEFAULT_EMAIL_TENANT_USER
        defaultTenantUserShouldNotBeFound("emailTenantUser.doesNotContain=" + DEFAULT_EMAIL_TENANT_USER);

        // Get all the tenantUserList where emailTenantUser does not contain UPDATED_EMAIL_TENANT_USER
        defaultTenantUserShouldBeFound("emailTenantUser.doesNotContain=" + UPDATED_EMAIL_TENANT_USER);
    }


    @Test
    @Transactional
    public void getAllTenantUsersByPasswordTenantUserIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where passwordTenantUser equals to DEFAULT_PASSWORD_TENANT_USER
        defaultTenantUserShouldBeFound("passwordTenantUser.equals=" + DEFAULT_PASSWORD_TENANT_USER);

        // Get all the tenantUserList where passwordTenantUser equals to UPDATED_PASSWORD_TENANT_USER
        defaultTenantUserShouldNotBeFound("passwordTenantUser.equals=" + UPDATED_PASSWORD_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByPasswordTenantUserIsNotEqualToSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where passwordTenantUser not equals to DEFAULT_PASSWORD_TENANT_USER
        defaultTenantUserShouldNotBeFound("passwordTenantUser.notEquals=" + DEFAULT_PASSWORD_TENANT_USER);

        // Get all the tenantUserList where passwordTenantUser not equals to UPDATED_PASSWORD_TENANT_USER
        defaultTenantUserShouldBeFound("passwordTenantUser.notEquals=" + UPDATED_PASSWORD_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByPasswordTenantUserIsInShouldWork() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where passwordTenantUser in DEFAULT_PASSWORD_TENANT_USER or UPDATED_PASSWORD_TENANT_USER
        defaultTenantUserShouldBeFound("passwordTenantUser.in=" + DEFAULT_PASSWORD_TENANT_USER + "," + UPDATED_PASSWORD_TENANT_USER);

        // Get all the tenantUserList where passwordTenantUser equals to UPDATED_PASSWORD_TENANT_USER
        defaultTenantUserShouldNotBeFound("passwordTenantUser.in=" + UPDATED_PASSWORD_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByPasswordTenantUserIsNullOrNotNull() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where passwordTenantUser is not null
        defaultTenantUserShouldBeFound("passwordTenantUser.specified=true");

        // Get all the tenantUserList where passwordTenantUser is null
        defaultTenantUserShouldNotBeFound("passwordTenantUser.specified=false");
    }
                @Test
    @Transactional
    public void getAllTenantUsersByPasswordTenantUserContainsSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where passwordTenantUser contains DEFAULT_PASSWORD_TENANT_USER
        defaultTenantUserShouldBeFound("passwordTenantUser.contains=" + DEFAULT_PASSWORD_TENANT_USER);

        // Get all the tenantUserList where passwordTenantUser contains UPDATED_PASSWORD_TENANT_USER
        defaultTenantUserShouldNotBeFound("passwordTenantUser.contains=" + UPDATED_PASSWORD_TENANT_USER);
    }

    @Test
    @Transactional
    public void getAllTenantUsersByPasswordTenantUserNotContainsSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);

        // Get all the tenantUserList where passwordTenantUser does not contain DEFAULT_PASSWORD_TENANT_USER
        defaultTenantUserShouldNotBeFound("passwordTenantUser.doesNotContain=" + DEFAULT_PASSWORD_TENANT_USER);

        // Get all the tenantUserList where passwordTenantUser does not contain UPDATED_PASSWORD_TENANT_USER
        defaultTenantUserShouldBeFound("passwordTenantUser.doesNotContain=" + UPDATED_PASSWORD_TENANT_USER);
    }


    @Test
    @Transactional
    public void getAllTenantUsersByTenantIsEqualToSomething() throws Exception {
        // Initialize the database
        tenantUserRepository.saveAndFlush(tenantUser);
        Tenant tenant = TenantResourceIT.createEntity(em);
        em.persist(tenant);
        em.flush();
        tenantUser.setTenant(tenant);
        tenantUserRepository.saveAndFlush(tenantUser);
        Long tenantId = tenant.getId();

        // Get all the tenantUserList where tenant equals to tenantId
        defaultTenantUserShouldBeFound("tenantId.equals=" + tenantId);

        // Get all the tenantUserList where tenant equals to tenantId + 1
        defaultTenantUserShouldNotBeFound("tenantId.equals=" + (tenantId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultTenantUserShouldBeFound(String filter) throws Exception {
        restTenantUserMockMvc.perform(get("/api/tenant-users?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tenantUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstNameTenantUser").value(hasItem(DEFAULT_FIRST_NAME_TENANT_USER)))
            .andExpect(jsonPath("$.[*].lastNameTenantUser").value(hasItem(DEFAULT_LAST_NAME_TENANT_USER)))
            .andExpect(jsonPath("$.[*].userNameTenantUser").value(hasItem(DEFAULT_USER_NAME_TENANT_USER)))
            .andExpect(jsonPath("$.[*].emailTenantUser").value(hasItem(DEFAULT_EMAIL_TENANT_USER)))
            .andExpect(jsonPath("$.[*].passwordTenantUser").value(hasItem(DEFAULT_PASSWORD_TENANT_USER)));

        // Check, that the count call also returns 1
        restTenantUserMockMvc.perform(get("/api/tenant-users/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultTenantUserShouldNotBeFound(String filter) throws Exception {
        restTenantUserMockMvc.perform(get("/api/tenant-users?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restTenantUserMockMvc.perform(get("/api/tenant-users/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
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
