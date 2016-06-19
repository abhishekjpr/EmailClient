import java.util.*;
import java.util.Date;

import javax.mail.*;
import javax.mail.internet.*;
import java.sql.*;

import javax.swing.*;

public class SendMail
{
	public SendMail(String r, String s, String m, JFrame frame)
	{
		String from="ur_emailid";
		String pwd="ur_password";
		String to= r;
		String sub=s;
		String body=m;

		Message msg;

		Properties props = new Properties();
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port", "465"); // default port 25
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.debug", "false");

		Session session = Session.getDefaultInstance(props,new SimpleMailAuthenticator(from,pwd));
				  //Session.getDefaultInstance(Properties, Authenticator);

		try
		{
			msg=new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
			msg.setSubject(sub);
			msg.setText(body);
			
			Transport.send(msg);
			try {
					DriverManager.registerDriver(new com.mysql.jdbc.Driver());
					String url = "jdbc:mysql://localhost:3306/emailclient?user=root&password=root";
					Connection con = DriverManager.getConnection(url);
					PreparedStatement pstmt = con.prepareStatement("Insert into sent values(?, ?, ?, ?)");
					
					pstmt.setString(1, to);
					pstmt.setString(2, sub);
					pstmt.setString(3, body);
					
					Date date = new Date();
					
					pstmt.setString(4, date.toString());
				//	pstmt.setString(4, " ");
					pstmt.executeUpdate();
				}		
				catch(Exception ee) {
					System.out.println(ee.getMessage());
				}
			frame.dispose();
			JOptionPane.showMessageDialog(frame, "Message Successfully Sent..!!", "Successfull", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(MessagingException me)
		{
			JOptionPane.showMessageDialog(frame, "Message Sending Failed..!!", "Unsuccessfull", JOptionPane.INFORMATION_MESSAGE);
				try {
				System.out.println("Hello");
				
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				String url = "jdbc:mysql://localhost:3306/emailclient?user=root&password=root";
				Connection con = DriverManager.getConnection(url);
				PreparedStatement pstmt = con.prepareStatement("Insert into outbox values(?, ?, ?, ?)");
				
				pstmt.setString(1, r);
				pstmt.setString(2, s);
				pstmt.setString(3, m);
				Date date = new Date();
				pstmt.setString(4, date.toString());
				
				pstmt.executeUpdate();	
				frame.dispose();
				JOptionPane.showMessageDialog(frame, "Message Saved To Outbox..!!");
			}		
			catch(Exception ee) {
				JOptionPane.showMessageDialog(frame, "Invalid Operation..!!");
			}
		}
	}
/*	public static void main(String[] args)	
	{	
		new SendMail();
	}*/
}
