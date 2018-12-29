package com.wonders.itemstaffmanage.repository;

import com.wonders.itemstaffmanage.entity.TbPageNavigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TBPageNavigationRepoditory extends JpaRepository<TbPageNavigation,String>,JpaSpecificationExecutor<TbPageNavigation> {
}
