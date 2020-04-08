package com.lm.portal.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TenantSuperAdminMapperTest {

    private TenantSuperAdminMapper tenantSuperAdminMapper;

    @BeforeEach
    public void setUp() {
        tenantSuperAdminMapper = new TenantSuperAdminMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tenantSuperAdminMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tenantSuperAdminMapper.fromId(null)).isNull();
    }
}
