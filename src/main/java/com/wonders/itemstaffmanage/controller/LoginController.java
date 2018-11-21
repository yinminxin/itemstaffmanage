package com.wonders.itemstaffmanage.controller;

import com.wonders.itemstaffmanage.entity.TbStaff;
import com.wonders.itemstaffmanage.service.TBStaffService;
import com.wonders.itemstaffmanage.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/")
public class LoginController extends BaseController {

    @Autowired
    private TBStaffService tbStaffService;

    @RequestMapping("/")
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
