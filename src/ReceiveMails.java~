import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;

public class ReceiveMails
{
	public ReceiveMails(JTable displayMails, DefaultTableModel model, JFrame frame) //throws IOException
	{
		String username = "ur_emailid";
		String password = "ur_password";

		try
		{
			Properties props = new Properties();
			props.put("mail.imap.host", "imap.gmail.com");
			props.put("mail.imap.port", "993");// default port 143
			props.put("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
//			props.put("mail.pop3.host", "pop.gmail.com");
//			props.put("mail.pop3.port", "995");// default port 110
//			props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 

			Session session = Session.getInstance(props,null);

//			Store store = session.getStore("pop3");
			Store store = session.getStore("imap");
			store.connect(username,password);

			Folder inboxFolder = store.getFolder("Inbox");
			inboxFolder.open(Folder.READ_ONLY);

			Message[] arr = inboxFolder.getMessages();

			System.out.println("No of Message : "+arr.length);
			for(int i=0; i<arr.length;i++)
			{
				System.out.println("\n--------------------------Message"+(i+1)+"--------------------------");
			//	arr[i].writeTo(System.out);
				Address[] from = arr[i].getFrom();
				System.out.println("From : " + from[0]);
				System.out.println("Subject : " + arr[i].getSubject());
				System.out.println("Date : " + arr[i].getSentDate());
				System.out.println("Message : " + arr[i].getContent());	
				try {
					DriverManager.registerDriver(new com.mysql.jdbc.Driver());
					String url = "jdbc:mysql://localhost:3306/emailclient?user=root&password=root";
					Connection con = DriverManager.getConnection(url);
					PreparedStatement pstmt = con.prepareStatement("Insert into inbox values(?, ?, ?, ?)");
					
					pstmt.setString(1, from[0]+"");
					pstmt.setString(2, arr[i].getSubject()+"");
					pstmt.setString(3, arr[i].getContent()+"");
					pstmt.setString(4, arr[i].getSentDate()+"");
					pstmt.executeUpdate();	
				}		
				catch(Exception ee) {
					System.out.println(ee.getMessage());
				}
			}
			JOptionPane.showMessageDialog(frame, "All message Loaded...", "Successfull", JOptionPane.INFORMATION_MESSAGE);
			inboxFolder.close(true);
			store.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
