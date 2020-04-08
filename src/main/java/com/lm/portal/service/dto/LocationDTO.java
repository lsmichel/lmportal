package com.lm.portal.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.lm.portal.domain.Location} entity.
 */
public class LocationDTO implements Serializable {
    
    private Long id;

    private String conntry;

    private String city;

    private String zipCode;

    private String adress;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConntry() {
        return conntry;
    }

    public void setConntry(String conntry) {
        this.conntry = conntry;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LocationDTO locationDTO = (LocationDTO) o;
        if (locationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), locationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LocationDTO{" +
            "id=" + getId() +
            ", conntry='" + getConntry() + "'" +
            ", city='" + getCity() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", adress='" + getAdress() + "'" +
            "}";
    }
}
