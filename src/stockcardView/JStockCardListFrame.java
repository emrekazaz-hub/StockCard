package stockcardView;

import javax.swing.JFrame;

import stockcardController.StockCardController;

public class JStockCardListFrame extends StockCardFrame{
	
	private int id;
	public String kodu;
	public String adi;
	public double orani;

	public JStockCardListFrame(StockCardController baseController, int id, String kodu, String adi, double orani) {
		super(baseController);
		this.id = id;
		this.kodu = kodu;
		this.adi = adi;
		this.orani = orani;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKodu() {
		return kodu;
	}

	public void setKodu(String kodu) {
		this.kodu = kodu;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public double getOrani() {
		return orani;
	}

	public void setOrani(double orani) {
		this.orani = orani;
	}

	public JStockCardListFrame(StockCardController baseController) {
		super(baseController);
		
	}

}
