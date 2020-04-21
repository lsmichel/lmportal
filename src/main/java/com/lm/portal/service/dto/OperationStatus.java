package com.lm.portal.service.dto;

public class OperationStatus {
    public boolean HasError ;
    public String fonctionalError ;
    public String systemError ;

    public boolean isHasError() {
        return HasError;
    }

    public void setHasError(boolean hasError) {
        HasError = hasError;
    }

    public String getFonctionalError() {
        return fonctionalError;
    }

    public void setFonctionalError(String fonctionalError) {
        this.fonctionalError = fonctionalError;
    }

    public String getSystemError() {
        return systemError;
    }

    public void setSystemError(String systemError) {
        this.systemError = systemError;
    }
}
