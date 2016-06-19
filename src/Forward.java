import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;


public class Forward implements ActionListener {
	JTextField recipient, subject;
	JTextArea message;
	JButton save, send, cancel;
	JFrame frame;
	JFrame fr;
	public Forward(String to, String sub, String body, JFrame framee) {
		if(sub.equals("") | body.equals("") ) {
			JOptionPane.showMessageDialog(framee, "Please Select The Mails First..", "Information", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			fr = framee;
			frame = new JFrame();
			frame.setBounds(550, 150, 700, 400);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setLayout(new GridBagLayout());
			
			GridBagConstraints gbc = new GridBagConstraints();
			
			JLabel toLabel = new JLabel("To");
			gbc.gridx = 0;			gbc.gridy = 0;
			gbc.weightx = 1.0;		gbc.weighty = 1.0;
			frame.add(toLabel, gbc);
			
			recipient = new JTextField(20);
			recipient.setText(to);
			gbc.gridx = 1;
			frame.add(recipient, gbc);
			
			JLabel subjectLabel = new JLabel("Subject");
			gbc.gridx = 0;		gbc.gridy = 1;
			frame.add(subjectLabel, gbc);
			
			subject = new JTextField(20);
			subject.setText(sub);
			gbc.gridx = 1;
			frame.add(subject, gbc);
			
			JLabel messageLabel = new JLabel("  Type Your Message Here : ");
			gbc.gridx = 1;		gbc.gridy = 2;
			frame.add(messageLabel, gbc);
			
			message = new JTextArea(10, 30);
			message.setText(body);
			
			JScrollPane jsp = new JScrollPane(message);
			gbc.gridy = 4;			gbc.gridx = 1;	
			frame.add(jsp, gbc);
			
			send = new JButton("Send");						send.addActionListener(this);
			gbc.gridy = 7;			gbc.gridx = 0;
			frame.add(send, gbc);
			
			save = new JButton("Save");						save.addActionListener(this);
			gbc.gridx = 1;
			frame.add(save, gbc);
			
			cancel = new JButton("Cancel");					cancel.addActionListener(this);
			gbc.gridx = 2;
			frame.add(cancel, gbc);
			
			try
			        {
			            UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[1].getClassName());
			            SwingUtilities.updateComponentTreeUI(frame);
			        }
			        catch(Exception e)
			        {
			            e.printStackTrace();
			        }
			
			frame.setVisible(true);
		}
	}
/*	public static void main(String[] args) {
		new Forward();
	}
*/	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("Send")) {
			String r = recipient.getText().trim();
			String s = subject.getText().trim();
			String m = message.getText().trim();
			new SendMail(r, s, m, fr);
		}
		else if(command.equals("Cancel")) {
			frame.dispose();
		}
		else if(command.equals("Save")) {
			System.out.println("Forward Save..!!");
			try {
				System.out.println("Hello");
				String to = recipient.getText();
				String sub = subject.getText();
				String body = message.getText();
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				String url = "jdbc:mysql://localhost:3306/emailclient?user=root&password=root";
				Connection con = DriverManager.getConnection(url);
				PreparedStatement pstmt = con.prepareStatement("Insert into drafts values(?, ?, ?, ?)");
				
				pstmt.setString(1, to);
				pstmt.setString(2, sub);
				pstmt.setString(3, body);
				Date date = new Date();
				
				pstmt.setString(4, date.toString());
				pstmt.executeUpdate();	
				frame.dispose();
				JOptionPane.showMessageDialog(frame, "Message Saved To Drafts..!!");
			}		
			catch(Exception ee) {
				JOptionPane.showMessageDialog(frame, "Invalid Operation..!!");
			}
		}
	}
}