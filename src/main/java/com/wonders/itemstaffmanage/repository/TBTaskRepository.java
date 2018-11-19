package com.wonders.itemstaffmanage.repository;

import com.wonders.itemstaffmanage.entity.TbTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TBTaskRepository extends JpaRepository<TbTask,String>,JpaSpecificationExecutor<TbTask>{

    @Query(value = "SELECT * FROM tb_task WHERE ST_STAFF_ID=:staffId",nativeQuery = true)
    List<TbTask> findAllByStaffId(@Param("staffId") String staffId);

    @Query(value = "SELECT * FROM tb_task WHERE ST_ITEM_ID=:itemId",nativeQuery = true)
    List<TbTask> findByItemId(@Param("itemId") String itemId);

    /**
     * 根据年和周查询已完成任务
     * @param state
     * @param year
     * @param week
     * @return
     */
    List<TbTask> findAllByStStateAndNumFinishYearAndNumFinishWeek(byte state, Integer year, Integer week);
}
