package com.wonders.itemstaffmanage.service.impl;

import com.wonders.itemstaffmanage.entity.TbStaff;
import com.wonders.itemstaffmanage.entity.TbTask;
import com.wonders.itemstaffmanage.repository.TBItemRepository;
import com.wonders.itemstaffmanage.repository.TBStaffRepository;
import com.wonders.itemstaffmanage.repository.TBTaskRepository;
import com.wonders.itemstaffmanage.service.TBStaffService;
import com.wonders.itemstaffmanage.utils.MD5Utils;
import com.wonders.itemstaffmanage.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TBStaffServiceImpl implements TBStaffService {

    @Autowired
    private TBTaskRepository tbTaskRepository;

    @Autowired
    private TBItemRepository tbItemRepository;

    @Autowired
    private TBStaffRepository tbStaffRepository;

    @Override
    public List<TbStaff> findAll() {
        byte state=0;
        List<TbStaff> all = tbStaffRepository.findAllByStState(state);
        return all;
    }

    @Override
    public TbStaff findByStaffId(String staffId) {
        Optional<TbStaff> staffO = tbStaffRepository.findById(staffId);
        if (staffO.isPresent()){
            TbStaff tbStaff = staffO.get();
            List<TbTask> tbTasks = tbStaff.getTbTasks();
            for(TbTask task : tbTasks){
//                task.getStaff().setTbTasks(null);
                task.getItem().setTbTasks(null);
            }
            return tbStaff;
        }
        return null;
    }

    @Override
    public void deleteStaff(String staffId) {
        TbStaff tbStaff = tbStaffRepository.findById(staffId).get();
        tbStaff.setStState((byte) 1);
        tbStaffRepository.save(tbStaff);
        List<TbTask> tasks = tbTaskRepository.findAllByStaffId(staffId);
        for(TbTask task : tasks){
            task.setStState((byte) 2);
            tbTaskRepository.save(task);
        }
    }

    @Override
    public TbStaff findByUsernameAndPassword(LoginVo loginVo) {
        String username=loginVo.getStUsername();
        String password=MD5Utils.getPassword(loginVo.getStPassword());
        TbStaff t=tbStaffRepository.findByUsernameAndPassword(username,password);
        return t;
    }

    @Override
    public void updatePassword(LoginVo vo) {
        String stId=vo.getStId();
        String password=MD5Utils.getPassword(vo.getStPassword());
        TbStaff staff = tbStaffRepository.findById(stId).get();
        staff.setStPassword(password);
        tbStaffRepository.save(staff);
    }

    @Override
    public TbStaff findOneByStNameAndStState(String searchName, byte state) {
        return tbStaffRepository.findByStNameAndStState(searchName,state);
    }
}
