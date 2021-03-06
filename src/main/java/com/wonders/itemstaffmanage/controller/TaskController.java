package com.wonders.itemstaffmanage.controller;

import com.wonders.itemstaffmanage.entity.TbItem;
import com.wonders.itemstaffmanage.entity.TbStaff;
import com.wonders.itemstaffmanage.entity.TbTask;
import com.wonders.itemstaffmanage.service.MailService;
import com.wonders.itemstaffmanage.service.TBItemService;
import com.wonders.itemstaffmanage.service.TBStaffService;
import com.wonders.itemstaffmanage.service.TBTaskService;
import com.wonders.itemstaffmanage.utils.GetFinishProceedTasksNumUtil;
import com.wonders.itemstaffmanage.vo.AddTaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Autowired
    private TBItemService tbItemService;

    @Autowired
    private TBStaffService tbStaffService;

    @Autowired
    private TBTaskService tbTaskService;

    @Autowired
    private MailService mailService;

    @RequestMapping("/addTask")
    @ResponseBody
    public ResponseEntity<Void> addTask(AddTaskVo vo){
        TbStaff tbStaff=tbStaffService.findByStaffId(vo.getStaffId());
        tbTaskService.addOneTask(vo);
        String content = " 您有新的任务等待处理:前往http://10.1.64.132:9001/index";
        mailService.sendTextMail(tbStaff.getStMail(), "任务管理系统", content);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/deleteTask")
    @ResponseBody
    public ResponseEntity<Void> deleteTask(@RequestParam(value = "taskId") String taskId){
        tbTaskService.deleteTaskById(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/finishTask")
    @ResponseBody
    public ResponseEntity<Void> finishTask(@RequestParam(value = "taskId") String taskId){
        tbTaskService.finishTaskById(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/findFinishTask")
    @ResponseBody
    public ResponseEntity<List<TbTask>> findFinishTask(@RequestParam(value = "staffId") String staffId){
        List<TbTask> tasks=tbTaskService.findFinishTask(staffId);
        return ResponseEntity.ok(tasks);
    }

    @RequestMapping("/findFinishTaskView")
    public String findFinishTaskView(Map<String,Object> map,@RequestParam("staffId") String staffId){
        List<TbTask> tasks = tbTaskService.findFinishTask(staffId);
        map.put("tasks",tasks);
        return "tasks/staffTasks";
    }


    @RequestMapping("/findTasksByItemId")
    public String findTasksByItemId(Map<String,Object> map,@RequestParam("itemId") String itemId){
        List<TbTask> tasks = new ArrayList<>();
//        List<TbStaff> staffs=tbStaffService.findAll();
//        List<TbItem> items=tbItemService.findAll();
        TbItem item=tbItemService.findByItemId(itemId);
        if(item.getTbTasks().size()>0){
            item = GetFinishProceedTasksNumUtil.setItemTasksNum(item);
            tasks.addAll(item.getTbTasks());
            map.put("tasks",tasks);
        }
        map.put("item",item);
        return "tasks/itemTasks";
    }

    @RequestMapping("/findTasksByStaffId")
    public String findTasksByStaffId(Map<String,Object> map,@RequestParam("staffId") String staffId){
        List<TbTask> tasks = new ArrayList<>();
//        List<TbStaff> staffs=tbStaffService.findAll();
//        List<TbItem> items=tbItemService.findAll();
        TbStaff staff=tbStaffService.findByStaffId(staffId);
        if(staff.getTbTasks().size()>0){
            staff = GetFinishProceedTasksNumUtil.setStaffTasksNum(staff);
            tasks.addAll(staff.getTbTasks());
            map.put("tasks",tasks);
        }
        map.put("staff",staff);
        return "tasks/staffTasks";
    }
    @RequestMapping("/searchTasks")
    public String findTasksBySearchName(Map<String,Object> map,@RequestParam("searchName") String searchName){
        List<TbTask> tasks = new ArrayList<>();
        TbItem item=tbItemService.findByStNameAndStState(searchName,(byte)0);
        if (!StringUtils.isEmpty(item)) {
            if(item.getTbTasks().size()>0){
                item = GetFinishProceedTasksNumUtil.setItemTasksNum(item);
                tasks.addAll(item.getTbTasks());
                map.put("tasks",tasks);
            }
            map.put("item",item);
            return "tasks/itemTasks";
        }
        TbStaff staff=tbStaffService.findOneByStNameAndStState(searchName,(byte)0);
        if(!StringUtils.isEmpty(staff)){
            if(staff.getTbTasks().size()>0){
                staff = GetFinishProceedTasksNumUtil.setStaffTasksNum(staff);
                tasks.addAll(staff.getTbTasks());
                map.put("tasks",tasks);
            }
            map.put("staff",staff);
            return "tasks/staffTasks";
        }
        return "index";
    }

    @RequestMapping("/loadTableByStaffOrItem")
    public String loadTableByStaffOrItem(Map<String,Object> map,@RequestParam("loadId") String loadId){
        List<TbTask> tasks = new ArrayList<>();
//        List<TbStaff> staffs=tbStaffService.findAll();
//        List<TbItem> items=tbItemService.findAll();
        TbStaff staff=tbStaffService.findByStaffId(loadId);
        if(!StringUtils.isEmpty(staff)){
            if(staff.getTbTasks().size()>0){
                staff = GetFinishProceedTasksNumUtil.setStaffTasksNum(staff);
                tasks.addAll(staff.getTbTasks());
                map.put("tasks",tasks);
            }
            map.put("staff",staff);
            return "tasks/staffTasks";
        }
        TbItem item=tbItemService.findByItemId(loadId);
        if(item.getTbTasks().size()>0){
            item = GetFinishProceedTasksNumUtil.setItemTasksNum(item);
            tasks.addAll(item.getTbTasks());
            map.put("tasks",tasks);
        }
        map.put("item",item);
        return "tasks/itemTasks";
    }


    @RequestMapping("/getTasksOfWeek")
    public String getTasksOfWeek(Map<String,Object> map,@RequestParam("finishYear") String finishYear,
                                 @RequestParam("finishWeek") String finishWeek){

        List<TbTask> finishTasks=tbTaskService.findAllByStStateAndNumFinishYearAndNumFinishWeek((byte)1,finishYear,finishWeek);

        map.put("finishTasks",finishTasks);
//        //获取session中的用户信息
        String staffId = (String) getSessionAttr("staff");
        TbStaff staff = tbStaffService.findByStaffId(staffId);
        map.put("userone",staff);
        return "tasks/finishTasksOfWeek";
    }
}
