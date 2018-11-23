package com.wonders.itemstaffmanage.utils;

import com.wonders.itemstaffmanage.entity.TbItem;
import com.wonders.itemstaffmanage.entity.TbStaff;
import com.wonders.itemstaffmanage.entity.TbTask;

public class GetFinishProceedTasksNumUtil {
    public static TbItem setItemTasksNum(TbItem tbItem){
        int j=0;//进行中的任务
        int f=0; //已完成的任务
        for(TbTask task : tbItem.getTbTasks()){
            if(task.getStState()==0){
                j++;
            };
            if(task.getStState()==1){
                f++;
            }
        }
        tbItem.setTaskFinishNum(f);
        tbItem.setTaskNowNum(j);
        return tbItem;
    }

    public static TbStaff setStaffTasksNum(TbStaff tbStaff){
        int j=0;//进行中的任务
        int f=0; //已完成的任务
        for(TbTask task : tbStaff.getTbTasks()){
            if(task.getStState()==0){
                j++;
            };
            if(task.getStState()==1){
                f++;
            }
        }
        tbStaff.setTaskFinishNum(f);
        tbStaff.setTaskNowNum(j);
        return tbStaff;
    }
}
