package stockcardController;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import stockcardView.StockCardDesign;

public class SearchStockCardController extends StockCardDesign{
	
	public void search() {
		try {
	          
			Stok_Kodu = textFieldAra.getText();
            pst = connection.prepareStatement("select * from stockcard where Stok_Kodu = ?");
            pst.setString(1, Stok_Kodu);
            
            ResultSet resultSet = pst.executeQuery();
            
            
        if(resultSet.next()==true)
        {
        	Stok_Kodu = resultSet.getString(1);
            Stok_Adi = resultSet.getString(2);
            comboValueTip = resultSet.getString(3);
            Birimi = resultSet.getString(4);
            Barkodu = resultSet.getString(5);
            comboValueKDV = resultSet.getString(6);
            Aciklama = resultSet.getString(7);
            
            
			textFieldStokKodu.setText(Stok_Kodu);
			textFieldStokAdi.setText(Stok_Adi);		
			comboBoxStokTipi.setSelectedItem(comboValueTip);
			comboBoxBirimi.setSelectedItem(Birimi);
			textFieldBarkodu.setText(Barkodu);
			comboBoxKDVTipi.setSelectedItem(comboValueKDV);
			textAreaAciklama.setText(Aciklama);
        }  
        else
        {
        	JOptionPane.showMessageDialog(null, "Lütfen Stok Kodunuzu Kontrol Edin");
            
        }		            

    }
catch (SQLException ex) {
      
    }
	}
}
