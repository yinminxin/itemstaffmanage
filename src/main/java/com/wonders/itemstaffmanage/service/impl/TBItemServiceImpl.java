package com.wonders.itemstaffmanage.service.impl;

import com.wonders.itemstaffmanage.entity.TbItem;
import com.wonders.itemstaffmanage.entity.TbStaff;
import com.wonders.itemstaffmanage.entity.TbTask;
import com.wonders.itemstaffmanage.repository.TBItemRepository;
import com.wonders.itemstaffmanage.repository.TBStaffRepository;
import com.wonders.itemstaffmanage.repository.TBTaskRepository;
import com.wonders.itemstaffmanage.service.TBItemService;
import com.wonders.itemstaffmanage.utils.RandomCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TBItemServiceImpl implements TBItemService {

    @Autowired
    private TBTaskRepository tbTaskRepository;

    @Autowired
    private TBItemRepository tbItemRepository;

    @Autowired
    private TBStaffRepository tbStaffRepository;

    @Override
    public List<TbItem> findAll() {
        List<TbItem> all=tbItemRepository.findAll();
        List<TbItem> tbItems = all.stream().filter(a -> a.getStState() == 0).collect(Collectors.toList());
        /*for(TbItem tbItem : tbItems){
            List<TbTask> tbTasks = tbItem.getTbTasks();
            if(tbTasks!=null&&!"".equals(tbTasks)){
                for(TbTask task : tbTasks){
                    task.setItem(null);
                    task.setStaff(null);
                }
            }
        }*/
        return tbItems;
    }

    @Override
    public void addItem(String itemName) {
        TbItem tbItem = new TbItem();
        tbItem.setStId(RandomCodeUtils.getUUid());
        tbItem.setStName(itemName);
        tbItem.setStCreatetime(new Date());
        tbItem.setStState((byte) 0);
        tbItemRepository.save(tbItem);
    }

    @Override
    public TbItem findByItemId(String itemId) {
        TbItem tbItem = tbItemRepository.findById(itemId).get();
        List<TbTask> tbTasks = tbItem.getTbTasks();
        return tbItem;
    }

    @Override
    public void deleteItem(String itemId) {
        TbItem tbItem = tbItemRepository.findById(itemId).get();
        tbItem.setStState((byte) 1);
        tbItemRepository.save(tbItem);
    }

    @Override
    public TbItem findByStNameAndStState(String searchName, byte state) {
        return tbItemRepository.findByStNameAndStState(searchName, state);
    }
}
