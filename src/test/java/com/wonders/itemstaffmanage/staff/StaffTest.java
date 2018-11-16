package com.wonders.itemstaffmanage.staff;

import com.wonders.itemstaffmanage.entity.TbItem;
import com.wonders.itemstaffmanage.entity.TbStaff;
import com.wonders.itemstaffmanage.entity.TbTask;
import com.wonders.itemstaffmanage.repository.TBItemRepository;
import com.wonders.itemstaffmanage.repository.TBStaffRepository;
import com.wonders.itemstaffmanage.repository.TBTaskRepository;
import com.wonders.itemstaffmanage.utils.ExcelUtil;
import com.wonders.itemstaffmanage.utils.MD5Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StaffTest {

    @Autowired
    private TBStaffRepository tbStaffRepository;
    @Autowired
    private TBTaskRepository tbTaskRepository;
    @Autowired
    private TBItemRepository tbItemRepository;

    @Test
    public void addOneStaff(){
        String pinyin="admin";   // 人员姓名的拼音
        String stName="管理员";   // 人员姓名
        TbStaff staff = new TbStaff();
        staff.setStId(pinyin);
        staff.setStName(stName);
        staff.setStState((byte) 0);
        staff.setStPassword("123456");
        staff.setStCreatetime(new Date());
        staff.setStUsername(pinyin);
        staff.setStRole((short) 11);
        tbStaffRepository.save(staff);
    }

    /**
     * excle表格批量导入人员 excel文件路径(两列,第一列:人员姓名的拼音(作为用户名);第二列:人员姓名)
     * 密码默认:123456
     * 默认角色:11-人员
     * @throws Exception
     */
    @Test
    @Rollback(false)
    @Transactional(/*rollbackFor = Exception.class*/)
    public void addStaffs() throws Exception {
        int i = 0;
        String filePath = "C:/Users/ymx/Desktop/temp/项目人员管理/人员.xlsx";  //excel文件路径
        File excelFile = new File(filePath);
        List<TbStaff> staffs = convertToVo(excelFile);
        for(TbStaff staff :staffs){
            tbStaffRepository.save(staff);
            i++;
        }
        System.out.println("添加成功"+i+"人");
    }

    /**
     * 把Excel表格转换为list
     * 表格格式为:
     * 年级  班级  姓名  身份证 学籍主号 全国学籍号 学籍副号
     *
     * @param file 文件
     * @return 返回封装后学生信息
     * @throws Exception 抛出文件解析异常
     */
    private List<TbStaff> convertToVo(File file) throws Exception {
        List<List<String>> lists = ExcelUtil.convertExcel(file);
        // 移除第一行
        lists.remove(0);
        // 统计行数
        int rowNum = 0;
        List<TbStaff> tbStaffs = new ArrayList<>();
        for (List<String> list : lists) {
            rowNum++;
            int lowNum = 0;
            if (StringUtils.isEmpty(list.get(lowNum))) {
                break;
            }
            // 赛到staff中
            TbStaff staff = new TbStaff();
            staff.setStId(list.get(0).trim()); // 人员id
            staff.setStName(list.get(1).trim()); //人员姓名
            staff.setStState((byte) 0); //人员状态
            staff.setStPassword(MD5Utils.getPassword("123456")); //默认密码
            staff.setStCreatetime(new Date()); // 创建时间
            staff.setStUsername(list.get(0).trim()); // 用户名
            staff.setStRole((short) 11); //人员角色 10-管理 11-人员
            tbStaffs.add(staff);
        }
        return tbStaffs;
    }


}
