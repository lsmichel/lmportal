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

import com.lm.portal.domain.TenantSuperAdmin;
import com.lm.portal.domain.*; // for static metamodels
import com.lm.portal.repository.TenantSuperAdminRepository;
import com.lm.portal.service.dto.TenantSuperAdminCriteria;
import com.lm.portal.service.dto.TenantSuperAdminDTO;
import com.lm.portal.service.mapper.TenantSuperAdminMapper;

/**
 * Service for executing complex queries for {@link TenantSuperAdmin} entities in the database.
 * The main input is a {@link TenantSuperAdminCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TenantSuperAdminDTO} or a {@link Page} of {@link TenantSuperAdminDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TenantSuperAdminQueryService extends QueryService<TenantSuperAdmin> {

    private final Logger log = LoggerFactory.getLogger(TenantSuperAdminQueryService.class);

    private final TenantSuperAdminRepository tenantSuperAdminRepository;

    private final TenantSuperAdminMapper tenantSuperAdminMapper;

    public TenantSuperAdminQueryService(TenantSuperAdminRepository tenantSuperAdminRepository, TenantSuperAdminMapper tenantSuperAdminMapper) {
        this.tenantSuperAdminRepository = tenantSuperAdminRepository;
        this.tenantSuperAdminMapper = tenantSuperAdminMapper;
    }

    /**
     * Return a {@link List} of {@link TenantSuperAdminDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TenantSuperAdminDTO> findByCriteria(TenantSuperAdminCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TenantSuperAdmin> specification = createSpecification(criteria);
        return tenantSuperAdminMapper.toDto(tenantSuperAdminRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TenantSuperAdminDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TenantSuperAdminDTO> findByCriteria(TenantSuperAdminCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TenantSuperAdmin> specification = createSpecification(criteria);
        return tenantSuperAdminRepository.findAll(specification, page)
            .map(tenantSuperAdminMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TenantSuperAdminCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TenantSuperAdmin> specification = createSpecification(criteria);
        return tenantSuperAdminRepository.count(specification);
    }

    /**
     * Function to convert {@link TenantSuperAdminCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TenantSuperAdmin> createSpecification(TenantSuperAdminCriteria criteria) {
        Specification<TenantSuperAdmin> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TenantSuperAdmin_.id));
            }
            if (criteria.getFirstNameTenantSuperAdmin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstNameTenantSuperAdmin(), TenantSuperAdmin_.firstNameTenantSuperAdmin));
            }
            if (criteria.getLastNameTenantSuperAdmin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastNameTenantSuperAdmin(), TenantSuperAdmin_.lastNameTenantSuperAdmin));
            }
            if (criteria.getUserNameTenantSuperAdmin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserNameTenantSuperAdmin(), TenantSuperAdmin_.userNameTenantSuperAdmin));
            }
            if (criteria.getEmailTenantSuperAdmin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmailTenantSuperAdmin(), TenantSuperAdmin_.emailTenantSuperAdmin));
            }
            if (criteria.getPasswordTenantSuperAdmin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPasswordTenantSuperAdmin(), TenantSuperAdmin_.passwordTenantSuperAdmin));
            }
            if (criteria.getTenantId() != null) {
                specification = specification.and(buildSpecification(criteria.getTenantId(),
                    root -> root.join(TenantSuperAdmin_.tenant, JoinType.LEFT).get(Tenant_.id)));
            }
        }
        return specification;
    }
}
