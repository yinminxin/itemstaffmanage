package com.wonders.itemstaffmanage.service;

import com.wonders.itemstaffmanage.entity.TbTask;
import com.wonders.itemstaffmanage.vo.AddTaskVo;

import java.util.List;

public interface TBTaskService {
    /**
     * 添加一个任务
     * @param vo
     */
    void addOneTask(AddTaskVo vo);

    /**
     * 删除一个任务
     * @param taskId
     */
    void deleteTaskById(String taskId);

    /**
     * 查找单个员工完成的任务
     * @param staffId
     * @return
     */
    List<TbTask> findFinishTask(String staffId);

    /**
     * 查找所有任务
     * @return
     */
    List<TbTask> findAll();

    /**
     * 将任务改为完成状态
     * @param taskId
     */
    void finishTaskById(String taskId);

    /**
     * 根据项目id查找所有任务
     * @param itemId
     * @return
     */
    List<TbTask> findAllByItemId(String itemId);
}
