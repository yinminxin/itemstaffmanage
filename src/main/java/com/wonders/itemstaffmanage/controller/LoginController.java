package com.wonders.itemstaffmanage.controller;

import com.wonders.itemstaffmanage.entity.TbPageNavigation;
import com.wonders.itemstaffmanage.entity.TbStaff;
import com.wonders.itemstaffmanage.service.QiniuService;
import com.wonders.itemstaffmanage.service.TBPageNavigationService;
import com.wonders.itemstaffmanage.service.TBStaffService;
import com.wonders.itemstaffmanage.vo.LoginVo;
import com.wonders.itemstaffmanage.vo.Qiniu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LoginController extends BaseController {

    @Autowired
    private TBStaffService tbStaffService;

    @Autowired
    private TBPageNavigationService tbPageNavigationService;

    @Autowired
    private QiniuService qiniuService;

    @Autowired
    private Qiniu qiniu;

    @RequestMapping("/")
    public String navigationView(Map<String,Object> map){
        List<TbPageNavigation> tpnList=tbPageNavigationService.getPageNavigationImformation();
        map.put("token", qiniuService.getUploadToken(null));
        map.put("domain", qiniu.getBucketUrl()+"/");
        map.put("tpnList",tpnList);
        return "navigation-page";
    }

    @RequestMapping("/loginView")
    public String loginView(){
        String staffId = (String) getSessionAttr("staff");
        if(staffId==null||staffId==""){
            return "login";
        }
        return "redirect:/index";
    }

    @RequestMapping("/login")
    public ResponseEntity<Void> login (Map<String,Object> map,  LoginVo loginVo){
        TbStaff tbStaff = tbStaffService.findByUsernameAndPassword(loginVo);
        if(!StringUtils.isEmpty(tbStaff)){
            setSessionAttr("staff",tbStaff.getStId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/loginOut")
    public ResponseEntity<Void> loginOut(){
        request.getSession().removeAttribute("staff");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
