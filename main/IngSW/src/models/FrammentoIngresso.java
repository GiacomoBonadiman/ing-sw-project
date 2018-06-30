package models;

public class FrammentoIngresso {
	
	private Articolo articolo;
	private int qty, numMensola;
	
	public FrammentoIngresso(Articolo articolo, int qty, int numMensola) {
		this.articolo = articolo;
		this.qty = qty;
		this.numMensola = numMensola;
	}

	public Articolo getArticolo() {
		return articolo;
	}
	
	public int getQty() {
		return qty;
	}

	public int getNumMensola() {
		return numMensola;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public void setNumMensola(int numMensola) {
		this.numMensola = numMensola;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
}
