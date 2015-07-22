package com.silicom.utils;

import java.io.UnsupportedEncodingException;
import	java.util.Properties;	
import java.util.logging.Logger;

import	javax.mail.Message;	
import	javax.mail.MessagingException;	
import	javax.mail.Session;	
import	javax.mail.Transport;	
import	javax.mail.internet.AddressException;	
import	javax.mail.internet.InternetAddress;	
import	javax.mail.internet.MimeMessage;	

import com.silicom.cron.ResetBookedTables;
import com.silicon.entities.User;

public class EmailSender {

	private static final Logger _logger = Logger
			.getLogger(EmailSender.class.getName());
	
	public static String sendEmail (User user) throws UnsupportedEncodingException{
		String msg = PropUtil.GP_OK;
		Properties props = new Properties();	
		Session	session	= Session.getDefaultInstance(props,	null);	
		try	{	
			Message	email	= new MimeMessage(session);	
			email.setFrom(new	InternetAddress("notreply@com-silicon-letseat.appspotmail.com",	"Let's Eat Servicios"));	
			email.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail(), user.getName()));	
			email.setSubject("Recuperacion de su Clave Let's Eat");	
			email.setText("Su usuario: "+ user.getName() +", tiene asociada la clave de acceso: " +user.getPass());	
			Transport.send(email);
			_logger.info("Email enviado con exito a: "+user.getName());
		} catch	(AddressException e) {	
			msg = PropUtil.GP_E_IE;
			_logger.severe("Email no enviado. Mensaje:"+e.toString());
		} catch	(MessagingException	e) {
			_logger.severe("Email no enviado. Mensaje:"+e.toString());
		}
		return msg;
	}
}
