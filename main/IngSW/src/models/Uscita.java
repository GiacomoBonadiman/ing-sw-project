package models;

import java.time.LocalDate;
import java.util.*;

public class Uscita {

	private LocalDate uscDate;
	private String uscBolla;
	private Articolo articolo;
	private Negozio negozio;
	private String spedizioniere;
	private String linkedOrd;
	
	public Uscita(LocalDate uscDate, String uscBolla, Articolo articolo, Negozio negozio, String spedizioniere, String linkedOrd) {
        this.uscDate = uscDate;
        this.uscBolla = uscBolla;
        this.articolo = articolo;
        this.negozio = negozio;
        this.spedizioniere = spedizioniere;
        this.linkedOrd = linkedOrd;
	}
	
	public LocalDate getUscDate() {
		return uscDate;
	}
	
	public String getUscBolla() {
		return uscBolla;
	}
	
	public Articolo getArticolo() {
		return articolo;
	}

	public Negozio getNegozio() {
		return negozio;
	}
	
	public String getSpedizioniere() {
		return spedizioniere;
	}
	
	public String getOrdUnicode() {
		return linkedOrd;
	}
	
	public void setUscDate(LocalDate uscDate) {
		this.uscDate = uscDate;
	}

	public void setUscBolla(String uscBolla) {
		this.uscBolla = uscBolla;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public void setNegozio(Negozio negozio) {
		this.negozio = negozio;
	}

	public void setSpedizioniere(String spedizioniere) {
		this.spedizioniere = spedizioniere;
	}
	
	public void setOrdUnicode(String linkedOrd) {
		this.linkedOrd = linkedOrd;
	}

	public boolean equals(Object object) {
		if (object== null) {
			return false;
		}
		if (object instanceof Uscita) {
			Uscita other = (Uscita)object;
			return uscBolla.equals(other.uscBolla) && articolo.equals(other.articolo);
		}
		return false;
	}
	
	public int hashCode() {
		return uscBolla.hashCode() ^ articolo.hashCode();
	}
}
