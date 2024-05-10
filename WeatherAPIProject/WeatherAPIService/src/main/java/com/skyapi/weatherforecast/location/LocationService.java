package com.skyapi.weatherforecast.location;

import com.skyapi.weatherforecast.common.Location;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private LocationRepository repository;

    public LocationService(LocationRepository repository) {
        super();
        this.repository = repository;
    }
    public Location add(Location location){
        return repository.save(location);
    }
    public List<Location> list(){
        return repository.findUnTrashed();
    }
    public Location get(String code){
        return repository.findByCode(code);
    }
}
