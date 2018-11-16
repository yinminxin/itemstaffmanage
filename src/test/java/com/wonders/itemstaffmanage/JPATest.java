package com.wonders.itemstaffmanage;

import com.wonders.itemstaffmanage.entity.TbItem;
import com.wonders.itemstaffmanage.entity.TbStaff;
import com.wonders.itemstaffmanage.entity.TbTask;
import com.wonders.itemstaffmanage.repository.TBItemRepository;
import com.wonders.itemstaffmanage.repository.TBStaffRepository;
import com.wonders.itemstaffmanage.repository.TBTaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPATest {

    @Autowired
    private TBStaffRepository tbStaffRepository;
    @Autowired
    private TBTaskRepository tbTaskRepository;
    @Autowired
    private TBItemRepository tbItemRepository;

    @Test
    public void test(){
        TbStaff yinminxin = tbStaffRepository.findById("yinminxin").get();
        TbItem item = tbItemRepository.findById("jqschool").get();
        TbTask task = tbTaskRepository.findById("yemian").get();
    }
}
