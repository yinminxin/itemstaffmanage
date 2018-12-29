package com.wonders.itemstaffmanage.controller;

import com.wonders.itemstaffmanage.entity.TbPageNavigation;
import com.wonders.itemstaffmanage.service.TBPageNavigationService;
import com.wonders.itemstaffmanage.utils.RandomCodeUtils;
import com.wonders.itemstaffmanage.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("navigation")
public class NavigationController extends BaseController {
    @Autowired
    private TBPageNavigationService tbPageNavigationService;

    @RequestMapping("upload")
    public ResponseEntity<Void> upload(@RequestParam("stPictureUrl")String stPictureUrl,
                                        @RequestParam("stName") String name,
                                        @RequestParam("stUrl") String url   ){

        if(StringUtils.isBlank(stPictureUrl)||StringUtils.isBlank(name)||StringUtils.isBlank(url)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        tbPageNavigationService.saveOne(stPictureUrl,name,url);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("deleteOne")
    public ResponseEntity<Void> delete(@RequestParam("id") String id){
        TbPageNavigation tbPageNavigation=tbPageNavigationService.findById(id);
        tbPageNavigation.setStState((byte)1);
        tbPageNavigationService.save(tbPageNavigation);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
