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

    public Location update(Location locationInRequest) throws LocationNotFoundException {
        String code = locationInRequest.getCode();

        Location locationInDB = repository.findByCode(code);

        if (locationInDB == null){
            throw new LocationNotFoundException("No Location found with the given code: "+ code);
        }

        locationInDB.setCityName(locationInRequest.getCityName());
        locationInDB.setCountryName(locationInRequest.getCountryName());
        locationInDB.setEnabled(locationInRequest.isEnabled());
        locationInDB.setCountryCode(locationInRequest.getCountryCode());
        locationInDB.setRegionName(locationInRequest.getRegionName());

        return repository.save(locationInDB);
    }
    public void delete(String code) throws LocationNotFoundException {
        if (!repository.existsById(code)){
            throw new LocationNotFoundException("No Location found with the given code: "+ code);
        }
        repository.trashByCode(code);
    }
}
