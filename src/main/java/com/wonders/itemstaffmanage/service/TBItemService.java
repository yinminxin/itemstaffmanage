package com.wonders.itemstaffmanage.service;

import com.wonders.itemstaffmanage.entity.TbItem;

import java.util.List;

public interface TBItemService {
    List<TbItem> findAll();

    /**
     * 添加项目
     * @param itemName
     */
    void addItem(String itemName);

    /**
     * 根据itemid查找项目
     * @param itemId
     * @return
     */
    TbItem findByItemId(String itemId);

    /**
     * 根据itemid删除项目
     * @param itemId
     */
    void deleteItem(String itemId);

    /**
     * 根据项目名称查找项目
     * @param searchName
     * @param state
     * @return
     */
    TbItem findByStNameAndStState(String searchName, byte state);
}
