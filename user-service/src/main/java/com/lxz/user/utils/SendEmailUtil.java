package com.lxz.user.utils;/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/4/6/9:38
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.user.utils
 * 文件描述: @Description: 发送邮件api
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/


import com.lxz.user.vo.ResultVO;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 包名称： com.lxz.user.utils
 * 类名称：SendEmailUtil
 * 类描述：发送邮件api
 * 创建人：@author xxxxx
 * 创建时间：2021/4/6/9:38
 */
@Component
public class SendEmailUtil{
    //邮件服务器主机地址
    private static String HOST="smtp.qq.com";
    //帐号
    private static String ACCOUNT = "736603914@qq.com";
    //密码
    private static String PASSWORD = "echvwpaeltinbefb";

    /**
     * @param toUser  发送邮件给谁
     * @param title   邮件的标题
     * @param emailMsg  邮件信息
     */
    public ResultVO sendMail(String toUser, String title, String emailMsg)   {
        ResultVO resultVO = new ResultVO();
        // 1.创建一个程序与邮件服务器会话对象 Session
        Properties props = new Properties();
        //设置发送的协议
        props.setProperty("mail.transport.protocol", "SMTP");

        //设置发送邮件的服务器
        props.setProperty("mail.host", HOST);
        // 指定验证为true
        props.setProperty("mail.smtp.auth", "true");

        try {

            // 创建验证器
            Authenticator auth = new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    //设置发送人的帐号和密码      帐号		   授权码
                    return new PasswordAuthentication(ACCOUNT, PASSWORD);
                }
            };
            //会话
            Session session = Session.getInstance(props, auth);

            // 2.创建一个Message，它相当于是邮件内容
            Message message = new MimeMessage(session);

            //设置发送者
            message.setFrom(new InternetAddress(ACCOUNT));

            //设置发送方式与接收者
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toUser));

            //设置邮件主题
            message.setSubject(title);
//            message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

            //设置邮件内容
            message.setContent(emailMsg, "text/html;charset=utf-8");

            // 3.创建 Transport用于将邮件发送
            Transport.send(message);
        }catch (MessagingException e){
            resultVO.setSuccess(false);
            resultVO.setMessage("发送失败："+e.toString());
            return resultVO;
        }
        resultVO.setSuccess(true);
        resultVO.setMessage("发送成功");
        return  resultVO;
    }
}
