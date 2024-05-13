package com.skyapi.weatherforecast.realtime;

import com.skyapi.weatherforecast.common.RealTimeWeather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RealtimeWeatherRepositoryTest {
    @Autowired
    private RealtimeWeatherRepository repository;

    @Test
    public void testUpdate(){
        String locationCode = "NYC_USA";

        RealTimeWeather realTimeWeather = repository.findById(locationCode).get();

        realTimeWeather.setTemperature(-2);
        realTimeWeather.setHumidity(35);
        realTimeWeather.setPrecipitation(50);
        realTimeWeather.setStatus("Snowy");
        realTimeWeather.setWindSpeed(12);
        realTimeWeather.setLastUpdated(new Date());

        repository.save(realTimeWeather);
    }
}
