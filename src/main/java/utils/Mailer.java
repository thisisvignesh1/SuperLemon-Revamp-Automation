package utils;

/**
 * @author Sumit
 * Nov 2, 2021
 */
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@SuppressWarnings({ "unused" })
public class Mailer extends Listener {
	static String suiteName;
	public static String platform;
	private static String releaseVersion = System.getProperty("release");
	private static String testType = System.getProperty("testType");

	/**
	 * Method to parse the mail content
	 *
	 * @author Sumit
	 * @throws Exception
	 */
	public static void execute(String name) throws Exception {
		platform = System.getProperty("platformName");
		suiteName = "Gupshup "+name + "Report";
		String[] path = { System.getProperty("user.dir") + File.separator + "test-output" + File.separator
				+ "ExtentReport" + File.separator + "SuperLemon" + ".html" };
//		String[] to = { "usha.nanthini@gupshup.io" };
//		String[] to = { "praveen.kumar@gupshup.io", "usha.nanthini@gupshup.io" };
		String[] to = { "praveen.kumar@gupshup.io", "vijayalakshmi.r@gupshup.io", "pratiksha.nigade@gupshup.io",
				"usha.nanthini@gupshup.io" };
//		String[] to = { "praveen.kumar@gupshup.io", "vijayalakshmi.r@gupshup.io", "pratiksha.nigade@gupshup.io",
//				"aravind.krishnankutty@gupshup.io", "surendranath.chandranath@gupshup.io",
//				"preetham.umarani@gupshup.io", "jayanthi.naik@gupshup.io","usha.nanthini@gupshup.io" };

		String[] cc = {};
		String[] bcc = {};
		Mailer.sendMail("usha.nanthini@gupshup.io", "gwuwffbwnbkctvhr", "smtp.gmail.com", "465", "true", "true", false,
				"javax.net.ssl.SSLSocketFactory", "false", to, cc, bcc, suiteName,
				"Attached html file and xls file contains the test result status", path);
	}

	/**
	 * Method to send the mail
	 *
	 * @author Gokul S
	 * @throws Exception
	 */
	public static boolean sendMail(String userName, String passWord, String host, String port, String starttls,
			String auth, boolean debug, String socketFactoryClass, String fallback, String[] to, String[] cc,
			String[] bcc, String subject, String text, String[] attachmentPath) {
		// Object Instantiation of a properties file.
		Properties props = new Properties();
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.host", host);
		if (!"".equals(port)) {
			props.put("mail.smtp.port", port);
		}
		if (!"".equals(starttls)) {
			props.put("mail.smtp.starttls.enable", starttls);
			props.put("mail.smtp.auth", auth);
		}
		if (debug) {
			props.put("mail.smtp.debug", "true");
		} else {
			props.put("mail.smtp.debug", "false");
		}
		if (!"".equals(port)) {
			props.put("mail.smtp.socketFactory.port", port);
		}
		if (!"".equals(socketFactoryClass)) {
			props.put("mail.smtp.socketFactory.class", socketFactoryClass);
		}
		if (!"".equals(fallback)) {
			props.put("mail.smtp.socketFactory.fallback", fallback);
		}
		try {
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(debug);
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(userName));
			msg.setSubject(subject);
			Multipart multipart = new MimeMultipart();
			if (attachmentPath != null && attachmentPath.length > 0) {
				for (String filePath : attachmentPath) {
					{
						MimeBodyPart attachPart = new MimeBodyPart();
						try {
							attachPart.attachFile(filePath);
						} catch (IOException ex) {
							ex.printStackTrace();
						}
						multipart.addBodyPart(attachPart);
					}
				}
			}
			BodyPart htmlBodyPart = new MimeBodyPart();
			String htmlMessageAsString = "<p>Hi Team,</p><p>New test build triggered!</p> <p>Attached " + suiteName
					+ " html file and the test result status</p> <body>" + "<table cellspacing=0 cellpadding=3 border=1>"
					+ "<tr>" + "<th style=background-color:#dddddd >Test Suite</th>"
					+ "<th style=background-color:#dddddd>Passed</th>"
					+ "<th style=background-color:#dddddd >Failed</th>"
					+ "<th style=background-color:#dddddd>Skipped</th>"
					+ "<th style=background-color:#dddddd>Start<br>Time</th>"
					+ "<th style=background-color:#dddddd>End<br>Time</th>"
					+ "<th style=background-color:#dddddd>Total Time<br>(hh:mm:ss:SSS)</th>"
					+ "<th style=background-color:#dddddd>Platform<br>Executed</th>" + "</tr>" + "<tr>" + "<td><b>"
					+ suiteName + "</b></td>" + "<td>" + Listener.Testcase_passed + "</td>" + "<td>"
					+ Listener.Testcase_failed + "</td>" + "<td>" + Listener.Testcase_skipped + "</td>" + "<td>"
					+ DriverBase.starttime + "</td>" + "<td>" + DriverBase.endtime + "</td>" + "<td>"
					+ DriverBase.executiontime + "</td>" + "<td>" + platform + "</td>" + "</tr>" + "<tr>"
					+ "<td><b>Total</b></td>" + "<td>" + Listener.Testcase_passed + "</td>" + "<td>"
					+ Listener.Testcase_failed + "</td>" + "<td>" + Listener.Testcase_skipped + "</td>" + "<td></td>"
					+ "<td></td>" + "<td>" + DriverBase.executiontime + "</td>" + "</tr>" + "</table>"
					+ "</body><p> </p> <p> </p> <p> </p> <p>Regards,  </p><p>Gupshup QA</p>";
			htmlBodyPart.setContent(htmlMessageAsString, "text/html");
			multipart.addBodyPart(htmlBodyPart);
			msg.setContent(multipart);
			for (int i = 0; i < to.length; i++) {
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			for (int i = 0; i < cc.length; i++) {
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc[i]));
			}
			for (int i = 0; i < bcc.length; i++) {
				msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc[i]));
			}
			msg.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect(host, userName, passWord);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			return true;
		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
	}
}