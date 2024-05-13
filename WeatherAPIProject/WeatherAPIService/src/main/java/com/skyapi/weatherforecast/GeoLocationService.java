package com.skyapi.weatherforecast;

import com.ip2location.IP2Location;
import com.ip2location.IPResult;
import com.skyapi.weatherforecast.common.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GeoLocationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeoLocationService.class);
    private String DBPath = "ip2locdb/IP2LOCATION-LITE-DB3.BIN";
    private IP2Location ipLocator = new IP2Location();
    public GeoLocationService() {
        try {
            ipLocator.Open(DBPath);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
        }
    }

    public Location getLocation(String ipAddress) throws GeolocationException {
        try {
            IPResult ipResult = ipLocator.IPQuery(ipAddress);
            if (!"OK".equals(ipResult.getStatus())){
                throw new GeolocationException("Geolocation failed with status: " + ipResult.getStatus());
            }
            return new Location(ipResult.getCity(),ipResult.getRegion(),ipResult.getCountryLong(),ipResult.getCountryShort());
        } catch (IOException e) {
            throw new GeolocationException("Error querying IP database",e);
        }
    }

}
