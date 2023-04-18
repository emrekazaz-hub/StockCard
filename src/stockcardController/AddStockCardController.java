package stockcardController;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import stockCardModel.MainModel;
import stockCardModel.StockCard;
import stockcardView.StockCardDesign;

public class AddStockCardController {

	StockCard stockCardModel;
	StockCardDesign stockCardDesign;
	MainModel mainModel;

	public AddStockCardController(StockCard stockCardModel) {
		super();
		this.stockCardModel = stockCardModel;
		
		
		mainModel = new MainModel(stockCardModel);
	}

	public void add() {
		mainModel.add();
		
	}

	public void delete() {
		mainModel.delete();
	}
	
	public void update() {
		mainModel.update();
	}

	public void search() {
		mainModel.search();
	}
	
	public void copy() {
		mainModel.copy();
	}
	
	public void upSellect() {
		mainModel.upSellect();
	}
	
	public void downSellect() {
		mainModel.downSellect();
	}

	public void topdf() {
		mainModel.topdf();
	}
	
	public void toexcel() {
		mainModel.toexcel();
	}
}

