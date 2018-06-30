package models;

import java.util.*;

public class Ordine {

	private Negozio negozio;
	private String ordUnicode;
	private Date ordDate;
	private Articolo articolo;
	private double totPrice;
	private boolean isDispatched;
	
	public Ordine(Negozio negozio, String ordUnicode, Date ordDate, Articolo articolo, double totPrice, boolean isDispatched) {
        this.negozio = negozio;
		this.ordUnicode = ordUnicode;
        this.ordDate = ordDate;
        this.articolo = articolo;
        this.isDispatched = isDispatched;
	}
	
	public Negozio getNegozio() {
		return negozio;
	}
	
	public String getOrdUnicode() {
		return ordUnicode;
	}
	
	public Date getOrdDate() {
		return ordDate;
	}
	
	public Articolo getArticolo() {
		return articolo;
	}

	public double getTotPrice() {
		return totPrice;
	}

	public boolean isDispatched() {
		return isDispatched;
	}
	
	public void setNegozio(Negozio negozio) {
		this.negozio = negozio;
	}

	public void setOrdUnicode(String ordUnicode) {
		this.ordUnicode = ordUnicode;
	}

	public void setOrdDate(Date ordDate) {
		this.ordDate = ordDate;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public void setTotPrice(double totPrice) {
		this.totPrice = totPrice;
	}

	public void setDispatched(boolean isDispatched) {
		this.isDispatched = isDispatched;
	}

	public boolean equals(Object object) {
		if (object== null) {
			return false;
		}
		if (object instanceof Ordine) {
			Ordine other = (Ordine)object;
			return ordUnicode.equals(other.ordUnicode) && articolo.equals(other.articolo);
		}
		return false;
	}
	
	public int hashCode() {
		return ordUnicode.hashCode() ^ articolo.hashCode();
	}
}
