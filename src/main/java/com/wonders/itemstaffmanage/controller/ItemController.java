package com.wonders.itemstaffmanage.controller;

import com.wonders.itemstaffmanage.entity.TbItem;
import com.wonders.itemstaffmanage.entity.TbTask;
import com.wonders.itemstaffmanage.service.TBItemService;
import com.wonders.itemstaffmanage.service.TBStaffService;
import com.wonders.itemstaffmanage.service.TBTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private TBItemService tbItemService;

    @Autowired
    private TBStaffService tbStaffService;

    @Autowired
    private TBTaskService tbTaskService;

    @RequestMapping("/addItem")
    public ResponseEntity<Void> addItem(Map<String,Object> map, @RequestParam("itemName") String itemName){
        tbItemService.addItem(itemName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/deleteItem")
    public ResponseEntity<Void> deleteItem(Map<String,Object> map, @RequestParam("itemId") String itemId){
        TbItem item = tbItemService.findByItemId(itemId);
        int i=0;
        for(TbTask task :item.getTbTasks()){
            if(task.getStState()==0){
                i++;
            }
        }
        if(i>0){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        tbItemService.deleteItem(itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
