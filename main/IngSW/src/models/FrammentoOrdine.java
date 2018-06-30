package models;

public class FrammentoOrdine {

	private Articolo articolo;
	private int qty;
	private double totPrice;
	
	public FrammentoOrdine (Articolo articolo, int qty, double totPrice){
        this.articolo=articolo;
        this.qty=qty;
        this.totPrice=totPrice;
        
	}
	public Articolo getArticolo() {
		return articolo;
	}
	public int getQty() {
		return qty;
	}
	public double getTotPrice() {
		return totPrice;
	}
	
}
