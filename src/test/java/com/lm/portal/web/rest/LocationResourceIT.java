package com.lm.portal.web.rest;

import com.lm.portal.LmPortal2App;
import com.lm.portal.domain.Location;
import com.lm.portal.domain.Site;
import com.lm.portal.repository.LocationRepository;
import com.lm.portal.service.LocationService;
import com.lm.portal.service.dto.LocationDTO;
import com.lm.portal.service.mapper.LocationMapper;
import com.lm.portal.service.dto.LocationCriteria;
import com.lm.portal.service.LocationQueryService;

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
 * Integration tests for the {@link LocationResource} REST controller.
 */
@SpringBootTest(classes = LmPortal2App.class)

@AutoConfigureMockMvc
@WithMockUser
public class LocationResourceIT {

    private static final String DEFAULT_CONNTRY = "AAAAAAAAAA";
    private static final String UPDATED_CONNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ZIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADRESS = "BBBBBBBBBB";

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationQueryService locationQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLocationMockMvc;

    private Location location;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Location createEntity(EntityManager em) {
        Location location = new Location()
            .conntry(DEFAULT_CONNTRY)
            .city(DEFAULT_CITY)
            .zipCode(DEFAULT_ZIP_CODE)
            .adress(DEFAULT_ADRESS);
        return location;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Location createUpdatedEntity(EntityManager em) {
        Location location = new Location()
            .conntry(UPDATED_CONNTRY)
            .city(UPDATED_CITY)
            .zipCode(UPDATED_ZIP_CODE)
            .adress(UPDATED_ADRESS);
        return location;
    }

    @BeforeEach
    public void initTest() {
        location = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocation() throws Exception {
        int databaseSizeBeforeCreate = locationRepository.findAll().size();

        // Create the Location
        LocationDTO locationDTO = locationMapper.toDto(location);
        restLocationMockMvc.perform(post("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isCreated());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeCreate + 1);
        Location testLocation = locationList.get(locationList.size() - 1);
        assertThat(testLocation.getConntry()).isEqualTo(DEFAULT_CONNTRY);
        assertThat(testLocation.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testLocation.getZipCode()).isEqualTo(DEFAULT_ZIP_CODE);
        assertThat(testLocation.getAdress()).isEqualTo(DEFAULT_ADRESS);
    }

    @Test
    @Transactional
    public void createLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = locationRepository.findAll().size();

        // Create the Location with an existing ID
        location.setId(1L);
        LocationDTO locationDTO = locationMapper.toDto(location);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocationMockMvc.perform(post("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLocations() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList
        restLocationMockMvc.perform(get("/api/locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(location.getId().intValue())))
            .andExpect(jsonPath("$.[*].conntry").value(hasItem(DEFAULT_CONNTRY)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].adress").value(hasItem(DEFAULT_ADRESS)));
    }
    
    @Test
    @Transactional
    public void getLocation() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get the location
        restLocationMockMvc.perform(get("/api/locations/{id}", location.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(location.getId().intValue()))
            .andExpect(jsonPath("$.conntry").value(DEFAULT_CONNTRY))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.zipCode").value(DEFAULT_ZIP_CODE))
            .andExpect(jsonPath("$.adress").value(DEFAULT_ADRESS));
    }


    @Test
    @Transactional
    public void getLocationsByIdFiltering() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        Long id = location.getId();

        defaultLocationShouldBeFound("id.equals=" + id);
        defaultLocationShouldNotBeFound("id.notEquals=" + id);

        defaultLocationShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultLocationShouldNotBeFound("id.greaterThan=" + id);

        defaultLocationShouldBeFound("id.lessThanOrEqual=" + id);
        defaultLocationShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllLocationsByConntryIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where conntry equals to DEFAULT_CONNTRY
        defaultLocationShouldBeFound("conntry.equals=" + DEFAULT_CONNTRY);

        // Get all the locationList where conntry equals to UPDATED_CONNTRY
        defaultLocationShouldNotBeFound("conntry.equals=" + UPDATED_CONNTRY);
    }

    @Test
    @Transactional
    public void getAllLocationsByConntryIsNotEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where conntry not equals to DEFAULT_CONNTRY
        defaultLocationShouldNotBeFound("conntry.notEquals=" + DEFAULT_CONNTRY);

        // Get all the locationList where conntry not equals to UPDATED_CONNTRY
        defaultLocationShouldBeFound("conntry.notEquals=" + UPDATED_CONNTRY);
    }

    @Test
    @Transactional
    public void getAllLocationsByConntryIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where conntry in DEFAULT_CONNTRY or UPDATED_CONNTRY
        defaultLocationShouldBeFound("conntry.in=" + DEFAULT_CONNTRY + "," + UPDATED_CONNTRY);

        // Get all the locationList where conntry equals to UPDATED_CONNTRY
        defaultLocationShouldNotBeFound("conntry.in=" + UPDATED_CONNTRY);
    }

    @Test
    @Transactional
    public void getAllLocationsByConntryIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where conntry is not null
        defaultLocationShouldBeFound("conntry.specified=true");

        // Get all the locationList where conntry is null
        defaultLocationShouldNotBeFound("conntry.specified=false");
    }
                @Test
    @Transactional
    public void getAllLocationsByConntryContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where conntry contains DEFAULT_CONNTRY
        defaultLocationShouldBeFound("conntry.contains=" + DEFAULT_CONNTRY);

        // Get all the locationList where conntry contains UPDATED_CONNTRY
        defaultLocationShouldNotBeFound("conntry.contains=" + UPDATED_CONNTRY);
    }

    @Test
    @Transactional
    public void getAllLocationsByConntryNotContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where conntry does not contain DEFAULT_CONNTRY
        defaultLocationShouldNotBeFound("conntry.doesNotContain=" + DEFAULT_CONNTRY);

        // Get all the locationList where conntry does not contain UPDATED_CONNTRY
        defaultLocationShouldBeFound("conntry.doesNotContain=" + UPDATED_CONNTRY);
    }


    @Test
    @Transactional
    public void getAllLocationsByCityIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where city equals to DEFAULT_CITY
        defaultLocationShouldBeFound("city.equals=" + DEFAULT_CITY);

        // Get all the locationList where city equals to UPDATED_CITY
        defaultLocationShouldNotBeFound("city.equals=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllLocationsByCityIsNotEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where city not equals to DEFAULT_CITY
        defaultLocationShouldNotBeFound("city.notEquals=" + DEFAULT_CITY);

        // Get all the locationList where city not equals to UPDATED_CITY
        defaultLocationShouldBeFound("city.notEquals=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllLocationsByCityIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where city in DEFAULT_CITY or UPDATED_CITY
        defaultLocationShouldBeFound("city.in=" + DEFAULT_CITY + "," + UPDATED_CITY);

        // Get all the locationList where city equals to UPDATED_CITY
        defaultLocationShouldNotBeFound("city.in=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllLocationsByCityIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where city is not null
        defaultLocationShouldBeFound("city.specified=true");

        // Get all the locationList where city is null
        defaultLocationShouldNotBeFound("city.specified=false");
    }
                @Test
    @Transactional
    public void getAllLocationsByCityContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where city contains DEFAULT_CITY
        defaultLocationShouldBeFound("city.contains=" + DEFAULT_CITY);

        // Get all the locationList where city contains UPDATED_CITY
        defaultLocationShouldNotBeFound("city.contains=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    public void getAllLocationsByCityNotContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where city does not contain DEFAULT_CITY
        defaultLocationShouldNotBeFound("city.doesNotContain=" + DEFAULT_CITY);

        // Get all the locationList where city does not contain UPDATED_CITY
        defaultLocationShouldBeFound("city.doesNotContain=" + UPDATED_CITY);
    }


    @Test
    @Transactional
    public void getAllLocationsByZipCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where zipCode equals to DEFAULT_ZIP_CODE
        defaultLocationShouldBeFound("zipCode.equals=" + DEFAULT_ZIP_CODE);

        // Get all the locationList where zipCode equals to UPDATED_ZIP_CODE
        defaultLocationShouldNotBeFound("zipCode.equals=" + UPDATED_ZIP_CODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByZipCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where zipCode not equals to DEFAULT_ZIP_CODE
        defaultLocationShouldNotBeFound("zipCode.notEquals=" + DEFAULT_ZIP_CODE);

        // Get all the locationList where zipCode not equals to UPDATED_ZIP_CODE
        defaultLocationShouldBeFound("zipCode.notEquals=" + UPDATED_ZIP_CODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByZipCodeIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where zipCode in DEFAULT_ZIP_CODE or UPDATED_ZIP_CODE
        defaultLocationShouldBeFound("zipCode.in=" + DEFAULT_ZIP_CODE + "," + UPDATED_ZIP_CODE);

        // Get all the locationList where zipCode equals to UPDATED_ZIP_CODE
        defaultLocationShouldNotBeFound("zipCode.in=" + UPDATED_ZIP_CODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByZipCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where zipCode is not null
        defaultLocationShouldBeFound("zipCode.specified=true");

        // Get all the locationList where zipCode is null
        defaultLocationShouldNotBeFound("zipCode.specified=false");
    }
                @Test
    @Transactional
    public void getAllLocationsByZipCodeContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where zipCode contains DEFAULT_ZIP_CODE
        defaultLocationShouldBeFound("zipCode.contains=" + DEFAULT_ZIP_CODE);

        // Get all the locationList where zipCode contains UPDATED_ZIP_CODE
        defaultLocationShouldNotBeFound("zipCode.contains=" + UPDATED_ZIP_CODE);
    }

    @Test
    @Transactional
    public void getAllLocationsByZipCodeNotContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where zipCode does not contain DEFAULT_ZIP_CODE
        defaultLocationShouldNotBeFound("zipCode.doesNotContain=" + DEFAULT_ZIP_CODE);

        // Get all the locationList where zipCode does not contain UPDATED_ZIP_CODE
        defaultLocationShouldBeFound("zipCode.doesNotContain=" + UPDATED_ZIP_CODE);
    }


    @Test
    @Transactional
    public void getAllLocationsByAdressIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where adress equals to DEFAULT_ADRESS
        defaultLocationShouldBeFound("adress.equals=" + DEFAULT_ADRESS);

        // Get all the locationList where adress equals to UPDATED_ADRESS
        defaultLocationShouldNotBeFound("adress.equals=" + UPDATED_ADRESS);
    }

    @Test
    @Transactional
    public void getAllLocationsByAdressIsNotEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where adress not equals to DEFAULT_ADRESS
        defaultLocationShouldNotBeFound("adress.notEquals=" + DEFAULT_ADRESS);

        // Get all the locationList where adress not equals to UPDATED_ADRESS
        defaultLocationShouldBeFound("adress.notEquals=" + UPDATED_ADRESS);
    }

    @Test
    @Transactional
    public void getAllLocationsByAdressIsInShouldWork() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where adress in DEFAULT_ADRESS or UPDATED_ADRESS
        defaultLocationShouldBeFound("adress.in=" + DEFAULT_ADRESS + "," + UPDATED_ADRESS);

        // Get all the locationList where adress equals to UPDATED_ADRESS
        defaultLocationShouldNotBeFound("adress.in=" + UPDATED_ADRESS);
    }

    @Test
    @Transactional
    public void getAllLocationsByAdressIsNullOrNotNull() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where adress is not null
        defaultLocationShouldBeFound("adress.specified=true");

        // Get all the locationList where adress is null
        defaultLocationShouldNotBeFound("adress.specified=false");
    }
                @Test
    @Transactional
    public void getAllLocationsByAdressContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where adress contains DEFAULT_ADRESS
        defaultLocationShouldBeFound("adress.contains=" + DEFAULT_ADRESS);

        // Get all the locationList where adress contains UPDATED_ADRESS
        defaultLocationShouldNotBeFound("adress.contains=" + UPDATED_ADRESS);
    }

    @Test
    @Transactional
    public void getAllLocationsByAdressNotContainsSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        // Get all the locationList where adress does not contain DEFAULT_ADRESS
        defaultLocationShouldNotBeFound("adress.doesNotContain=" + DEFAULT_ADRESS);

        // Get all the locationList where adress does not contain UPDATED_ADRESS
        defaultLocationShouldBeFound("adress.doesNotContain=" + UPDATED_ADRESS);
    }


    @Test
    @Transactional
    public void getAllLocationsBySiteIsEqualToSomething() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);
        Site site = SiteResourceIT.createEntity(em);
        em.persist(site);
        em.flush();
        location.addSite(site);
        locationRepository.saveAndFlush(location);
        Long siteId = site.getId();

        // Get all the locationList where site equals to siteId
        defaultLocationShouldBeFound("siteId.equals=" + siteId);

        // Get all the locationList where site equals to siteId + 1
        defaultLocationShouldNotBeFound("siteId.equals=" + (siteId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultLocationShouldBeFound(String filter) throws Exception {
        restLocationMockMvc.perform(get("/api/locations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(location.getId().intValue())))
            .andExpect(jsonPath("$.[*].conntry").value(hasItem(DEFAULT_CONNTRY)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE)))
            .andExpect(jsonPath("$.[*].adress").value(hasItem(DEFAULT_ADRESS)));

        // Check, that the count call also returns 1
        restLocationMockMvc.perform(get("/api/locations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultLocationShouldNotBeFound(String filter) throws Exception {
        restLocationMockMvc.perform(get("/api/locations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restLocationMockMvc.perform(get("/api/locations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingLocation() throws Exception {
        // Get the location
        restLocationMockMvc.perform(get("/api/locations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocation() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        int databaseSizeBeforeUpdate = locationRepository.findAll().size();

        // Update the location
        Location updatedLocation = locationRepository.findById(location.getId()).get();
        // Disconnect from session so that the updates on updatedLocation are not directly saved in db
        em.detach(updatedLocation);
        updatedLocation
            .conntry(UPDATED_CONNTRY)
            .city(UPDATED_CITY)
            .zipCode(UPDATED_ZIP_CODE)
            .adress(UPDATED_ADRESS);
        LocationDTO locationDTO = locationMapper.toDto(updatedLocation);

        restLocationMockMvc.perform(put("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isOk());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeUpdate);
        Location testLocation = locationList.get(locationList.size() - 1);
        assertThat(testLocation.getConntry()).isEqualTo(UPDATED_CONNTRY);
        assertThat(testLocation.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testLocation.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testLocation.getAdress()).isEqualTo(UPDATED_ADRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingLocation() throws Exception {
        int databaseSizeBeforeUpdate = locationRepository.findAll().size();

        // Create the Location
        LocationDTO locationDTO = locationMapper.toDto(location);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocationMockMvc.perform(put("/api/locations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Location in the database
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLocation() throws Exception {
        // Initialize the database
        locationRepository.saveAndFlush(location);

        int databaseSizeBeforeDelete = locationRepository.findAll().size();

        // Delete the location
        restLocationMockMvc.perform(delete("/api/locations/{id}", location.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
