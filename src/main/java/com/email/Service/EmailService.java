package com.email.Service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;



///this service (method ) to send the email by passing  the data(subject,message,to)
///we pass the data in it by getting data from email_request class object
///then we use its object in controller to send collected data from email_request 
@Service//best than @component
public class EmailService {

	
	
	
	//sending the simple text with mail....................................................(way-1)
    
	///this is responsible to sent mail with simple text...
	 
	public boolean sendEmail(String subject,String message,String to) {
		
		
		///rest of code here  form email sending method.....
	 
		boolean f= false;
		
        String from="npcoder0@gmail.com";

		
		
			///variable for host
			///host(server) to send the mail
			String host="smtp.gmail.com";
			
			
			///get the system properties
			     Properties properties = System.getProperties();
			     System.out.println("PROPERTIES " +properties);
			     
			     ///setting the imp information to properties 
			     
			     //host set
			     properties.put("mail.smtp.host", host);
			     properties.put("mail.smtp.port", "465");
			     properties.put("mail.smtp.ssl.enable", "true");
			     properties.put("mail.smtp.auth", "true");

			     
			     
	  // step #1 get the session object........................................................
			     
			     
			     Session session = Session.getInstance(properties,  new Authenticator() {

					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						// TODO Auto-generated method stub
						return new PasswordAuthentication("npcoder0@gmail.com","lhnt riqt fotr cerj");
					}
			    	 ///(lhnt riqt fotr cerj) - this is my password allow the our project to use our mail(sender)
				});
			     
			     session.setDebug(true);
			     
			     
			     
			     
			     
	//step #2 compose the message [text,multi_media].............................................
			  MimeMessage m  =new MimeMessage(session);
			     
			     try {
			    	 
			    	 ///form email
			    	 m.setFrom(from);
			    	 ///adding recipient to message
			    	 m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			    	 ///adding subject to message
			    	 m.setSubject(subject);
			    	 ///adding text to message
			    	m.setText(message);
			    	
			    	
	//step #3 sending the message using transport class.................................
			    	Transport.send(m);
			    	
			    	System.out.println("message with simple text is send the successfully");
			    	
			    	f=true;
			    	
			     }catch(Exception e){
			    	 e.printStackTrace();
			    	 
			    	 
			     }
			     
		return f;
		
	}
}
