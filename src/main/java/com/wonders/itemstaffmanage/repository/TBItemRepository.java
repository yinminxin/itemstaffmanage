package com.wonders.itemstaffmanage.repository;

import com.wonders.itemstaffmanage.entity.TbItem;
import com.wonders.itemstaffmanage.entity.TbTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TBItemRepository extends JpaRepository<TbItem,String>,JpaSpecificationExecutor<TbItem>{
}
