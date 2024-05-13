package com.skyapi.weatherforecast.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Table(name = "locations")
public class Location {
    @Column(length = 12, nullable = false,unique = true)
    @Id
    @NotNull(message = "Location code cannot be left null")
    @Length(min = 3, max = 12, message = "Location code must have 3-12 characters")
    private String code;
    @Column(length = 128,nullable = false)
    @JsonProperty("city_name")
    @NotNull(message = "City name cannot be left null")
    private String cityName;
    @Column(length = 128)
    @JsonProperty("region_name")
    @NotNull(message = "Region name cannot be left null")
    private String regionName;
    @Column(length = 64,nullable = false)
    @JsonProperty("country_name")
    @NotNull(message = "Country name cannot be left null")
    @Length(min = 3, max = 64, message = "Country name must have 3-64 characters")
    private String countryName;
    @Column(length = 2, nullable = false)
    @JsonProperty("country_code")
    @NotNull(message = "Country code cannot be left null")
    @Length(min = 2, max = 2, message = "Country Code must have 2 characters")
    private String countryCode;

    private boolean enabled;
    @OneToOne(mappedBy = "location", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private RealTimeWeather realTimeWeather;

    public Location() {
    }

    public Location(String cityName, String regionName, String countryName, String countryCode) {
        this.cityName = cityName;
        this.regionName = regionName;
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public RealTimeWeather getRealTimeWeather() {
        return realTimeWeather;
    }

    public void setRealTimeWeather(RealTimeWeather realTimeWeather) {
        this.realTimeWeather = realTimeWeather;
    }

    @JsonIgnore
    private boolean trashed;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isTrashed() {
        return trashed;
    }

    public void setTrashed(boolean trashed) {
        this.trashed = trashed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;
        return Objects.equals(code, location.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Location{" +
                "code='" + code + '\'' +
                ", cityName='" + cityName + '\'' +
                ", regionName='" + regionName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", enabled=" + enabled +
                ", trashed=" + trashed +
                '}';
    }
}
