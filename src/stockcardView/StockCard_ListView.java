package stockcardView;

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import net.proteanit.sql.DbUtils;
import stockCardModel.StockCard;
import stockcardController.AddStockCardController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.UIManager;

public class StockCard_ListView extends StockCardDesign{


	public JTable table;
	public JFrame frame1;
	public JTable table2;
	public JComboBox comboBox;
	
	StockCard stockCard;
	
	
	public JFrame getFrame1() {
		return frame1;
	}

	public void setFrame1(JFrame frame1) {
		this.frame1 = frame1;
	}

	public JTable getTable2() {
		return table2;
	}

	public void setTable2(JTable table2) {
		this.table2 = table2;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockCard_ListView window = new StockCard_ListView();
					window.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StockCard_ListView() {
		initialize();
		dbconnection();
		table_load2();
		table_load();
		
	}

	public void dbconnection() {
		try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockcard", "root","2013");
            DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
            RecordTable.setRowCount(0);
			int SelectedRows = table.getSelectedRow();
			table_load2();

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
	
	
	public void table_load2()
    {
     try
     {
    pst = connection.prepareStatement("select * from stockcard");
    resultSet = pst.executeQuery();
    table.setModel(DbUtils.resultSetToTableModel(resultSet));
}
     catch (SQLException e)
     {
     e.printStackTrace();
  }
    }
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame1 = new JFrame();
		frame1.setBounds(100, 100, 1250, 670);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		frame1.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		table2 = new JTable();
		scrollPane.setViewportView(table2);
		scrollPane.setBounds(28, 135, 1159, 417);
		frame1.getContentPane().add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("Stok Kartı");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(537, 11, 145, 45);
		frame1.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(28, 49, 319, 75);
		frame1.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Listelenecek Stok Tipini Seçiniz");
		lblNewLabel.setBounds(10, 11, 190, 20);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		String [] stoktipView = {"1", "2","Tümü"};
		comboBox = new JComboBox(stoktipView);
		comboBox.setBounds(10, 42, 190, 22);
		panel.add(comboBox);
		
		JButton btnNewButton_2 = new JButton("Listele");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex()==0) {
					try {
			            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockcard", "root","2013");
			            pst = connection.prepareStatement("select Stok_Kodu, Stok_Adi, Aciklama from stockcard where Stok_Tipi=1");
			            resultSet = pst.executeQuery();
			            table2.setModel(DbUtils.resultSetToTableModel(resultSet));
			            
			            
			            while(resultSet.next()) {
			            	Stok_Kodu = resultSet.getString("Stok_Kodu");
			            	Stok_Adi = resultSet.getString("Stok_Adi");
			            	Aciklama = resultSet.getString("Aciklama");
			            	
			            	String dbData[] = {Stok_Kodu,Stok_Adi};
			                DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
			                RecordTable.addRow(dbData);	     
			            }
			                
			            }
					 
					catch (SQLException e1)
					        {
						System.out.println("calismiyor");
					e1.printStackTrace();
					}
				}else if (comboBox.getSelectedIndex()==1){
					try {
			            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockcard", "root","2013");
			            pst = connection.prepareStatement("select Stok_Kodu, Stok_Adi,KDV_Tipi from stockcard where Stok_Tipi=2");
			            resultSet = pst.executeQuery();
			            table2.setModel(DbUtils.resultSetToTableModel(resultSet));
			            
			            
			            while(resultSet.next()) {
			            	Stok_Kodu = resultSet.getString("Stok_Kodu");
			            	Stok_Adi = resultSet.getString("Stok_Adi");
			            	KDV_Tipi = resultSet.getDouble("KDV_Tipi");
			            	String dbData[] = {Stok_Kodu,Stok_Adi};
			                DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
			                RecordTable.addRow(dbData);	     
			            }
			                
			            }
					 
					catch (SQLException e1)
					        {
						System.out.println("calismiyor");
					e1.printStackTrace();
					}
				}else {
					
					try {
			            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockcard", "root","2013");
			            pst = connection.prepareStatement("select * from stockcard");
			            resultSet = pst.executeQuery();
			            table2.setModel(DbUtils.resultSetToTableModel(resultSet));
			            
			            
			            while(resultSet.next()) {
			            	Stok_Kodu = resultSet.getString("Stok_Kodu");
			            	Stok_Adi = resultSet.getString("Stok_Adi");
			            	KDV_Tipi = resultSet.getDouble("KDV_Tipi");
			            	String dbData[] = {Stok_Kodu,Stok_Adi};
			                DefaultTableModel RecordTable = (DefaultTableModel)table.getModel();
			                RecordTable.addRow(dbData);	     
			            }
			                
			            }
					 
					catch (SQLException e1)
					        {
						System.out.println("calismiyor");
					e1.printStackTrace();
					}
					
				}
				
/*				if(comboBox.getSelectedIndex()==0) {
					System.out.println("ilk tip liste getirilecek");
				}else {
					System.out.println("ikinci tip liste getirilecek");
				}
				*/
			}
		});
		btnNewButton_2.setBounds(220, 29, 89, 36);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Excel Çıktısı");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddStockCardController addStockCardController = new AddStockCardController(stockCardModel);
				addStockCardController.toexcel();
			
			
			}
		});
		btnNewButton.setBounds(28, 563, 103, 45);
		frame1.getContentPane().add(btnNewButton);
		
		JButton btnPdfkts = new JButton("PDF Çıktısı");
		btnPdfkts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showSaveDialog(null);
				SimpleDateFormat Date_Format = new SimpleDateFormat("YYYY-MM-dd"); 
		        String datefrom=  Date_Format.format(stockCard.getOlusturma_Tarihi);
		        String dateto=  Date_Format.format(stockCard.getOlusturma_Tarihi);
		       
		        MessageFormat header=new MessageFormat("Report From "+datefrom+" To " +dateto);
		        MessageFormat footer=new MessageFormat("page{0,number,integer}");
		        try {
		            table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
		            
		        } catch (Exception e1) {
		            e1.getMessage();
		        } 
		        */
			}
		});
		btnPdfkts.setBounds(141, 563, 103, 45);
		frame1.getContentPane().add(btnPdfkts);
		
		JButton geriButton = new JButton("");
		geriButton.setBorderPainted(false);
		geriButton.setOpaque(false);
		geriButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean row_Is_Selected = false;
			    int index;
			    DefaultTableModel model = null;
				if(row_Is_Selected == false)
		        {
		            model = (DefaultTableModel)table2.getModel();
		            row_Is_Selected = true;
		        }
				
				 index = table2.getSelectedRow();
			        if(index > 0){
			            // set selection to the new position
			            table2.setRowSelectionInterval(index - 1, index - 1);        
			        }
			}
		});
		geriButton.setBackground(UIManager.getColor("Button.background"));
		geriButton.setForeground(Color.RED);
		geriButton.setIcon(new ImageIcon("C:\\Users\\emre_\\Downloads\\left-arrow (2).png"));
		geriButton.setBounds(1108, 79, 32, 45);
		frame1.getContentPane().add(geriButton);
		
		JButton ileriButton = new JButton("");
		ileriButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean row_Is_Selected = false;
			    int index;
			    DefaultTableModel model = null;
				if(row_Is_Selected == false)
		        {
		            model = (DefaultTableModel)table2.getModel();
		            row_Is_Selected = true;
		        }
		        
		        index = table2.getSelectedRow();
		        if(index < model.getRowCount() - 1){
		            table2.setRowSelectionInterval(index + 1, index + 1);
		            
		        }
			}
		});
		ileriButton.setBorderPainted(false);
		ileriButton.setIcon(new ImageIcon("C:\\Users\\emre_\\Downloads\\next.png"));
		ileriButton.setBounds(1150, 79, 37, 45);
		frame1.getContentPane().add(ileriButton);
		
		JButton btnKaytEkle = new JButton("Kayıt Ekle");
		btnKaytEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				StockCardDesign stockCardDesign = new StockCardDesign();
//				stockCardDesign.initialize();
			}
		});
		btnKaytEkle.setBounds(1084, 563, 103, 45);
		frame1.getContentPane().add(btnKaytEkle);
	}
}
