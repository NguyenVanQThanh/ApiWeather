package com.skyapi.weatherforecast.location;

import com.skyapi.weatherforecast.common.Location;
import com.skyapi.weatherforecast.common.RealTimeWeather;
import com.skyapi.weatherforecast.realtime.RealtimeWeatherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class LocationRepositoryTest {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private RealtimeWeatherRepository realtimeWeatherRepository;

    @Test
    public void testAddSuccess(){
        Location location = new Location();
        location.setCode("NYC_USA");
        location.setCityName("New York City");
        location.setRegionName("New York");
        location.setCountryCode("US");
        location.setCountryName("United States of America");
        location.setEnabled(true);

        Location savedLocation = locationRepository.save(location);
        assertThat(savedLocation).isNotNull();
        assertThat(savedLocation.getCode()).isEqualTo("NYC_USA");
    }
    @Test
    public void testListSuccess(){
        List<Location> locations = locationRepository.findUnTrashed();
        assertThat(locations).isNotEmpty();
        locations.forEach(System.out::println);
    }
    @Test
    public void testGetNotFound(){
        String code = "DELHI_IN";
        Location location = locationRepository.findByCode(code);

        assertThat(location).isNotNull();
        assertThat(location.getCode()).isEqualTo(code);
    }
    @Test
    public void testTrashSuccess(){
        String code = "LACA_US";
        locationRepository.trashByCode(code);
        Location location = locationRepository.findByCode(code);

        assertThat(location).isNotNull();
        assertThat(location.getCode()).isEqualTo(code);
    }

    @Test
    public void testAddRealtimeWeatherData(){
        String code = "NYC_USA";

        Location location = locationRepository.findByCode(code);
        RealTimeWeather realTimeWeather = location.getRealTimeWeather();
        if (realTimeWeather == null){
            realTimeWeather = new RealTimeWeather();
            realTimeWeather.setLocation(location);
            location.setRealTimeWeather(realTimeWeather);
        }
        realTimeWeather.setTemperature(-1);
        realTimeWeather.setHumidity(30);
        realTimeWeather.setPrecipitation(40);
        realTimeWeather.setStatus("Snowy");
        realTimeWeather.setWindSpeed(15);
        realTimeWeather.setLastUpdated(new Date());

        Location updatedLocation = locationRepository.save(location);

        assertThat(updatedLocation.getRealTimeWeather().getLocationCode()).isEqualTo(updatedLocation.getCode());
    }
}
