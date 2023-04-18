package stockcardView;

import javax.swing.JPanel;

import stockcardController.StockCardController;

public class StockCardPanel extends JPanel {

	private StockCardController baseController;
	
	public StockCardPanel(StockCardController baseController) {
		this.baseController = baseController;
		
	}
}
