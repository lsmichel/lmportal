package com.lm.portal.repository;

import com.lm.portal.domain.TenantSuperAdmin;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TenantSuperAdmin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenantSuperAdminRepository extends JpaRepository<TenantSuperAdmin, Long>, JpaSpecificationExecutor<TenantSuperAdmin> {
}
