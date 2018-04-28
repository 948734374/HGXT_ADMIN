package com.mcfish.util.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.mcfish.entity.common.TbSystemSmtp;
import com.mcfish.service.common.ISystemService;

/**
 * 发送邮件
 * 
 * @package com.share.util.email
 * @description
 * @author shangfei
 * @date 2017年12月14日
 *
 */
@Component
public class SimpleSSLMail {

	// 日志记录
	private static Logger log = Logger.getLogger(SimpleSSLMail.class);

	// 服务器地址
	private static String SMTP_HOST_NAME = "";
	// 端口号
	private static int SMTP_HOST_PORT = 000;
	// 企业邮箱授权账号
	private static String SMTP_AUTH_USER = "";
	// 授权账号密码
	private static String SMTP_AUTH_PWD = "";
	// 发送方账号
	private static String Address = "";

	@Autowired
	private ThreadPoolTaskExecutor sendMessagePool;

	@Autowired
	protected ISystemService systemService;

	
	/**
	 * 发送邮件
	 * @param corpid 企业授权码
	 * @param subject 邮件主题
	 * @param text 邮件内容
	 * @param sendTo 接收方邮箱 多个邮箱用;分割
	 * @throws Exception
	 * @throws Exception
	 */
	public void sendEmail(String corpid, String subject, String text, String sendTo) {
		sendMessagePool.execute(new Runnable() {
			@SuppressWarnings("unused")
			public void run() {
				// 获取企业账户信息
				TbSystemSmtp ts = new TbSystemSmtp();
				if (ts == null) {
					log.error("\r\n发送邮件失败;服务器邮箱未设置：" + corpid + " \r\n ");
				}
				SMTP_HOST_NAME = ts.getHostName();
				SMTP_HOST_PORT = Integer.parseInt(ts.getHostPort());
				SMTP_AUTH_USER = ts.getAuthUser();
				SMTP_AUTH_PWD = ts.getAuthPwd();
				Address = ts.getAddress();

				Properties props = new Properties();
				try {
					props.put("mail.transport.protocol", "smtps");
					props.put("mail.smtps.host", SMTP_HOST_NAME);
					props.put("mail.smtps.auth", "true");
					// props.put("mail.smtps.quitwait", "false");

					Session mailSession = Session.getInstance(props, new Authenticator() {
						public PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
						}
					});
					mailSession.setDebug(false);
					Transport transport = mailSession.getTransport();

					MimeMessage message = new MimeMessage(mailSession);
					if (subject == null) {
						message.setSubject("【McAdmin】");
					} else {
						message.setSubject(subject);
					}
					message.setContent(text, "text/html;charset=GBK");
					message.setFrom(new InternetAddress(Address));
					message.saveChanges();

					// 多邮箱发送
					String[] emails = sendTo.split(";");
					String succ = "";

					sendEmails: for (int i = 0; i < emails.length; i++) {
						// 判断非空
						if (emails[i] == null || "".equals(emails[i])) {
							continue sendEmails;
						}
						try {
							message.addRecipient(Message.RecipientType.TO, new InternetAddress(emails[i], null));
							transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
							transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
							transport.close();
							succ = succ + emails[i] + ";";
						} catch (Exception e) {
							log.error("\r\n发送邮件失败;邮箱：" + emails[i] + " \r\n " + "异常信息--》" + e);
							message = new MimeMessage(mailSession);
							if (subject == null) {
								message.setSubject("【McAdmin】");
							} else {
								message.setSubject(subject);
							}
							message.setContent(text, "text/html;charset=GBK");
							message.setFrom(new InternetAddress(Address));
							message.saveChanges();
						}
					}
					// 记录发送成功记录
					if (!"".equals(succ)) {
						log.info("\r\n发送邮件成功;邮箱：" + succ);
					}

				} catch (Exception e) {
					log.error("\r\n发送邮件失败;邮箱：" + sendTo + " \r\n " + "发送主题：" + subject + "\r\n " + "发送内容：" + text
							+ "\r\n " + "异常信息--》" + e);
				}

			}
		});
	}

}