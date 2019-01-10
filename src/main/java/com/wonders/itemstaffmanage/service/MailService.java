package com.wonders.itemstaffmanage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface MailService {


    /**
     * 发送文本邮件
     *
     * @param toUser  接收人
     * @param subject 主题
     * @param content 文本正文
     * @return void
     * @author sunxiaodong
     * @date 2018/11/7 15:46
     */
    void sendTextMail(String toUser, String subject, String content);

    /**
     * 发送Html邮件
     *
     * @param toUser  接收人
     * @param subject 主题
     * @param content Html正文
     * @return void
     * @author sunxiaodong
     * @date 2018/11/7 15:46
     */
    void sendHtmlMail(String toUser, String subject, String content);


    /**
     * 发送带附件的邮件
     *
     * @param toUser    接收人
     * @param subject   主题
     * @param content   附件邮件内容
     * @param filePaths 多个文件路径
     * @return void
     * @author sunxiaodong
     * @date 2018/11/7 15:46
     */
    void sendAttachmentMail(String toUser, String subject, String content,
                            List<String> filePaths);

    /**
     * 发送带图片的邮件
     *
     * @param toUser     接收人
     * @param subject    主题
     * @param content    附件邮件内容
     * @param imgUrlList 多个图片路径及ID集合
     * @return void
     * @author sunxiaodong
     * @date 2018/11/7 15:46
     */
    void sendImgInlineMail(String toUser, String subject, String content,
                           List<String> imgUrlList);

}
