package com.skyapi.weatherforecast;

import com.ip2location.IP2Location;
import com.ip2location.IPResult;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


public class IP2LocationTest {
    private String DBPath = "ip2locdb/IP2LOCATION-LITE-DB3.BIN";
    @Test
    public void testInvalidIP() throws IOException {
        IP2Location ipLocator = new IP2Location();
        ipLocator.Open(DBPath);
        String ipAddress =  "abc";
        IPResult ipResult = ipLocator.IPQuery(ipAddress);
        assertThat(ipResult.getStatus()).isEqualTo("INVALID_IP_ADDRESS");
        System.out.println(ipResult);
    }
    @Test
    public void testValidIP1() throws IOException {
        IP2Location ipLocator = new IP2Location();
        ipLocator.Open(DBPath);
        String ipAddress =  "108.30.178.78";
        IPResult ipResult = ipLocator.IPQuery(ipAddress);
        assertThat(ipResult.getStatus()).isEqualTo("OK");
        System.out.println(ipResult);
    }
}
