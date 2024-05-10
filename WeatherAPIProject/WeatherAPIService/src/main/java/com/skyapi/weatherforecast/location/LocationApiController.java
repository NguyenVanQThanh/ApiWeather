package com.skyapi.weatherforecast.location;

import com.skyapi.weatherforecast.common.Location;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/locations")
public class LocationApiController {
    private LocationService service;

    public LocationApiController (LocationService service){
        super();
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Location> addLocation(@RequestBody @Valid Location location){
        Location addedLocation = service.add(location);
        URI uri = URI.create("/v1/locations/" + location.getCode());

        return ResponseEntity.created(uri).body(addedLocation);
    }
    @GetMapping
    public ResponseEntity<?> listLocation(){
        List<Location> lists = service.list();
        if (lists.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lists);
    }
    @GetMapping("/{code}")
    public ResponseEntity<?> getLocation(@PathVariable("code") String code){
        Location location = service.get(code);

        if (location == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(location);
    }
}
