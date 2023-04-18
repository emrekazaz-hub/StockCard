package stockcardController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import stockcardView.StockCardDesign;

public class DataBaseController extends StockCardDesign{
	
	public Connection connection;
	public PreparedStatement pst;

	public ResultSet resultSet;
	public Statement stm;
	public String sql;
	
	public void table_load() {
		try {
			pst = connection.prepareStatement("select * from stockcard");
			resultSet = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
		
		
	public void dbConnection() {
	
		try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockcard", "root","2013");
            DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
            RecordTable.setRowCount(0);
			int SelectedRows = table.getSelectedRow();

        }
        catch (ClassNotFoundException ex)
        {
          ex.printStackTrace();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();

        }
	}

}
