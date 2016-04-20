package com.firozsubair.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DestinationDTO {

    private String _id;
    private String key;
    private String name;
    private String fullName;
    private String iata_airport_code;
    private String type;
    private String country;
    private DestinationGeoPositionDTO geo_position;
    private String location_id;
    private String inEurope;
    private String countryCode;
    private String coreCountry;
    private String distance;
    
    private String latitude;
    private String longitude;
    public String getLatitude() {
        return  getGeo_position().getLatitude();
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return getGeo_position().getLongitude();
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
   
    
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getIata_airport_code() {
        return iata_airport_code;
    }
    public void setIata_airport_code(String iata_airport_code) {
        this.iata_airport_code = iata_airport_code;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getLocation_id() {
        return location_id;
    }
    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }
    public String getInEurope() {
        return inEurope;
    }
    public void setInEurope(String inEurope) {
        this.inEurope = inEurope;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getCoreCountry() {
        return coreCountry;
    }
    public void setCoreCountry(String coreCountry) {
        this.coreCountry = coreCountry;
    }
    public String getDistance() {
        return distance;
    }
    public void setDistance(String distance) {
        this.distance = distance;
    }
    public DestinationGeoPositionDTO getGeo_position() {
        return geo_position;
    }
    public void setGeo_position(DestinationGeoPositionDTO geo_position) {
        this.geo_position = geo_position;
    }
    @Override
    public String toString() {
        return "DestinationDTO [_id=" + _id + ", key=" + key + ", name=" + name + ", fullName=" + fullName + ", iata_airport_code=" + iata_airport_code + ", type=" + type + ", country=" + country + ", geo_position=" + geo_position + ", location_id="
                + location_id + ", inEurope=" + inEurope + ", countryCode=" + countryCode + ", coreCountry=" + coreCountry + ", distance=" + distance + ", latitude=" + latitude + ", longitude=" + longitude + "]";
    }
}
