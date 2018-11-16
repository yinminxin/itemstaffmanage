package com.wonders.itemstaffmanage.service;

import com.wonders.itemstaffmanage.entity.TbStaff;
import com.wonders.itemstaffmanage.vo.LoginVo;

import java.util.List;

public interface TBStaffService {

    /**
     * 查找所有人员
     * @return
     */
    List<TbStaff> findAll();

    /**
     * 根据人员id查找任务
     * @param staffId
     * @return
     */
    TbStaff findByStaffId(String staffId);

    /**
     * 删除人员
     * @param staffId
     */
    void deleteStaff(String staffId);

    /**
     * 根据用户名密码登陆
     * @param loginVo
     * @return
     */
    TbStaff findByUsernameAndPassword(LoginVo loginVo);

    /**
     * 更改密码
     * @param vo
     */
    void updatePassword(LoginVo vo);

    TbStaff findOneByStNameAndStState(String searchName, byte b);
}
