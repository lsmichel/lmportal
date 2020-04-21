package com.lm.portal.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TenantUserMapperTest {

    private TenantUserMapper tenantUserMapper;

    @BeforeEach
    public void setUp() {
        tenantUserMapper = new TenantUserMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tenantUserMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tenantUserMapper.fromId(null)).isNull();
    }
}
