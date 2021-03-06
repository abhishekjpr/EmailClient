import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;



public class SentTable
{
	DefaultTableModel model;
	JTable displayMails;
	
	SentTable(JTable displayMails, DefaultTableModel model)
	{
		this.displayMails = displayMails;
		this.model = model;
		displayMails.selectAll();
    			int []rowNum = displayMails.getSelectedRows();
			System.out.println();
			for(int i=rowNum.length;i>0;i--)
			{
				model.removeRow(rowNum[i-1]);
			}
    			try {
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				String url = "jdbc:mysql://localhost:3306/emailclient?user=root&password=root";
				Connection con = DriverManager.getConnection(url);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("Select * FROM Sent");
	
				int record = 0;
				while(rs.next()) {
					record++;
				}
				int count = 0;
				
				rs = stmt.executeQuery("Select * FROM Sent");
				while(rs.next()) {
					count++;
					String from = rs.getString(1);
					String subject = rs.getString(2);
					String content = rs.getString(3);
					String date = rs.getString(4);
					model.addRow(new String[]{from, subject, content, date });
				}
			}
			catch(Exception ee) {
				System.out.println(ee.getMessage());
			}			
	}
}
