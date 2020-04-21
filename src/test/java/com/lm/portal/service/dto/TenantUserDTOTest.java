package com.lm.portal.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lm.portal.web.rest.TestUtil;

public class TenantUserDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenantUserDTO.class);
        TenantUserDTO tenantUserDTO1 = new TenantUserDTO();
        tenantUserDTO1.setId(1L);
        TenantUserDTO tenantUserDTO2 = new TenantUserDTO();
        assertThat(tenantUserDTO1).isNotEqualTo(tenantUserDTO2);
        tenantUserDTO2.setId(tenantUserDTO1.getId());
        assertThat(tenantUserDTO1).isEqualTo(tenantUserDTO2);
        tenantUserDTO2.setId(2L);
        assertThat(tenantUserDTO1).isNotEqualTo(tenantUserDTO2);
        tenantUserDTO1.setId(null);
        assertThat(tenantUserDTO1).isNotEqualTo(tenantUserDTO2);
    }
}
