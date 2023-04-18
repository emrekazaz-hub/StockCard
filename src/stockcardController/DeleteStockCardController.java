package stockcardController;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

import stockcardView.StockCardDesign;

public class DeleteStockCardController extends StockCardDesign{

	
	public void delete() {
			
		try {
			pst = connection.prepareStatement("delete from stockcard where Stok_Kodu=?");
			pst.setString(1, Stok_Kodu);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "silindi");
			table_load();
			
			textFieldStokKodu.requestFocus(); 
			textFieldStokKodu.setText("");
			textFieldStokAdi.setText("");		
			comboBoxStokTipi.setSelectedItem("");
			comboBoxBirimi.setSelectedItem("");
			textFieldBarkodu.setText("");
			comboBoxKDVTipi.setSelectedItem("");
			textAreaAciklama.setText("");
			
			
		}catch(SQLException e1) {
			
		}
		
	}
}
