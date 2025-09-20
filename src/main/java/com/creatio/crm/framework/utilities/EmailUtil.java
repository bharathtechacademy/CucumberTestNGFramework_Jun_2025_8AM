package com.creatio.crm.framework.utilities;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Multipart;

public class EmailUtil {
	
	public static void sendEmailWithAttachment(String to, String subject, String body, String attachmentPath) {		
		System.out.println("Sending email to: " + to);
		System.out.println("Subject: " + subject);
		System.out.println("Body: " + body);
		System.out.println("Attachment: " + attachmentPath);
		
		// Senders email configuration (Email & APP Password)
		final String from = "bharathtechacademy5@gmail.com";
		final String password ="jwcauyixbdraqfks";
		
		// Setup GMail SMTP properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		
		// Get the Session object and authenticate
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		
		// Code to compose the email and send it with attachment would go here
		
		//create a default message object
		Message message = new MimeMessage(session);
		
		try {
			// Set From: header field
			message.setFrom(new InternetAddress(from));
			
			// Set To: header field
			message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
			
			// Set Subject: header field
			message.setSubject(subject);
			
			// Prepare the message body and attachment using MimeBodyPart and Mime
			Multipart multipart = new MimeMultipart();
			
			// Set the body text
			MimeBodyPart emailBodyPart = new MimeBodyPart();
			emailBodyPart.setText(body);
			multipart.addBodyPart(emailBodyPart);
			
			// Set the attachment
			MimeBodyPart attachmentPart = new MimeBodyPart();
			attachmentPart.attachFile(attachmentPath);
			multipart.addBodyPart(attachmentPart);
			
			// Add all part of message to main message
			message.setContent(multipart);
			
			// Send message
			Transport.send(message);
			
			System.out.println("Email sent to [ "+to+" ] successfully with attachment.");
			
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
	public static void main(String[] args) {
		// Test the email sending functionality
		String to = "BharathTechAcademy@Gmail.com";
		String subject = "Test Email from Java Program with Attachment";
		String body = "Hi,\nThis is a test email sent from a Java program with attachment.";
		String attachmentPath = System.getProperty("user.dir")+"\\src\\test\\resources\\jmeter\\bin\\report-output\\index.html";
		sendEmailWithAttachment(to, subject, body, attachmentPath);
	}
		

}
