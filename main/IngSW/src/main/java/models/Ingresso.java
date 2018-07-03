package main.java.models;

import java.time.LocalDate;

public class Ingresso {

	private String ingrUnicode;
	private LocalDate ingrDate;
	private Articolo articolo;
	private int numMensola;
	
	public Ingresso(String ingrUnicode, LocalDate ingrDate, Articolo articolo, int numMensola) {
		this.ingrUnicode = ingrUnicode;
		this.ingrDate = ingrDate;
		this.articolo = articolo;
		this.numMensola = numMensola;
	}
	
	public String getIngrUnicode() {
		return ingrUnicode;
	}

	public LocalDate getIngrDate() {
		return ingrDate;
	}
	
	public void setIngrUnicode(String ingrUnicode) {
		this.ingrUnicode = ingrUnicode;
	}

	public void setIngrDate(LocalDate ingrDate) {
		this.ingrDate = ingrDate;
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public int getNumMensola() {
		return numMensola;
	}

	public void setNumMensola(int numMensola) {
		this.numMensola = numMensola;
	}

	public boolean equals(Object object) {
		if (object== null) {
			return false;
		}
		if (object instanceof Ingresso) {
			Ingresso other = (Ingresso)object;
			return ingrUnicode.equals(other.ingrUnicode) && articolo.equals(other.articolo);
		}
		return false;
	}
	
	public int hashCode() {
		return ingrUnicode.hashCode() ^ articolo.hashCode();
	}
}
