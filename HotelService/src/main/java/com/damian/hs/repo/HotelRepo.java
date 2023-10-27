package com.damian.hs.repo;

import com.damian.hs.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRepo extends JpaRepository<Hotel,String> {

    Optional<Hotel> findByHotelName(String hotelName);

    List<Hotel> findByPackageId(String packageID);
}
