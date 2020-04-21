package com.lm.portal.service.utilities;

import java.util.UUID;
public class GenerateTenantId {
    public GenerateTenantId(){

    }
    public String GenerateId () {
        UUID id = UUID.randomUUID();
        return id.toString() ;
    }

    private static class GenerateTenantIdHolder
    {
        /** Instance unique non préinitialisée */
        private final static GenerateTenantId instance = new GenerateTenantId();
    }

    public static GenerateTenantId getInstance(){
        return GenerateTenantIdHolder.instance ;
    }
}
