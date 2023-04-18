package stockcardView;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.xml.crypto.Data;


import com.mysql.cj.exceptions.DataTruncationException;

import net.proteanit.sql.DbUtils;
import stockCardModel.MainModel;
import stockCardModel.StockCard;
import stockcardController.AddStockCardController;
import stockcardController.DeleteStockCardController;
import stockcardController.SearchStockCardController;
import stockcardController.StockCardController;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle.Control;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.naming.directory.SearchControls;
import javax.print.attribute.standard.DateTimeAtCreation;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StockCardDesign {

	public JFrame frame;
	public JTextField textFieldStokKodu;
	public JTextField textFieldStokAdi;
	public JTextField textFieldBarkodu;
	public JTextArea textAreaAciklama;
	public Date formattedTextField;

	public JTable table;
	public JButton btnStokKartListesi;
	public JComboBox<?> comboBoxBirimi;
	public JComboBox<?> comboBoxKDVTipi;
	public JComboBox<?> comboBoxStokTipi;
	public String comboValueTip;
	public String comboValueKDV;
	public JTextField textFieldAra;
	
	public String Stok_Kodu;
	public String Stok_Adi;
	public int Stok_Tipi;
	public String Birimi;
	public String Barkodu;
	public double KDV_Tipi;
	public String Aciklama;
	public Date Olusturma_Tarihi;
	public String Stok_Koduid;

	public Connection connection;
	public PreparedStatement pst;

	public ResultSet resultSet;
	public Statement stm;
	public String sql;

	StockCard stockCardModel;
	MainModel mainModel;
	AddStockCardController addStockCardController;


	public StockCardDesign(AddStockCardController addStockCardController) {
		super();
		this.addStockCardController = addStockCardController;
	}

	public StockCardDesign(StockCard stockCardModel) {
		super();
		this.stockCardModel = stockCardModel;
	}

	public StockCardDesign(MainModel mainModel) {
		super();
		this.mainModel = mainModel;
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockCardDesign window = new StockCardDesign();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StockCardDesign() {
		initialize();
		Connect();
		table_load();
	}

	public void Connect() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockcard", "root", "2013");
			DefaultTableModel RecordTable = (DefaultTableModel) table.getModel();
			RecordTable.setRowCount(0);
			int SelectedRows = table.getSelectedRow();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();

		} catch (SQLException ex) {
			ex.printStackTrace();

		}
	}

	public void table_load() {
		try {
			pst = connection.prepareStatement("select * from stockcard");
			resultSet = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1250, 670);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JLabel lblNewLabel = new JLabel("Stok Kartı");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(537, 11, 145, 45);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(10, 57, 500, 468);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Stok Kodu");
		lblNewLabel_1.setBounds(10, 11, 62, 32);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblNewLabel_1_1 = new JLabel("Stok Adı");
		lblNewLabel_1_1.setBounds(10, 68, 62, 32);
		panel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblNewLabel_1_2 = new JLabel("Stok Tipi");
		lblNewLabel_1_2.setBounds(10, 125, 62, 32);
		panel.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblNewLabel_1_1_1 = new JLabel("Birimi");
		lblNewLabel_1_1_1.setBounds(10, 182, 62, 32);
		panel.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblNewLabel_1_3 = new JLabel("Barkodu");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(10, 240, 62, 32);
		panel.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_1_2 = new JLabel("KDV Tipi");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_2.setBounds(10, 297, 62, 32);
		panel.add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_2_1 = new JLabel("Açıklama");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2_1.setBounds(10, 354, 62, 32);
		panel.add(lblNewLabel_1_2_1);

		textFieldStokKodu = new JTextField();
		textFieldStokKodu.setBounds(179, 18, 278, 20);
		panel.add(textFieldStokKodu);
		textFieldStokKodu.setColumns(10);

		textFieldStokAdi = new JTextField();
		textFieldStokAdi.setBounds(179, 75, 278, 20);
		panel.add(textFieldStokAdi);
		textFieldStokAdi.setColumns(10);

		/////////////////

		String[] stoktip = { "1", "2" };
		comboBoxStokTipi = new JComboBox(stoktip);
		comboBoxStokTipi.actionPerformed(null);
		comboBoxStokTipi.setBounds(179, 131, 278, 22);
		panel.add(comboBoxStokTipi);

		////////////////////

		String[] birim = { "A Birim", "B Birim" };
		comboBoxBirimi = new JComboBox(birim);
		comboBoxBirimi.actionPerformed(null);
		comboBoxBirimi.setBounds(179, 188, 278, 22);
		panel.add(comboBoxBirimi);

		textFieldBarkodu = new JTextField();
		textFieldBarkodu.setBounds(179, 247, 278, 20);
		panel.add(textFieldBarkodu);
		textFieldBarkodu.setColumns(10);

		String[] kdvtip = { "18", "8" };
		comboBoxKDVTipi = new JComboBox(kdvtip);
		comboBoxKDVTipi.actionPerformed(null);
		comboBoxKDVTipi.setBounds(179, 303, 278, 22);
		panel.add(comboBoxKDVTipi);

		textAreaAciklama = new JTextArea();
		textAreaAciklama.setBounds(179, 359, 278, 98);
		panel.add(textAreaAciklama);
		

		JButton btnNewButtonEkle = new JButton("Ekle");
		btnNewButtonEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				AddStockCardController addStockCardController = new AddStockCardController(stockCardModel);
//				addStockCardController.add();
				
				try {
				pst = connection.prepareStatement("insert into stockcard(Stok_Kodu,Stok_Adi,Stok_Tipi,Birimi,Barkodu,KDV_Tipi,Aciklama)"
								+ "values(?,?,?,?,?,?,?)");
				if(textFieldStokKodu.getText()==null) {
					JOptionPane.showMessageDialog(null, "Lütfen Tüm Alanları Doldurunuz");
				}
				
				
				pst.setString(1, textFieldStokKodu.getText());
				pst.setString(2, textFieldStokAdi.getText());
				pst.setString(3, comboBoxStokTipi.getSelectedItem().toString());
				pst.setString(4, comboBoxBirimi.getSelectedItem().toString());
				pst.setString(5, textFieldBarkodu.getText());
				pst.setString(6, comboBoxKDVTipi.getSelectedItem().toString());
				pst.setString(7, textAreaAciklama.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Kayit Güncellendi");
				textFieldStokKodu.requestFocus();
				table_load();
				
				textFieldStokKodu.setText("");
				textFieldStokAdi.setText("");
				comboBoxStokTipi.setSelectedItem("");
				comboBoxBirimi.setSelectedItem("");
				textFieldBarkodu.setText("");
				comboBoxKDVTipi.setSelectedItem("");
				textAreaAciklama.setText("");

				}
				
				catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Lütfen Tüm Alanları Doldurunuz");
					e1.printStackTrace();
				}
				
			}
		});

		btnNewButtonEkle.setBounds(10, 536, 101, 45);
		frame.getContentPane().add(btnNewButtonEkle);

		JButton btnNewButtonSil = new JButton("Sil");
		btnNewButtonSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					pst = connection.prepareStatement("delete * from stockcard where Stok_Kodu=?");
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
		});
		btnNewButtonSil.setBounds(142, 536, 101, 45);
		frame.getContentPane().add(btnNewButtonSil);

		JButton btnNewButtonGuncelle = new JButton("Güncelle");
		btnNewButtonGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStockCardController addStockCardController = new AddStockCardController(stockCardModel);
				addStockCardController.update();
			}
		});
		
		btnNewButtonGuncelle.setBounds(280, 536, 101, 45);
		frame.getContentPane().add(btnNewButtonGuncelle);

		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(537, 108, 673, 417);
		frame.getContentPane().add(scrollPane);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int SelectedRows = table.getSelectedRow();
				textFieldStokKodu.setText(table.getValueAt(SelectedRows, 0).toString());
				textFieldStokAdi.setText(table.getValueAt(SelectedRows, 1).toString());
				comboBoxStokTipi.setSelectedItem(table.getValueAt(SelectedRows, 2).toString());
				comboBoxBirimi.setSelectedItem(table.getValueAt(SelectedRows, 3));
				textFieldBarkodu.setText(table.getValueAt(SelectedRows, 4).toString());
				comboBoxKDVTipi.setSelectedItem(table.getValueAt(SelectedRows, 5).toString());
				textAreaAciklama.setText(table.getValueAt(SelectedRows, 6).toString());

			}

			@Override
			public void mousePressed(MouseEvent e) {
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(614, 52, 596, 45);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Stok Kodu Ara");
		lblNewLabel_2.setBounds(10, 16, 106, 14);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));

		textFieldAra = new JTextField();
		textFieldAra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				AddStockCardController addStockCardController = new AddStockCardController(stockCardModel);
				addStockCardController.search();

			}
		});
		textFieldAra.setBounds(99, 11, 398, 27);
		panel_1.add(textFieldAra);
		textFieldAra.setColumns(10);

		JButton btnNewButton = new JButton("Ara");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

		});
		btnNewButton.setBounds(507, 13, 89, 23);
		panel_1.add(btnNewButton);

		JButton btnNewButtonKopyala = new JButton("Kopyala");
		btnNewButtonKopyala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
		});
		btnNewButtonKopyala.setBounds(409, 536, 101, 45);
		frame.getContentPane().add(btnNewButtonKopyala);

		JButton btnStokKartListesi = new JButton("Stok Kartı Listesini Aç");
		btnStokKartListesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				StockCard_ListView stockCard_ListView = new StockCard_ListView();
				stockCard_ListView.initialize();

			}
		});
		btnStokKartListesi.setBounds(788, 536, 167, 52);
		frame.getContentPane().add(btnStokKartListesi);

	}
}
