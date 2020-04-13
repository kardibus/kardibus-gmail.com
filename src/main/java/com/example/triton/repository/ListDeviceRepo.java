package com.example.triton.repository;

import com.example.triton.domain.ListDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListDeviceRepo extends JpaRepository<ListDevice,Long> {

}
