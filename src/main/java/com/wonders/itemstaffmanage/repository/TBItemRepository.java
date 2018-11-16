package com.wonders.itemstaffmanage.repository;

import com.wonders.itemstaffmanage.entity.TbItem;
import com.wonders.itemstaffmanage.entity.TbTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TBItemRepository extends JpaRepository<TbItem,String>,JpaSpecificationExecutor<TbItem>{
    /**
     * 根据项目名称查找项目
     * @param searchName 项目名称
     * @param state  正常状态-0
     * @return
     */
    TbItem findByStNameAndStState(String searchName, byte state);
}
