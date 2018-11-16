package com.wonders.itemstaffmanage.controller;

import com.wonders.itemstaffmanage.entity.TbItem;
import com.wonders.itemstaffmanage.entity.TbStaff;
import com.wonders.itemstaffmanage.entity.TbTask;
import com.wonders.itemstaffmanage.service.TBItemService;
import com.wonders.itemstaffmanage.service.TBStaffService;
import com.wonders.itemstaffmanage.service.TBTaskService;
import com.wonders.itemstaffmanage.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/staff")
public class StaffController extends BaseController {
    @Autowired
    private TBItemService tbItemService;

    @Autowired
    private TBStaffService tbStaffService;

    @Autowired
    private TBTaskService tbTaskService;


    @RequestMapping("/deleteStaff")
    public ResponseEntity<Void> deleteStaff(Map<String,Object> map, @RequestParam("staffId") String staffId){
        tbStaffService.deleteStaff(staffId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/updatePassword")
    public ResponseEntity<Void> updatePassword(Map<String,Object> map, LoginVo vo){
        tbStaffService.updatePassword(vo);
        request.getSession().invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
