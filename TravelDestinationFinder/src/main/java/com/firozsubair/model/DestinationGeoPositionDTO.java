package com.firozsubair.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "geo_position")
public class DestinationGeoPositionDTO {

    private String latitude;
    private String longitude;
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    @Override
    public String toString() {
        return "DestinationGeoPositionDTO [latitude=" + latitude + ", longitude=" + longitude + "]";
    }
    
}