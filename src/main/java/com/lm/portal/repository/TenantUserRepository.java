package com.lm.portal.repository;

import com.lm.portal.domain.TenantUser;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TenantUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenantUserRepository extends JpaRepository<TenantUser, Long> {
}
