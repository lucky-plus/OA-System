package com.oa.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtil {
	
	private static Properties props = new Properties();
	private static Session session;
	private static MimeMessage message; 
	
	static {
		props.put("mail.smtp.host", "smtp.qq.com");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		
		session = Session.getDefaultInstance(props);
		session.setDebug(true);
		message	 = new MimeMessage(session);
	}
	
	public static void sendMessage(String toAddr,String subject,String content) throws MessagingException, IOException {
        /*
         * 也可以根据已有的eml邮件文件创建 MimeMessage 对象
         * MimeMessage message = new MimeMessage(session, new FileInputStream("MyEmail.eml"));
         */

        // 2. From: 发件人
        //    其中 InternetAddress 的三个参数分别为: 邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
        //    真正要发送时, 邮箱必须是真实有效的邮箱。
        message.setFrom(new InternetAddress("2651416933@qq.com"));

        // 3. To: 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toAddr, "USER_CC", "UTF-8"));
        
        // 4. Subject: 邮件主题
        message.setSubject(subject, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(content, "text/html;charset=UTF-8");

        // 6. 设置显示的发件时间
        message.setSentDate(new Date());

        // 7. 保存前面的设置
        message.saveChanges();

        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.qq.com","2651416933@qq.com","cgrdwatucmofdjbf");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
	}
	
	public void testEmailWithFile() throws MessagingException, IOException {
		
        /*
         * 也可以根据已有的eml邮件文件创建 MimeMessage 对象
         * MimeMessage message = new MimeMessage(session, new FileInputStream("MyEmail.eml"));
         */

        // 2. From: 发件人
        //    其中 InternetAddress 的三个参数分别为: 邮箱, 显示的昵称(只用于显示, 没有特别的要求), 昵称的字符集编码
        //    真正要发送时, 邮箱必须是真实有效的邮箱。
        message.setFrom(new InternetAddress("934789892@qq.com"));

        // 3. To: 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("569613942@qq.com", "USER_CC", "UTF-8"));
        
        // 4. Subject: 邮件主题
        message.setSubject("加楷超级大煞笔", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        // message.setContent("大傻逼大傻逼", "text/html;charset=UTF-8");
        // 
        
        // 6. 设置显示的发件时间
        message.setSentDate(new Date());

        // 8. 文件附件
        // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent("你好啊", "text/html;charset=UTF-8");
       
        
        BodyPart bodyPart = new MimeBodyPart();
        //        		new FileInputStream(
        //        				new File("C://Users//Administrator//Desktop//新建文本文档 (2).txt")));
        DataSource source = new FileDataSource("C://Users//Administrator//Desktop//新建文本文档 (2).txt");
        bodyPart.setDataHandler(new DataHandler(source));
        bodyPart.setFileName(MimeUtility.encodeWord("逗逼.txt"));
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(contentPart);
        multipart.addBodyPart(bodyPart);
        message.setContent(multipart);
        
        // 7. 保存前面的设置
        message.saveChanges();
        
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.qq.com","934789892@qq.com","asdasdasdasd");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
	}
	
	public void testEmailTakeHtml() throws AddressException, MessagingException, UnsupportedEncodingException {
        message.setFrom(new InternetAddress("2651416933@qq.com"));

        // 3. To: 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("598959863@qq.com", "USER_CC", "UTF-8"));
        
        // 4. Subject: 邮件主题
        message.setSubject("加楷超级大煞笔", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        // message.setContent("大傻逼大傻逼", "text/html;charset=UTF-8");
        // 
        
        // 6. 设置显示的发件时间
        message.setSentDate(new Date());

        // 8. 文件附件
        // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        // 负责添加正文
        // contentPart.setContent("你好啊", "text/html;charset=UTF-8");
        DataSource htmlContext = new FileDataSource("C://Users//Administrator//Desktop//仿LayoutUI//test10.html");
        contentPart.setDataHandler(new DataHandler(htmlContext));
        
        BodyPart bodyPart = new MimeBodyPart();
        //        		new FileInputStream(
        //        				new File("C://Users//Administrator//Desktop//新建文本文档 (2).txt")));
        DataSource source = new FileDataSource("C://Users//Administrator//Desktop//新建文本文档 (2).txt");
        bodyPart.setDataHandler(new DataHandler(source));
        bodyPart.setFileName(MimeUtility.encodeWord("逗逼.txt"));
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(contentPart);
        multipart.addBodyPart(bodyPart);
        message.setContent(multipart);
        
        // 7. 保存前面的设置
        message.saveChanges();
        
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.qq.com","2651416933@qq.com","tfyklqfxqnrwecaa");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
	}
}
