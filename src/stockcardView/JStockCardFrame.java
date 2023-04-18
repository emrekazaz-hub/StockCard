package stockcardView;

import stockcardController.StockCardController;

public class JStockCardFrame extends StockCardFrame{

	private int id;
	public String kodu;
	public String adi;
	public String aciklama;
	
	public JStockCardFrame(StockCardController baseController, int id, String kodu, String adi, String aciklama) {
		super(baseController);
		this.id = id;
		this.kodu = kodu;
		this.adi = adi;
		this.aciklama = aciklama;
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

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	
	public JStockCardFrame(StockCardController baseController) {
		super(baseController);

	}

}
