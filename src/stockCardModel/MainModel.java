package stockCardModel;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import stockcardController.AddStockCardController;
import stockcardController.DataBaseController;
import stockcardView.StockCardDesign;
import stockcardView.StockCard_ListView;

public class MainModel extends StockCardDesign {

	StockCard_ListView stockCard_ListView;
	StockCard stockCardModel;
	StockCardDesign stockCardDesign = new StockCardDesign();

		
	public MainModel(AddStockCardController addStockCardController, StockCard_ListView stockCard_ListView) {
		super(addStockCardController);
		this.stockCard_ListView = stockCard_ListView;
	}

	public MainModel(StockCardDesign stockCardDesign) {
		super();
		this.stockCardDesign = stockCardDesign;
	}

	public MainModel(StockCard stockCardModel) {
		super();
		this.stockCardModel = stockCardModel;
	}
	
	
	
	
	public Connection connection;
	public PreparedStatement pst;

	public ResultSet resultSet;
	public Statement stm;
	public String sql;
		
		
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
	
	  
	public void add() {
		try {
			pst = stockCardDesign.connection.prepareStatement(
					"insert into stockcard(Stok_Kodu,Stok_Adi,Stok_Tipi,Birimi,Barkodu,KDV_Tipi,Aciklama)"
							+ "values(?,?,?,?,?,?,?)");
			
			pst.setString(1, stockCardDesign.textFieldStokAdi.getText());
			pst.setString(2, stockCardDesign.textFieldStokAdi.getText());
			pst.setString(3, stockCardDesign.comboBoxStokTipi.getSelectedItem().toString());
			pst.setString(4, stockCardDesign.comboBoxBirimi.getSelectedItem().toString());
			pst.setString(5, stockCardDesign.textFieldBarkodu.getText());
			pst.setString(6, stockCardDesign.comboBoxKDVTipi.getSelectedItem().toString());
			pst.setString(7, stockCardDesign.textAreaAciklama.getText());
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Kayit Eklendi");
			textFieldStokKodu.requestFocus();

			stockCardDesign.textFieldStokKodu.requestFocus();
			stockCardDesign.textFieldStokKodu.setText("");
			stockCardDesign.textFieldStokAdi.setText("");
			stockCardDesign.comboBoxStokTipi.setSelectedItem("");
			stockCardDesign.comboBoxBirimi.setSelectedItem("");
			stockCardDesign.textFieldBarkodu.setText("");
			stockCardDesign.comboBoxKDVTipi.setSelectedItem("");
			stockCardDesign.textAreaAciklama.setText("");


		}

		catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Lütfen Tüm Alanları Doldurunuz");
			e1.printStackTrace();
		}
	}

	public void delete() {
		Stok_Kodu = textFieldStokKodu.getText();

		try {
			pst = connection.prepareStatement("delete from stockcard where Stok_Kodu=?");
			System.out.println(Stok_Kodu);
			pst.setString(1, Stok_Kodu);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Kayıt Silindi");
			table_load();

			textFieldStokKodu.requestFocus();
			textFieldStokKodu.setText("");
			textFieldStokAdi.setText("");
			comboBoxStokTipi.setSelectedItem("");
			comboBoxBirimi.setSelectedItem("");
			textFieldBarkodu.setText("");
			comboBoxKDVTipi.setSelectedItem("");
			textAreaAciklama.setText("");

		} catch (SQLException e1) {

		}
	}

	public void update() {
		try {
			pst = connection.prepareStatement(
					"update stockcard set (Stok_Kodu=?, Stok_Adi=?, Stok_Tipi=?, Birimi=?, Barkodu=?, KDV_Tipi=?, Aciklama=?)"
							+ "where (Stok_Kodu=?)");
			pst.setString(1, stockCardDesign.textFieldStokKodu.getText());
			pst.setString(2, stockCardDesign.textFieldStokAdi.getText());
			pst.setString(3, stockCardDesign.comboBoxStokTipi.getSelectedItem().toString());
			pst.setString(4, stockCardDesign.comboBoxBirimi.getSelectedItem().toString());
			pst.setString(5, stockCardDesign.textFieldBarkodu.getText());
			pst.setString(6, stockCardDesign.comboBoxKDVTipi.getSelectedItem().toString());
			pst.setString(7, stockCardDesign.textAreaAciklama.getText());

//			pst.setString(7, Olusturma_Tarihi);
			pst.executeUpdate();

			JOptionPane.showMessageDialog(null, "Guncellendi");

			stockCardDesign.textFieldStokKodu.setText("");
			stockCardDesign.textFieldStokAdi.setText("");
			stockCardDesign.comboBoxStokTipi.setSelectedItem("");
			stockCardDesign.comboBoxBirimi.setSelectedItem("");
			stockCardDesign.textFieldBarkodu.setText("");
			stockCardDesign.comboBoxKDVTipi.setSelectedItem("");
			stockCardDesign.textAreaAciklama.setText("");
			
			

		}

		catch (SQLException e1) {
			System.out.println("calismiyor");
			e1.printStackTrace();
		}
	}

	public void search() {
		try {
			pst = connection.prepareStatement("select * from stockcard where Stok_Kodu = ?");
			pst.setString(1, Stok_Kodu);

			ResultSet resultSet = pst.executeQuery();

			if (resultSet.next() == true) {
				Stok_Kodu = resultSet.getString(1);
				Stok_Adi = resultSet.getString(2);
				comboValueTip = resultSet.getString(3);
				Birimi = resultSet.getString(4);
				Barkodu = resultSet.getString(5);
				comboValueKDV = resultSet.getString(6);
				Aciklama = resultSet.getString(7);

				stockCardDesign.textFieldStokKodu.setText(Stok_Kodu);
				stockCardDesign.textFieldStokAdi.setText(Stok_Adi);
				stockCardDesign.comboBoxStokTipi.setSelectedItem(comboValueTip);
				stockCardDesign.comboBoxBirimi.setSelectedItem(Birimi);
				stockCardDesign.textFieldBarkodu.setText(Barkodu);
				stockCardDesign.comboBoxKDVTipi.setSelectedItem(comboValueKDV);
				stockCardDesign.textAreaAciklama.setText(Aciklama);
				
				
				
				
			} else {
				JOptionPane.showMessageDialog(null, "Lütfen Stok Kodunuzu Kontrol Edin");

			}

		} catch (SQLException ex) {

		}
		
	}
	
	public void copy() {
		
		String multiLineText = (textFieldStokKodu.getText() + "\n" + textFieldStokAdi.getText() + "\n"
				+ comboBoxStokTipi.getSelectedItem().toString() + "\n"
				+ comboBoxBirimi.getSelectedItem().toString() + "\n" + textFieldBarkodu.getText() + "\n"
				+ comboBoxKDVTipi.getSelectedItem().toString() + "\n" + textAreaAciklama.getText());
		Scanner textReader = new Scanner(multiLineText);

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		StringSelection strel = new StringSelection(multiLineText);
		clipboard.setContents(strel, strel);
		JOptionPane.showMessageDialog(null, "Kayıt Kopyalandı");
	}
	
	public void tableListener() {
		
		int SelectedRows = table.getSelectedRow();

		System.out.println("tiklandi");

		textFieldStokKodu.setText(table.getValueAt(SelectedRows, 0).toString());
		textFieldStokAdi.setText(table.getValueAt(SelectedRows, 1).toString());
		comboBoxStokTipi.setSelectedItem(table.getValueAt(SelectedRows, 2).toString());
		comboBoxBirimi.setSelectedItem(table.getValueAt(SelectedRows, 3));
		textFieldBarkodu.setText(table.getValueAt(SelectedRows, 4).toString());
//		comboBoxKDVTipi.setSelectedIndex(SelectedRows);
//		Bunu yazinca kdv yi aliyor ama max 2 satiri sayabiliyor (iki satir kadar alabiliyor) yazmayinca da satir sorunu olmuyor ama bu sefer de kdv yi alamiyor...
		comboBoxKDVTipi.setSelectedItem(table.getValueAt(SelectedRows, 5).toString());
		textAreaAciklama.setText(table.getValueAt(SelectedRows, 6).toString());

		
	}	
	// Row Sellection
	
	
	
	boolean row_Is_Selected = false;
    int index;
    DefaultTableModel model = null;
	
	public void upSellect() {
		if(row_Is_Selected == false)
        {
            model = (DefaultTableModel)stockCard_ListView.getTable2().getModel();
            row_Is_Selected = true;
        }
		
		 index = stockCard_ListView.getTable2().getSelectedRow();
	        if(index > 0){
	        	stockCard_ListView.getTable2().setRowSelectionInterval(index - 1, index - 1);
	            
	        }
	}
	
	public void downSellect() {
		
		if(row_Is_Selected == false)
        {
            model = (DefaultTableModel)stockCard_ListView.getTable2().getModel();
            row_Is_Selected = true;
        }
        
        index = stockCard_ListView.getTable2().getSelectedRow();
        if(index < model.getRowCount() - 1){
        	stockCard_ListView.getTable2().setRowSelectionInterval(index + 1, index + 1);
            
        }
	}
	public void topdf() {
		
	}
	public void toexcel() {
		
	}
}

