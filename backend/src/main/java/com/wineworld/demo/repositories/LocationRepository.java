package com.wineworld.demo.repositories;

import com.wineworld.demo.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository  extends JpaRepository<Location, Long> {
}
