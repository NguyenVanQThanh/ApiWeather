package com.skyapi.weatherforecast.location;

import com.skyapi.weatherforecast.common.Location;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocationRepository extends CrudRepository<Location, String> {
    @Query("SELECT l FROM Location l WHERE l.trashed = false")
    public List<Location> findUnTrashed();
    @Query("SELECT l FROM Location l WHERE l.trashed = false AND l.code = ?1")
    public Location findByCode(String code);
    @Modifying
    @Transactional
    @Query("UPDATE Location l SET l.trashed = true WHERE l.code = ?1")
    public void trashByCode(String code);
}
