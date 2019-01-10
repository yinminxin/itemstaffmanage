package com.wonders.itemstaffmanage;

import com.wonders.itemstaffmanage.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSendTextMail() {
        String content = " 您有新的任务等待处理:前往http://10.1.64.132:9001/index";
        mailService.sendTextMail("yinminxin@wondersgroup.com", "任务管理系统", content);
    }

    @Test
    public void testSendHtmlMail() {


        String content = " <html> \n " +
                "<body> \n" +
                "<h3> Hello World </h3> \n" +
                "<span> 恭喜您已经注册成功 ,请" +
                "<a href = http://www.baidu.com >点击激活</a></span> \n" +
                "<img src='http://smartcampus.eduincloud.net/video/youhua/img/bnjwl001.jpg' width='100px' height='100px'>" +
                "<img src='http://smartcampus.eduincloud.net/video/youhua/img/bnjwl002.jpg' width='100px' height='100px' >" +
                "</body> \n" +
                "</html>";

        mailService.sendHtmlMail("yinminxin@wondersgroup.com", "第一封Html邮件", content);
    }

    @Test
    public void testSendAttachmentMail()  {

        String content = "这是一封带多附件的邮件";
        List<String> filePaths = new ArrayList<>();
        filePaths.add("D:\\workspace\\wanda\\smartcampus\\src\\main\\webapp\\resource\\收费表格模版.xlsx");
        filePaths.add("D:\\workspace\\wanda\\smartcampus\\src\\main\\webapp\\resource\\教师资格认定申请表.doc");
        filePaths.add("C:/Users/sxd19/Desktop/智慧校园/教师资格/教师资格办事指南-高校.doc");
        filePaths.add("C:/Users/sxd19/Desktop/智慧校园/教师资格/教师资格证.zip");
//        filePaths.add("D:\\workspace\\wanda\\smartcampus\\src\\main\\webapp\\resource\\高级教师资格认定申请表.doc");

        mailService.sendAttachmentMail("yinminxin@wondersgroup.com", "第一封附件邮件", content, filePaths);
    }

    @Test
    public void testSendImgIlineMail()  {

        List<String> imageUrlList = new ArrayList<>();
        imageUrlList.add("C:/Users/sxd19/Pictures/Camera Roll/610525200002021914.jpg");
        imageUrlList.add("C:/Users/sxd19/Pictures/Camera Roll/310105199901012323.jpg");

        String subject = "第一封带图片邮件";
        String toUser = "yinminxin@wondersgroup.com";
        String content = " <html> \n " +
                "<body> \n" +
                "<h3> Hello World </h3> \n";

        for (String url : imageUrlList) {
            content += "<img src='" + url + "'>";
        }
        content += "</body> \n" + "</html>";
        mailService.sendImgInlineMail(toUser, subject, content, imageUrlList);
    }

    @Test
    public void testSendTemplateMail()  {
        Context context = new Context();
        context.setVariable("accountId","8a828ef16261cf4d016261db6a390003");
        context.setVariable("username","17621785681");
        String mailTemplate = templateEngine.process("mailTemplate", context);

        mailService.sendHtmlMail("yinminxin@wondersgroup.com", "第一封模板邮件", mailTemplate);
    }

}
