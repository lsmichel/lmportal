package com.lm.portal.service.mapper;


import com.lm.portal.domain.*;
import com.lm.portal.service.dto.SiteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Site} and its DTO {@link SiteDTO}.
 */
@Mapper(componentModel = "spring", uses = {TenantMapper.class, LocationMapper.class})
public interface SiteMapper extends EntityMapper<SiteDTO, Site> {

    @Mapping(source = "tenant.id", target = "tenantId")
    @Mapping(source = "location.id", target = "locationId")
    SiteDTO toDto(Site site);

    @Mapping(source = "tenantId", target = "tenant")
    @Mapping(source = "locationId", target = "location")
    Site toEntity(SiteDTO siteDTO);

    default Site fromId(Long id) {
        if (id == null) {
            return null;
        }
        Site site = new Site();
        site.setId(id);
        return site;
    }
}
