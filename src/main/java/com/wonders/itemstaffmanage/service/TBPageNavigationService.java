package com.wonders.itemstaffmanage.service;

import com.wonders.itemstaffmanage.entity.TbPageNavigation;

import java.util.List;

public interface TBPageNavigationService {
    List<TbPageNavigation> getPageNavigationImformation();

    void saveOne(String stPictureUrl, String name, String url);

    TbPageNavigation findById(String id);


    void save(TbPageNavigation tbPageNavigation);
}
