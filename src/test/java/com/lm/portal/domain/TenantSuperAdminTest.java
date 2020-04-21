package com.lm.portal.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lm.portal.web.rest.TestUtil;

public class TenantSuperAdminTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenantSuperAdmin.class);
        TenantSuperAdmin tenantSuperAdmin1 = new TenantSuperAdmin();
        tenantSuperAdmin1.setId(1L);
        TenantSuperAdmin tenantSuperAdmin2 = new TenantSuperAdmin();
        tenantSuperAdmin2.setId(tenantSuperAdmin1.getId());
        assertThat(tenantSuperAdmin1).isEqualTo(tenantSuperAdmin2);
        tenantSuperAdmin2.setId(2L);
        assertThat(tenantSuperAdmin1).isNotEqualTo(tenantSuperAdmin2);
        tenantSuperAdmin1.setId(null);
        assertThat(tenantSuperAdmin1).isNotEqualTo(tenantSuperAdmin2);
    }
}
