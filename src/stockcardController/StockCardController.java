package stockcardController;

import stockcardView.StockCardDesign;
import stockcardView.StockCardFrame;

public class StockCardController extends StockCardDesign{

	private StockCardFrame appFrame;
	
	public void start() {
		
		appFrame = new StockCardFrame(this);

	}
	
	
	
	
	
	
}
