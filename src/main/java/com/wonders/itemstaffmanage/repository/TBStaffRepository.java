package com.wonders.itemstaffmanage.repository;

import com.wonders.itemstaffmanage.entity.TbStaff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TBStaffRepository extends JpaRepository<TbStaff,String>,JpaSpecificationExecutor<TbStaff> {

    @Query(value = "select * from tb_staff where ST_USERNAME=:username and ST_PASSWORD=:password and ST_STATE=0",nativeQuery = true)
    TbStaff findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);


    @Query(value = "SELECT * FROM tb_staff WHERE ST_STATE=:state",nativeQuery = true)
    List<TbStaff> findAllByStState(@Param("state") byte state);
}
