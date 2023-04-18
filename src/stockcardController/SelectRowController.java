package stockcardController;

import javax.swing.table.DefaultTableModel;

import stockcardView.StockCard_ListView;

public class SelectRowController extends StockCard_ListView{

	
	boolean row_Is_Selected = false;
    int index;
    DefaultTableModel model = null;
	
	public void UpSellect() {
		if(row_Is_Selected == false)
        {
            model = (DefaultTableModel)table2.getModel();
            row_Is_Selected = true;
        }
		
		 index = table2.getSelectedRow();
	        if(index > 0){
	            table2.setRowSelectionInterval(index - 1, index - 1);
	            
	        }
	}
	
	
	
	public void DownSellect() {
		
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
}
