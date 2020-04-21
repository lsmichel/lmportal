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

import com.lm.portal.domain.TenantUser;
import com.lm.portal.domain.*; // for static metamodels
import com.lm.portal.repository.TenantUserRepository;
import com.lm.portal.service.dto.TenantUserCriteria;
import com.lm.portal.service.dto.TenantUserDTO;
import com.lm.portal.service.mapper.TenantUserMapper;

/**
 * Service for executing complex queries for {@link TenantUser} entities in the database.
 * The main input is a {@link TenantUserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TenantUserDTO} or a {@link Page} of {@link TenantUserDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TenantUserQueryService extends QueryService<TenantUser> {

    private final Logger log = LoggerFactory.getLogger(TenantUserQueryService.class);

    private final TenantUserRepository tenantUserRepository;

    private final TenantUserMapper tenantUserMapper;

    public TenantUserQueryService(TenantUserRepository tenantUserRepository, TenantUserMapper tenantUserMapper) {
        this.tenantUserRepository = tenantUserRepository;
        this.tenantUserMapper = tenantUserMapper;
    }

    /**
     * Return a {@link List} of {@link TenantUserDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TenantUserDTO> findByCriteria(TenantUserCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TenantUser> specification = createSpecification(criteria);
        return tenantUserMapper.toDto(tenantUserRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TenantUserDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TenantUserDTO> findByCriteria(TenantUserCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TenantUser> specification = createSpecification(criteria);
        return tenantUserRepository.findAll(specification, page)
            .map(tenantUserMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TenantUserCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TenantUser> specification = createSpecification(criteria);
        return tenantUserRepository.count(specification);
    }

    /**
     * Function to convert {@link TenantUserCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TenantUser> createSpecification(TenantUserCriteria criteria) {
        Specification<TenantUser> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TenantUser_.id));
            }
            if (criteria.getFirstNameTenantUser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstNameTenantUser(), TenantUser_.firstNameTenantUser));
            }
            if (criteria.getLastNameTenantUser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastNameTenantUser(), TenantUser_.lastNameTenantUser));
            }
            if (criteria.getUserNameTenantUser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserNameTenantUser(), TenantUser_.userNameTenantUser));
            }
            if (criteria.getEmailTenantUser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmailTenantUser(), TenantUser_.emailTenantUser));
            }
            if (criteria.getPasswordTenantUser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPasswordTenantUser(), TenantUser_.passwordTenantUser));
            }
            if (criteria.getTenantId() != null) {
                specification = specification.and(buildSpecification(criteria.getTenantId(),
                    root -> root.join(TenantUser_.tenant, JoinType.LEFT).get(Tenant_.id)));
            }
        }
        return specification;
    }
}
