package com.lm.portal.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.lm.portal.domain.Location} entity. This class is used
 * in {@link com.lm.portal.web.rest.LocationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /locations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LocationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter conntry;

    private StringFilter city;

    private StringFilter zipCode;

    private StringFilter adress;

    private LongFilter siteId;

    public LocationCriteria() {
    }

    public LocationCriteria(LocationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.conntry = other.conntry == null ? null : other.conntry.copy();
        this.city = other.city == null ? null : other.city.copy();
        this.zipCode = other.zipCode == null ? null : other.zipCode.copy();
        this.adress = other.adress == null ? null : other.adress.copy();
        this.siteId = other.siteId == null ? null : other.siteId.copy();
    }

    @Override
    public LocationCriteria copy() {
        return new LocationCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getConntry() {
        return conntry;
    }

    public void setConntry(StringFilter conntry) {
        this.conntry = conntry;
    }

    public StringFilter getCity() {
        return city;
    }

    public void setCity(StringFilter city) {
        this.city = city;
    }

    public StringFilter getZipCode() {
        return zipCode;
    }

    public void setZipCode(StringFilter zipCode) {
        this.zipCode = zipCode;
    }

    public StringFilter getAdress() {
        return adress;
    }

    public void setAdress(StringFilter adress) {
        this.adress = adress;
    }

    public LongFilter getSiteId() {
        return siteId;
    }

    public void setSiteId(LongFilter siteId) {
        this.siteId = siteId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LocationCriteria that = (LocationCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(conntry, that.conntry) &&
            Objects.equals(city, that.city) &&
            Objects.equals(zipCode, that.zipCode) &&
            Objects.equals(adress, that.adress) &&
            Objects.equals(siteId, that.siteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        conntry,
        city,
        zipCode,
        adress,
        siteId
        );
    }

    @Override
    public String toString() {
        return "LocationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (conntry != null ? "conntry=" + conntry + ", " : "") +
                (city != null ? "city=" + city + ", " : "") +
                (zipCode != null ? "zipCode=" + zipCode + ", " : "") +
                (adress != null ? "adress=" + adress + ", " : "") +
                (siteId != null ? "siteId=" + siteId + ", " : "") +
            "}";
    }

}
