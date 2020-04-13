package com.example.triton.repository;

import com.example.triton.domain.Device;
import com.example.triton.domain.ListDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DeviceRepo extends JpaRepository<Device,Long> {

    @Query(value = "select max(id) as id,sum(quantity) as quantity,max(date) as date,max(user_id) as user_id,list_id from (select * from device where user_id= :user_id) as device WHERE TO_CHAR(date,'dd.mm.yyyy') LIKE :search group by list_id",nativeQuery = true)
    Iterable<Device> findByDay(@Param("search") String search,@Param("user_id") Long user_id);

    @Query(value = "SELECT max(id) as id,sum(quantity) as quantity,max(date) as date,max(user_id) as user_id,list_id FROM (select * from device where user_id=:user_id) as device WHERE to_char(date,'yyyy-mm-dd') between :dateFirst and :dateTwo group by list_id",nativeQuery = true)
    Iterable<Device> findByMonth(@Param("dateFirst") String dateFirst,@Param("dateTwo")String dateTwo,@Param("user_id") Long user_id);

    @Query(value = "SELECT * from device where user_id= :user_id",nativeQuery = true)
    Iterable<Device> findAllByAuthor(@Param("user_id") Long id);
}
