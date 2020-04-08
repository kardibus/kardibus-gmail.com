package com.example.triton.repository;

import com.example.triton.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DeviceRepo extends JpaRepository<Device,Long> {

    @Query(value = "SELECT * FROM Device WHERE TO_CHAR(date,'dd.mm.yyyy') LIKE :search ",nativeQuery = true)
    Iterable<Device> findByDay(@Param("search") String search);


}
