package com.lm.portal.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.lm.portal.domain.Tenant;
import com.lm.portal.domain.*; // for static metamodels
import com.lm.portal.repository.TenantRepository;
import com.lm.portal.service.dto.TenantCriteria;
import com.lm.portal.service.dto.TenantDTO;
import com.lm.portal.service.mapper.TenantMapper;

/**
 * Service for executing complex queries for {@link Tenant} entities in the database.
 * The main input is a {@link TenantCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TenantDTO} or a {@link Page} of {@link TenantDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TenantQueryService extends QueryService<Tenant> {

    private final Logger log = LoggerFactory.getLogger(TenantQueryService.class);

    private final TenantRepository tenantRepository;

    private final TenantMapper tenantMapper;

    public TenantQueryService(TenantRepository tenantRepository, TenantMapper tenantMapper) {
        this.tenantRepository = tenantRepository;
        this.tenantMapper = tenantMapper;
    }

    /**
     * Return a {@link List} of {@link TenantDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TenantDTO> findByCriteria(TenantCriteria criteria) {

        log.debug("find by criteria : {}", criteria);
        final Specification<Tenant> specification = createSpecification(criteria);
        return tenantMapper.toDto(tenantRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TenantDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TenantDTO> findByCriteria(TenantCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Tenant> specification = createSpecification(criteria);
        return tenantRepository.findAll(specification, page)
            .map(tenantMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TenantCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Tenant> specification = createSpecification(criteria);
        return tenantRepository.count(specification);
    }

    /**
     * Function to convert {@link TenantCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Tenant> createSpecification(TenantCriteria criteria) {
        Specification<Tenant> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Tenant_.id));
            }
            if (criteria.getIdTenant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdTenant(), Tenant_.idTenant));
            }
            if (criteria.getLogoTenat() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLogoTenat(), Tenant_.logoTenat));
            }
            if (criteria.getSpaceNameTenant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSpaceNameTenant(), Tenant_.spaceNameTenant));
            }
            if (criteria.getPhoneNumberTenant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoneNumberTenant(), Tenant_.phoneNumberTenant));
            }
            if (criteria.getWebSiteTenant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWebSiteTenant(), Tenant_.webSiteTenant));
            }
            if (criteria.getEmailTenant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmailTenant(), Tenant_.emailTenant));
            }
            if (criteria.getFacebookTenant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFacebookTenant(), Tenant_.facebookTenant));
            }
            if (criteria.getTwitterTenant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTwitterTenant(), Tenant_.twitterTenant));
            }
            if (criteria.getInstagramTenant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInstagramTenant(), Tenant_.instagramTenant));
            }
            if (criteria.getYoutubeTenant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getYoutubeTenant(), Tenant_.youtubeTenant));
            }
            if (criteria.getTenantSuperAdminId() != null) {
                specification = specification.and(buildSpecification(criteria.getTenantSuperAdminId(),
                    root -> root.join(Tenant_.tenantSuperAdmins, JoinType.LEFT).get(TenantSuperAdmin_.id)));
            }
            if (criteria.getTenantUserId() != null) {
                specification = specification.and(buildSpecification(criteria.getTenantUserId(),
                    root -> root.join(Tenant_.tenantUsers, JoinType.LEFT).get(TenantUser_.id)));
            }
            if (criteria.getSiteId() != null) {
                specification = specification.and(buildSpecification(criteria.getSiteId(),
                    root -> root.join(Tenant_.sites, JoinType.LEFT).get(Site_.id)));
            }
        }
        return specification;
    }
}
