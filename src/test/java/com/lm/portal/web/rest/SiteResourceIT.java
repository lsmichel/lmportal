package com.lm.portal.web.rest;

import com.lm.portal.LmPortal2App;
import com.lm.portal.domain.Site;
import com.lm.portal.domain.Tenant;
import com.lm.portal.domain.Location;
import com.lm.portal.repository.SiteRepository;
import com.lm.portal.service.SiteService;
import com.lm.portal.service.dto.SiteDTO;
import com.lm.portal.service.mapper.SiteMapper;
import com.lm.portal.service.dto.SiteCriteria;
import com.lm.portal.service.SiteQueryService;

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
 * Integration tests for the {@link SiteResource} REST controller.
 */
@SpringBootTest(classes = LmPortal2App.class)

@AutoConfigureMockMvc
@WithMockUser
public class SiteResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private SiteMapper siteMapper;

    @Autowired
    private SiteService siteService;

    @Autowired
    private SiteQueryService siteQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSiteMockMvc;

    private Site site;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Site createEntity(EntityManager em) {
        Site site = new Site()
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION);
        return site;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Site createUpdatedEntity(EntityManager em) {
        Site site = new Site()
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);
        return site;
    }

    @BeforeEach
    public void initTest() {
        site = createEntity(em);
    }

    @Test
    @Transactional
    public void createSite() throws Exception {
        int databaseSizeBeforeCreate = siteRepository.findAll().size();

        // Create the Site
        SiteDTO siteDTO = siteMapper.toDto(site);
        restSiteMockMvc.perform(post("/api/sites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siteDTO)))
            .andExpect(status().isCreated());

        // Validate the Site in the database
        List<Site> siteList = siteRepository.findAll();
        assertThat(siteList).hasSize(databaseSizeBeforeCreate + 1);
        Site testSite = siteList.get(siteList.size() - 1);
        assertThat(testSite.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testSite.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createSiteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = siteRepository.findAll().size();

        // Create the Site with an existing ID
        site.setId(1L);
        SiteDTO siteDTO = siteMapper.toDto(site);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSiteMockMvc.perform(post("/api/sites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Site in the database
        List<Site> siteList = siteRepository.findAll();
        assertThat(siteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSites() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        // Get all the siteList
        restSiteMockMvc.perform(get("/api/sites?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(site.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getSite() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        // Get the site
        restSiteMockMvc.perform(get("/api/sites/{id}", site.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(site.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }


    @Test
    @Transactional
    public void getSitesByIdFiltering() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        Long id = site.getId();

        defaultSiteShouldBeFound("id.equals=" + id);
        defaultSiteShouldNotBeFound("id.notEquals=" + id);

        defaultSiteShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultSiteShouldNotBeFound("id.greaterThan=" + id);

        defaultSiteShouldBeFound("id.lessThanOrEqual=" + id);
        defaultSiteShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllSitesByLibelleIsEqualToSomething() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        // Get all the siteList where libelle equals to DEFAULT_LIBELLE
        defaultSiteShouldBeFound("libelle.equals=" + DEFAULT_LIBELLE);

        // Get all the siteList where libelle equals to UPDATED_LIBELLE
        defaultSiteShouldNotBeFound("libelle.equals=" + UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void getAllSitesByLibelleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        // Get all the siteList where libelle not equals to DEFAULT_LIBELLE
        defaultSiteShouldNotBeFound("libelle.notEquals=" + DEFAULT_LIBELLE);

        // Get all the siteList where libelle not equals to UPDATED_LIBELLE
        defaultSiteShouldBeFound("libelle.notEquals=" + UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void getAllSitesByLibelleIsInShouldWork() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        // Get all the siteList where libelle in DEFAULT_LIBELLE or UPDATED_LIBELLE
        defaultSiteShouldBeFound("libelle.in=" + DEFAULT_LIBELLE + "," + UPDATED_LIBELLE);

        // Get all the siteList where libelle equals to UPDATED_LIBELLE
        defaultSiteShouldNotBeFound("libelle.in=" + UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void getAllSitesByLibelleIsNullOrNotNull() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        // Get all the siteList where libelle is not null
        defaultSiteShouldBeFound("libelle.specified=true");

        // Get all the siteList where libelle is null
        defaultSiteShouldNotBeFound("libelle.specified=false");
    }
                @Test
    @Transactional
    public void getAllSitesByLibelleContainsSomething() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        // Get all the siteList where libelle contains DEFAULT_LIBELLE
        defaultSiteShouldBeFound("libelle.contains=" + DEFAULT_LIBELLE);

        // Get all the siteList where libelle contains UPDATED_LIBELLE
        defaultSiteShouldNotBeFound("libelle.contains=" + UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void getAllSitesByLibelleNotContainsSomething() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        // Get all the siteList where libelle does not contain DEFAULT_LIBELLE
        defaultSiteShouldNotBeFound("libelle.doesNotContain=" + DEFAULT_LIBELLE);

        // Get all the siteList where libelle does not contain UPDATED_LIBELLE
        defaultSiteShouldBeFound("libelle.doesNotContain=" + UPDATED_LIBELLE);
    }


    @Test
    @Transactional
    public void getAllSitesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        // Get all the siteList where description equals to DEFAULT_DESCRIPTION
        defaultSiteShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the siteList where description equals to UPDATED_DESCRIPTION
        defaultSiteShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllSitesByDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        // Get all the siteList where description not equals to DEFAULT_DESCRIPTION
        defaultSiteShouldNotBeFound("description.notEquals=" + DEFAULT_DESCRIPTION);

        // Get all the siteList where description not equals to UPDATED_DESCRIPTION
        defaultSiteShouldBeFound("description.notEquals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllSitesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        // Get all the siteList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultSiteShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the siteList where description equals to UPDATED_DESCRIPTION
        defaultSiteShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllSitesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        // Get all the siteList where description is not null
        defaultSiteShouldBeFound("description.specified=true");

        // Get all the siteList where description is null
        defaultSiteShouldNotBeFound("description.specified=false");
    }
                @Test
    @Transactional
    public void getAllSitesByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        // Get all the siteList where description contains DEFAULT_DESCRIPTION
        defaultSiteShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the siteList where description contains UPDATED_DESCRIPTION
        defaultSiteShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllSitesByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        // Get all the siteList where description does not contain DEFAULT_DESCRIPTION
        defaultSiteShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the siteList where description does not contain UPDATED_DESCRIPTION
        defaultSiteShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }


    @Test
    @Transactional
    public void getAllSitesByTenantIsEqualToSomething() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);
        Tenant tenant = TenantResourceIT.createEntity(em);
        em.persist(tenant);
        em.flush();
        site.setTenant(tenant);
        siteRepository.saveAndFlush(site);
        Long tenantId = tenant.getId();

        // Get all the siteList where tenant equals to tenantId
        defaultSiteShouldBeFound("tenantId.equals=" + tenantId);

        // Get all the siteList where tenant equals to tenantId + 1
        defaultSiteShouldNotBeFound("tenantId.equals=" + (tenantId + 1));
    }


    @Test
    @Transactional
    public void getAllSitesByLocationIsEqualToSomething() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);
        Location location = LocationResourceIT.createEntity(em);
        em.persist(location);
        em.flush();
        site.setLocation(location);
        siteRepository.saveAndFlush(site);
        Long locationId = location.getId();

        // Get all the siteList where location equals to locationId
        defaultSiteShouldBeFound("locationId.equals=" + locationId);

        // Get all the siteList where location equals to locationId + 1
        defaultSiteShouldNotBeFound("locationId.equals=" + (locationId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSiteShouldBeFound(String filter) throws Exception {
        restSiteMockMvc.perform(get("/api/sites?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(site.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));

        // Check, that the count call also returns 1
        restSiteMockMvc.perform(get("/api/sites/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSiteShouldNotBeFound(String filter) throws Exception {
        restSiteMockMvc.perform(get("/api/sites?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSiteMockMvc.perform(get("/api/sites/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSite() throws Exception {
        // Get the site
        restSiteMockMvc.perform(get("/api/sites/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSite() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        int databaseSizeBeforeUpdate = siteRepository.findAll().size();

        // Update the site
        Site updatedSite = siteRepository.findById(site.getId()).get();
        // Disconnect from session so that the updates on updatedSite are not directly saved in db
        em.detach(updatedSite);
        updatedSite
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION);
        SiteDTO siteDTO = siteMapper.toDto(updatedSite);

        restSiteMockMvc.perform(put("/api/sites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siteDTO)))
            .andExpect(status().isOk());

        // Validate the Site in the database
        List<Site> siteList = siteRepository.findAll();
        assertThat(siteList).hasSize(databaseSizeBeforeUpdate);
        Site testSite = siteList.get(siteList.size() - 1);
        assertThat(testSite.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testSite.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingSite() throws Exception {
        int databaseSizeBeforeUpdate = siteRepository.findAll().size();

        // Create the Site
        SiteDTO siteDTO = siteMapper.toDto(site);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSiteMockMvc.perform(put("/api/sites")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(siteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Site in the database
        List<Site> siteList = siteRepository.findAll();
        assertThat(siteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSite() throws Exception {
        // Initialize the database
        siteRepository.saveAndFlush(site);

        int databaseSizeBeforeDelete = siteRepository.findAll().size();

        // Delete the site
        restSiteMockMvc.perform(delete("/api/sites/{id}", site.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Site> siteList = siteRepository.findAll();
        assertThat(siteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
