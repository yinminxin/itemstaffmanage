package com.wonders.itemstaffmanage.controller;

import com.wonders.itemstaffmanage.entity.TbItem;
import com.wonders.itemstaffmanage.entity.TbStaff;
import com.wonders.itemstaffmanage.entity.TbTask;
import com.wonders.itemstaffmanage.service.TBItemService;
import com.wonders.itemstaffmanage.service.TBStaffService;
import com.wonders.itemstaffmanage.service.TBTaskService;
import com.wonders.itemstaffmanage.utils.GetFinishProceedTasksNumUtil;
import com.wonders.itemstaffmanage.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.util.*;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
    @Autowired
    private TBItemService tbItemService;

    @Autowired
    private TBStaffService tbStaffService;

    @Autowired
    private TBTaskService tbTaskService;
    @RequestMapping("/index")
    public String findAllStaff1(Map<String,Object> map){
        //获取session中的用户信息
        String staffId = (String) getSessionAttr("staff");
        //判断用户是否存在,不存在跳转登陆页面
        if(staffId==null||staffId==""){
            return "login";
        }

        //已完成任务数
        int finish=0;
        //进行中任务数
        int proceed=0;
        //今日新增数量
        int add=0;
        //今天的日期
        Date date = new Date();
        DateFormat format = DateFormat.getDateInstance();
        String today = format.format(date);

        //当前年份
        int weekYear = TimeUtils.getYear();
        //今天属于今年的第几周
        int weekOfYear = TimeUtils.getWeekOfYear();
        map.put("weekYear",weekYear);
        map.put("weekOfYear",weekOfYear);

        TbStaff staff = tbStaffService.findByStaffId(staffId);

        List<TbItem> items=tbItemService.findAll();
        List<TbTask> tasks=null;
        if(staff.getStRole()==10){
            tasks=tbTaskService.findAll();
            List<TbStaff> staffs=tbStaffService.findAll();
            for(TbStaff tbStaff :staffs){
                int j=0;//进行中的任务
                int f=0; //已完成的任务
                for(TbTask task : tbStaff.getTbTasks()){
                    if(task.getStState()==0){ j++; }
                    if(task.getStState()==1){ f++; }
                }
                tbStaff.setTaskFinishNum(f);
                tbStaff.setTaskNowNum(j);
            }
            map.put("staffs",staffs);
        }else{
            tasks = staff.getTbTasks();
            if(staff.getTbTasks().size()>0){
                staff = GetFinishProceedTasksNumUtil.setStaffTasksNum(staff);
            }
        }
        if(!tasks.isEmpty()){
            for(TbTask task : tasks){
                if(1==task.getStState()) { finish++; }
                if(0==task.getStState()) { proceed++; }
                if(today.equals(format.format(task.getDtCreatetime()))){add++;}
            }
        }
        map.put("finishTaskNum",finish);
        map.put("todayAddTaskNum",add);
        map.put("proceedTaskNum",proceed);
        map.put("items",items);
        map.put("user",staff);
        return "index";
    }
}
