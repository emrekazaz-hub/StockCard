package stockcardView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import stockcardController.StockCardController;

public class StockCardFrame extends JFrame implements MouseListener{


	private StockCardPanel basePanel;
	
	public StockCardFrame(StockCardController baseController) {
		basePanel = new StockCardPanel(baseController);
		setupFrame();
	}
	
	private void setupFrame() {
		this.setContentPane(basePanel);
		this.setSize(500, 500);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
