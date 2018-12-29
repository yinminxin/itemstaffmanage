package com.wonders.itemstaffmanage.service.impl;

import com.wonders.itemstaffmanage.entity.TbPageNavigation;
import com.wonders.itemstaffmanage.repository.TBPageNavigationRepoditory;
import com.wonders.itemstaffmanage.service.TBPageNavigationService;
import com.wonders.itemstaffmanage.utils.RandomCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TBPageNavigationServiceImpl implements TBPageNavigationService {

    @Autowired
    private TBPageNavigationRepoditory tbPageNavigationRepoditory;

    @Override
    public List<TbPageNavigation> getPageNavigationImformation() {
        List<TbPageNavigation> all = tbPageNavigationRepoditory.findAll();
        return all;
    }

    @Override
    public void saveOne(String stPictureUrl, String name, String url) {
        TbPageNavigation tbPageNavigation = new TbPageNavigation(RandomCodeUtils.getUUid(), name, url, stPictureUrl, (byte) 0);
        tbPageNavigationRepoditory.save(tbPageNavigation);
    }

    @Override
    public TbPageNavigation findById(String id) {
        return tbPageNavigationRepoditory.getOne(id);
    }

    @Override
    public void save(TbPageNavigation tbPageNavigation) {
        tbPageNavigationRepoditory.save(tbPageNavigation);
    }

}
