package com.wonders.itemstaffmanage.service.impl;

import com.wonders.itemstaffmanage.service.MailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
public class MailServiceImpl implements MailService {
    private final static Logger log = LogManager.getLogger(MailServiceImpl.class);

    @Value("${spring.mail.username}")
    private String fromUser;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendTextMail(String toUser, String subject, String content) {
        log.info("开始发送静态文本邮件:接收人{}", toUser);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(subject);
            message.setTo(toUser);
            message.setText(content);
            message.setFrom(fromUser);
            mailSender.send(message);
            log.info("恭喜,邮件发送成功");
        } catch (Exception e) {
            log.error("邮件发送失败", e);
        }
    }

    @Override
    public void sendHtmlMail(String toUser, String subject, String content) {
        log.info("开始发送静态Html邮件:接收人{}", toUser);
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromUser);
            helper.setTo(toUser);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
            log.info("恭喜,邮件发送成功");
        } catch (Exception e) {
            log.error("邮件发送失败", e);
        }
    }


    @Override
    public void sendAttachmentMail(String toUser, String subject, String content,
                                   List<String> filePaths) {
        log.info("开始发送带附件静态邮件:接收人{}", toUser);
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toUser);
            helper.setFrom(fromUser);
            helper.setSubject(subject);
            helper.setText(content, true);

            if (filePaths != null && filePaths.size() > 0) {
                for (String filePath : filePaths) {
                    FileSystemResource file = new FileSystemResource(new File(filePath));
                    System.out.println(file.getFilename());
                    helper.addAttachment(file.getFilename(), file);
                }
            }
            mailSender.send(message);
            log.info("恭喜,邮件发送成功");
        } catch (Exception e) {
            log.error("邮件发送失败", e);
        }
    }

    @Override
    public void sendImgInlineMail(String toUser, String subject, String content,
                                  List<String> imgUrlList) {
        log.info("开始发送带图片静态邮件:接收人{}", toUser);
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toUser);
            helper.setFrom(fromUser);
            helper.setSubject(subject);
            helper.setText(content, true);

            if (imgUrlList != null && imgUrlList.size() > 0) {
                for (String url : imgUrlList) {
                    FileSystemResource file = new FileSystemResource(new File(url));
                    helper.addInline("", file);
                }
            }
            mailSender.send(message);
            log.info("恭喜,邮件发送成功");
        } catch (Exception e) {
            log.error("邮件发送失败", e);
        }
    }

}
