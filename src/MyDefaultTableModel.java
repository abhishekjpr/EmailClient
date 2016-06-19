import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;


class MyDefaultTableModel extends DefaultTableModel {
	MyDefaultTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
   	}
    		public boolean isCellEditable(int row, int column) {
            	return false;
	}
}