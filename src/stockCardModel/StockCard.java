package stockCardModel;

import java.util.Date;

import stockcardView.StockCardDesign;

public class StockCard {
	
	private String Stok_Kodu;
	private String Stok_Adi;
	private int Stok_Tipi;
	private String Birimi;
	private String Barkodu;
	private double KDV_Tipi;
	private String Aciklama;
	private int Olusturma_Tarihi;
	public Date getOlusturma_Tarihi;
	
	public String getStok_Kodu() {return Stok_Kodu;}
	public void setStok_Kodu(String stok_Kodu) {Stok_Kodu = stok_Kodu;}
	
	public String getStok_Adi() {return Stok_Adi;}
	public void setStok_Adi(String stok_Adi) {Stok_Adi = stok_Adi;}
	
	public int getStok_Tipi() {return Stok_Tipi;}
	public void setStok_Tipi(int stok_Tipi) {Stok_Tipi = stok_Tipi;}
	
	public String getBirimi() {return Birimi;}
	public void setBirimi(String birimi) {Birimi = birimi;}
	
	public String getBarkodu() {return Barkodu;}
	public void setBarkodu(String barkodu) {Barkodu = barkodu;}
	
	public double getKDV_Tipi() {return KDV_Tipi;}
	public void setKDV_Tipi(double kDV_Tipi) {KDV_Tipi = kDV_Tipi;}
	
	public String getAciklama() {return Aciklama;}
	public void setAciklama(String aciklama) {Aciklama = aciklama;}
	
	public int getOlusturma_Tarihi() {return Olusturma_Tarihi;}
	public void setOlusturma_Tarihi(int olusturma_Tarihi) {Olusturma_Tarihi = olusturma_Tarihi;}
}
	
