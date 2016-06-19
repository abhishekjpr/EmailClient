import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;


public class OutboxTable
{
	DefaultTableModel model;
	JTable displayMails;
	
	OutboxTable(JTable displayMails, DefaultTableModel model)
	{
	//	System.out.println("Outbox Invoked..!!!");
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
				ResultSet rs = stmt.executeQuery("Select * FROM Outbox");
	
				int record = 0;
				while(rs.next()) {
					record++;
				}
				int count = 0;
				
				rs = stmt.executeQuery("Select * FROM Outbox");
				while(rs.next()) {
					count++;
					String from = rs.getString(1);
					String subject = rs.getString(2);
					String content = rs.getString(3);
					String date = rs.getString(4);
		//			System.out.println(from+"   "+subject+"   "+content+"   "+date);
					model.addRow(new String[]{from, subject, content, date });
		//			System.out.println("Hello");
				}
			}
			catch(Exception ee) {
				System.out.println(ee.getMessage());
			}			
	}
}
