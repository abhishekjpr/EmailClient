import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import java.sql.*;


public class EmailClient implements ActionListener, TreeSelectionListener, ListSelectionListener {
	
	private JFrame frame;
	String cv = "Hello";;
	private JButton button;
	JTree tree;
	String treeLabel = "";
	String getTitle = "";
	ArrayList<String> str ;
	String sub="", body="", to = "";
	boolean inboxCheck = false, outboxCheck = false, sentCheck = false, trashCheck = false;
	DefaultMutableTreeNode topTreeNode, inbox, outbox, sentMails, trash, drafts;
	JTree mainTree;
	JScrollPane treeScrollPane;
	JTextArea readMails;
	JTable displayMails;	
	JPanel read;
	DefaultTableModel model;
	
	public EmailClient() {
		str = new ArrayList<String>();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(300, 100, 800, 500);
			
		JPanel buttonPanel = new JPanel();
			try
   		     {
           			 UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[1].getClassName());
		            SwingUtilities.updateComponentTreeUI(buttonPanel);
		        }
		        catch(Exception e)
		        {
		            e.printStackTrace();
		        }


		buttonPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		JLabel lab= new JLabel("  ");		
		lab.setBackground(Color.white);
		gbc.gridy = 0;
		buttonPanel.add(lab, gbc);
		
		
		button = new JButton("Receive All");						button.addActionListener(this);
		gbc.gridx = 0;			gbc.gridy = 1;
		gbc.weightx = 1.0;		gbc.weighty = 1.0;
		buttonPanel.add(button, gbc);
			
		button = new JButton("Compose");							button.addActionListener(this);
		gbc.gridx = 1;
		buttonPanel.add(button, gbc);
		
		button = new JButton("Forward");							button.addActionListener(this);
		gbc.gridx = 2;			
		buttonPanel.add(button, gbc);
		
		button = new JButton("Delete");								button.addActionListener(this);
		gbc.gridx = 3;		
		buttonPanel.add(button, gbc);
		
		button = new JButton("Delete All");				//			button.addActionListener(this);
		gbc.gridx = 4;
		buttonPanel.add(button, gbc);
		
		button = new JButton("Restore");							button.addActionListener(this);
		gbc.gridx = 5;		
		buttonPanel.add(button, gbc);		
		
	/*	button = new JButton("Restore All");						button.addActionListener(this);
		gbc.gridx = 6;		
		buttonPanel.add(button, gbc);
	*/		
		lab = new JLabel("  ");		
		lab.setBackground(Color.white);
		gbc.gridy = 2;
		buttonPanel.add(lab, gbc);
		
		frame.add(buttonPanel);
		
	/************** Button Panel End ********************/
		
				
		JPanel twoPanel = new JPanel();
		try
   		     {
           			 UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[1].getClassName());
		            SwingUtilities.updateComponentTreeUI(twoPanel);
		        }
		        catch(Exception e)
		        {
		            e.printStackTrace();
		        }
		twoPanel.setLayout(new BorderLayout());
		
		JPanel internalPanelTree = new JPanel();
			try
   		     {
           			 UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[1].getClassName());
		            SwingUtilities.updateComponentTreeUI(internalPanelTree);
		        }
		        catch(Exception e)
		        {
		            e.printStackTrace();
		        }
		internalPanelTree.setLayout(new GridLayout(1, 0));
		
		topTreeNode = new DefaultMutableTreeNode("Mailbox");
			
		inbox = new DefaultMutableTreeNode("Inbox");
		outbox = new DefaultMutableTreeNode("Outbox");
		sentMails = new DefaultMutableTreeNode("Sent Mails          ");
		drafts = new DefaultMutableTreeNode("Drafts");
		trash = new DefaultMutableTreeNode("Trash");
		
		topTreeNode.add(inbox);		topTreeNode.add(outbox);		topTreeNode.add(sentMails);		topTreeNode.add(drafts);	topTreeNode.add(trash);		
		
		mainTree = new JTree(topTreeNode);
		mainTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		mainTree.addTreeSelectionListener(this);
		
		treeScrollPane = new JScrollPane(mainTree);
		internalPanelTree.add(treeScrollPane);
		
		
		JPanel readMailsPanel = new JPanel();
			try
   		     {
           			 UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[2].getClassName());
		            SwingUtilities.updateComponentTreeUI(readMailsPanel);
		        }
		        catch(Exception e)
		        {
		            e.printStackTrace();
		        }
		readMailsPanel.setLayout(new BorderLayout());
		
	/*********************************************************/	
		read = new JPanel();
			try
   		     {
           			 UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[1].getClassName());
		            SwingUtilities.updateComponentTreeUI(read);
		        }
		        catch(Exception e)
		        {
		            e.printStackTrace();
		        }
		read.setLayout(new BorderLayout());
		read.setPreferredSize(new Dimension(200, 300));
		displayMails = new JTable();
		displayMails.setDragEnabled(false);
		String columns[] = {"Sender", "Subject", "Content", "Date"};
		model = new MyDefaultTableModel(null, columns);
		displayMails = new JTable(model);
	//	displayMails.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane pane = new JScrollPane(displayMails);
			read.add(pane, BorderLayout.CENTER);
			displayMails.getSelectionModel().addListSelectionListener(this);
			read.setVisible(true);

//		read.add(displayMails);
	/*********************************************************/
	
	/*********************************************************/	
		JPanel display = new JPanel();
		display.setLayout(new BorderLayout());
			try
   		     {
           			 UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[2].getClassName());
		            SwingUtilities.updateComponentTreeUI(display);
		        }
		        catch(Exception e)
		        {
		            e.printStackTrace();
		        }
	
		readMails = new JTextArea();
		readMails.setEditable(false);
		JScrollPane displayPanel = new JScrollPane(readMails);
		display.add(readMails);
	/*********************************************************/
		
		JSplitPane rightPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, read, display);
		readMailsPanel.add(rightPane);
		
		JSplitPane totalPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, internalPanelTree, readMailsPanel);
		twoPanel.add(totalPane);
		
		JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPanel, twoPanel);
		
		try
   		     {
           			 UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[1].getClassName());
		            SwingUtilities.updateComponentTreeUI(frame);
		     }
		     catch(Exception e)
		     {
		            e.printStackTrace();
		     }
		frame.add(jsp);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new EmailClient();
	}
	public void valueChanged(TreeSelectionEvent e) {

    		DefaultMutableTreeNode node = (DefaultMutableTreeNode) mainTree.getLastSelectedPathComponent();
    		String nodeLabel = (String)node.getUserObject();
    		System.out.println(nodeLabel);
    		treeLabel = nodeLabel;
    		if(nodeLabel.equals("Inbox")) {
    			inboxCheck = true;
    			getTitle = "Inbox";
    			readMails.setText("");
    			new InboxTable(displayMails, model);
    		}
    		else if(nodeLabel.equals("Outbox")) {
    			outboxCheck = true;
    			getTitle = "Outbox";
//    			String name = displayMails.getColumnName(0);
  //  			System.out.println(name);
    			readMails.setText("");
    			new OutboxTable(displayMails, model);
    		}
    		else if(nodeLabel.equals("Sent Mails          ")) {
    			sentCheck = true;
    			getTitle = "Sent Mail";
    			readMails.setText("");
    			new SentTable(displayMails, model);
    		}
    		else if(nodeLabel.equals("Drafts")) {
    			outboxCheck = true;
    			getTitle = "Drafts";
//    			String name = displayMails.getColumnName(0);
  //  			System.out.println(name);
    			readMails.setText("");
    			new DraftsTable(displayMails, model);
    		}
    		else if(nodeLabel.equals("Trash")) {
    			trashCheck = true;
    			getTitle = "Trash";
    			readMails.setText("");
    			new TrashTable(displayMails, model);
    		}
    		cv = "Hello";
   	 }
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command.equals("Receive All")) {
			new ReceiveMails(displayMails, model, frame);
		//	valueChanged(new TreeSelectionEvent );
		}
		else if(command.equals("Forward")) {
			new Forward(to, sub, body, frame);
		}
		else if(command.equals("Compose")) {
			new Compose(frame);
		}
		else if(command.equals("Restore")) {
			if(displayMails.getSelectedRowCount() != 0) {
				try {
					int result = JOptionPane.showConfirmDialog(frame, "Are You Sure You Want To Restore Selected Mails ?", "Respond", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(result == JOptionPane.YES_OPTION)
					{
						int []rowNum = displayMails.getSelectedRows();
						
						ResultSet rs1 = null;
						String val = "", val1 = "", val2 = "", val3 = "";
						DriverManager.registerDriver(new com.mysql.jdbc.Driver());
						String url = "jdbc:mysql://localhost:3306/emailclient?user=root&password=root";
						Connection con = DriverManager.getConnection(url);
						for(int i=rowNum.length;i>0;i--)
						{
							System.out.println(rowNum[rowNum.length-1]);
							int row = displayMails.getSelectedRow();
								try {
									Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
									
									rs1 = stmt.executeQuery("Select * from Trash where date='"+val3+"'");
									val = (String)model.getValueAt(row, 0);
									val1 = (String)model.getValueAt(row, 1);	
									val2 = (String)model.getValueAt(row, 2);
									val3 = (String)model.getValueAt(row, 3);
									System.out.println(val+"	"+val1+"	"+val2+"	"+val3);
								}
								catch(Exception e1) {
									System.out.println(e1.getMessage());
								}
								try {
									Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
									//	System.out.println("Select * from Inbox where subject='"+val1+"'");
										PreparedStatement pstmt = null;
								//		if(getTitle.equals("Inbox"))
											pstmt = con.prepareStatement("Insert into inbox values(?, ?, ?, ?)");
							//			else if(getTitle.equals("Sent"))
								//			pstmt = con.prepareStatement("Insert into sent values(?, ?, ?, ?)");
										
										
					//					else if(getTitle.equals("Outbox"))
						//					pstmt = con.prepareStatement("Insert into outbox values(?, ?, ?, ?)");
							//			else if(getTitle.equals("Drafts"))
								//			pstmt = con.prepareStatement("Insert into drafts values(?, ?, ?, ?)");
										try {
									//		pstmt = con.prepareStatement("Insert into trash values(?, ?, ?, ?)");
											pstmt.setString(1, val);
											pstmt.setString(2, val1);
											pstmt.setString(3, val2);
											pstmt.setString(4, val3);
											pstmt.executeUpdate();	
											rs1 = stmt.executeQuery("Select * from Trash where date='"+val3+"'");
											while(rs1.next())
											{
												rs1.deleteRow();
											}
											model.removeRow(rowNum[i-1]);
										}		
										catch(Exception ee) {
											System.out.println(ee.getMessage());
										}
										
								}
								catch(Exception e1) {
									System.out.println(e1.getMessage());
								}
							}
						}
					}
				catch(Exception op)
				{
					
				}
			}
			else{
				JOptionPane.showMessageDialog(frame, "No Mails Selected..", "Information",  JOptionPane.INFORMATION_MESSAGE);	
			}
		}
		else if(command.equals("Delete")) {
			if(inboxCheck == false && outboxCheck == false && sentCheck == false && trashCheck == false) {
				JOptionPane.showMessageDialog(frame, "Please Select The Databse First..", "Information",  JOptionPane.INFORMATION_MESSAGE);				
			}
			else
			{
				System.out.println(displayMails.getRowCount());
				if(displayMails.getRowCount() == 0) 
				{
					JOptionPane.showMessageDialog(frame, "There Is No More Mails In This Folder..", "Information",  JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					if(displayMails.getSelectedRowCount() != 0) {
						try {
							int []rowNum = displayMails.getSelectedRows();
							
							int result = JOptionPane.showConfirmDialog(frame, "Are You Sure You Want To Delete Selected Mails ?", "Respond", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
							if(result == JOptionPane.YES_OPTION)
							{
								DriverManager.registerDriver(new com.mysql.jdbc.Driver());
								String url = "jdbc:mysql://localhost:3306/emailclient?user=root&password=root";
								Connection con = DriverManager.getConnection(url);
								for(int i=rowNum.length;i>0;i--)
								{
									System.out.println(rowNum[rowNum.length-1]);
									int row = displayMails.getSelectedRow();
									if(!getTitle.equals("Trash")) {
										
										String val = "", val1 = "", val2 = "", val3 = "";
										if (row > -1) {		
						 				 	val = (String)model.getValueAt(row, 0);
											val1 = (String)model.getValueAt(row, 1);	
											val2 = (String)model.getValueAt(row, 2);
											val3 = (String)model.getValueAt(row, 3);
											System.out.println(val+"	"+val1+"	"+val2+"	"+val3);
										}
										try {
											PreparedStatement pstmt = con.prepareStatement("Insert into trash values(?, ?, ?, ?)");
											pstmt.setString(1, val);
											pstmt.setString(2, val1);
											pstmt.setString(3, val2);
											pstmt.setString(4, val3);
											pstmt.executeUpdate();									
											
										}		
										catch(Exception ee) {
											System.out.println(ee.getMessage());
										}
										model.removeRow(rowNum[i-1]);
										try {
											Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
											ResultSet rs1 = null;
											if(getTitle.equals("Inbox"))
												rs1 = stmt.executeQuery("Select * from Inbox where date='"+val3+"'");
											else if(getTitle.equals("Sent Mail"))
												rs1 = stmt.executeQuery("Select * from Sent where date='"+val3+"'");
											else if(getTitle.equals("Outbox"))
												rs1 = stmt.executeQuery("Select * from outbox where date='"+val3+"'");
											else if(getTitle.equals("Drafts"))
												rs1 = stmt.executeQuery("Select * from drafts where date='"+val3+"'");
										
												
											while(rs1.next())
											{
												rs1.deleteRow();
											}
										}
										catch(Exception e1) {
											System.out.println(e1.getMessage());
										}
									}
									else {
										model.removeRow(rowNum[i-1]);
										try {
											Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
											//	System.out.println("Select * from Inbox where subject='"+val1+"'");
												ResultSet rs1 = stmt.executeQuery("Select * from trash ");
											while(rs1.next())
											{
												rs1.deleteRow();
											}
										}
										catch(Exception e1) {
											System.out.println(e1.getMessage());
										}
									}
								}
							}
						}
						catch(Exception op)
						{
							
						}
					}
					else{
						JOptionPane.showMessageDialog(frame, "No Mails Selected..", "Information",  JOptionPane.INFORMATION_MESSAGE);	
					}
				}
			}

		}
		else if(command.equals("Restore All")) {
			
		}
		else if(command.equals("Delete All")) {
			if(inboxCheck == false && outboxCheck == false && sentCheck == false && trashCheck == false) {
				JOptionPane.showMessageDialog(frame, "Please Select The Databse First..", "Information",  JOptionPane.INFORMATION_MESSAGE);				
			}
			else
			{
				System.out.println(displayMails.getRowCount());
				if(displayMails.getRowCount() == 0)
				{
					JOptionPane.showMessageDialog(frame, "There Is No More Mails In This Folder..", "Information",  JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					if(displayMails.getSelectedRowCount() != 0) {
						displayMails.selectAll();
						int []rowNum = displayMails.getSelectedRows();
						int result = JOptionPane.showConfirmDialog(frame, "Are You Sure You Want To Delete Selected Mails ?", "Respond", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
						if(result == JOptionPane.YES_OPTION)
						{		
							for(int i=rowNum.length;i>0;i--)
							{
								model.removeRow(rowNum[i-1]);
							}
						}
					}
					else{
						JOptionPane.showMessageDialog(frame, "No Mails Selected..", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}			
		}
	}
	public void valueChanged(ListSelectionEvent e) {
		
		if(e.getValueIsAdjusting() == false) {
			if(cv.equals("Hello")) {
				int row = displayMails.getSelectedRow();
				if (row > -1) {
					Object val = model.getValueAt(row, 2);
					to = body =(String) model.getValueAt(row, 0);
					body =(String) model.getValueAt(row, 2);
					sub =(String) model.getValueAt(row, 1);
					readMails.setFont(new Font("Corbel", Font.BOLD, 25));
					readMails.setText(val+"");
				}
				else {
					cv = "Not";
					readMails.setText("");
				}
			}
			else if(cv.equals("Not")){
				
			}
		}
	}
}