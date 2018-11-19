package com.wonders.itemstaffmanage.service.impl;

import com.wonders.itemstaffmanage.entity.TbItem;
import com.wonders.itemstaffmanage.entity.TbStaff;
import com.wonders.itemstaffmanage.entity.TbTask;
import com.wonders.itemstaffmanage.repository.TBItemRepository;
import com.wonders.itemstaffmanage.repository.TBStaffRepository;
import com.wonders.itemstaffmanage.repository.TBTaskRepository;
import com.wonders.itemstaffmanage.service.TBTaskService;
import com.wonders.itemstaffmanage.utils.RandomCodeUtils;
import com.wonders.itemstaffmanage.utils.TimeUtils;
import com.wonders.itemstaffmanage.vo.AddTaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TBTaskServiceIpml implements TBTaskService {

    @Autowired
    private TBTaskRepository tbTaskRepository;

    @Autowired
    private TBItemRepository tbItemRepository;

    @Autowired
    private TBStaffRepository tbStaffRepository;

    @Override
    @Transactional
    public void addOneTask(AddTaskVo vo) {
        Optional<TbItem> tbItem=tbItemRepository.findById(vo.getItemId());
        Optional<TbStaff> tbStaff = tbStaffRepository.findById(vo.getStaffId());
        TbTask tbtask = new TbTask();
        tbtask.setStId(RandomCodeUtils.getUUid());
        tbtask.setDtCreatetime(new Date());
        tbtask.setStName(vo.getTaskName());
        tbtask.setStState((byte) 0);
        tbtask.setItem(tbItem.get());
        tbtask.setStaff(tbStaff.get());
        tbTaskRepository.save(tbtask);
    }

    @Override
    public void deleteTaskById(String taskId) {
        TbTask tbTask = tbTaskRepository.findById(taskId).get();
        tbTask.setStState((byte) 2);
        tbTaskRepository.save(tbTask);
    }

    @Override
    public List<TbTask> findFinishTask(String staffId) {
        byte state=1;
        List<TbTask> tasks = tbTaskRepository.findAllByStaffId(staffId);
        for(TbTask task : tasks){
            task.getItem().setTbTasks(null);
            task.getStaff().setTbTasks(null);
        }
        return tasks;
    }

    @Override
    public List<TbTask> findAll() {
        return tbTaskRepository.findAll();
    }

    @Override
    public void finishTaskById(String taskId) {
        TbTask task = tbTaskRepository.findById(taskId).get();
        task.setStState((byte) 1);
        task.setDtFinishtime(new Date());
        task.setNumFinishWeek(TimeUtils.getWeekOfYear());
        task.setNumFinishYear(TimeUtils.getYear());
        tbTaskRepository.save(task);
    }

    @Override
    public List<TbTask> findAllByItemId(String itemId) {
        List<TbTask> tasks=tbTaskRepository.findByItemId(itemId);
        if(tasks.size()>0){
            for (TbTask task :tasks){
                task.getItem().setTbTasks(null);
                task.getStaff().setTbTasks(null);
            }
        }
        return tasks;
    }

    @Override
    public List<TbTask> findAllByStStateAndNumFinishYearAndNumFinishWeek(byte state, String finishYear, String finishWeek) {
        Integer year = Integer.valueOf(finishYear);
        Integer week = Integer.valueOf(finishWeek);
        List<TbTask> t = tbTaskRepository.findAllByStStateAndNumFinishYearAndNumFinishWeek(state, year, week);
        return t;
    }
}
