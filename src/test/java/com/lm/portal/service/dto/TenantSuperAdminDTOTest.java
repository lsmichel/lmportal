package com.lm.portal.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lm.portal.web.rest.TestUtil;

public class TenantSuperAdminDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenantSuperAdminDTO.class);
        TenantSuperAdminDTO tenantSuperAdminDTO1 = new TenantSuperAdminDTO();
        tenantSuperAdminDTO1.setId(1L);
        TenantSuperAdminDTO tenantSuperAdminDTO2 = new TenantSuperAdminDTO();
        assertThat(tenantSuperAdminDTO1).isNotEqualTo(tenantSuperAdminDTO2);
        tenantSuperAdminDTO2.setId(tenantSuperAdminDTO1.getId());
        assertThat(tenantSuperAdminDTO1).isEqualTo(tenantSuperAdminDTO2);
        tenantSuperAdminDTO2.setId(2L);
        assertThat(tenantSuperAdminDTO1).isNotEqualTo(tenantSuperAdminDTO2);
        tenantSuperAdminDTO1.setId(null);
        assertThat(tenantSuperAdminDTO1).isNotEqualTo(tenantSuperAdminDTO2);
    }
}
