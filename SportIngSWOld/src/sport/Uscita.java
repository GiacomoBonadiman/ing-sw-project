package sport;

import java.util.*;

public class Uscita {

	private Date uscDate;
	private int uscBolla;
	private Articolo uscArticoli;
	private Negozio negozio;
	private String spedizioniere;
	
	public Uscita(Date uscDate, int uscBolla, Articolo uscArticoli, Negozio negozio, String spedizioniere){
        this.uscDate = uscDate;
        this.uscBolla = uscBolla;
        this.uscArticoli = uscArticoli;
        this.negozio = negozio;
        this.spedizioniere = spedizioniere;
	}
	
	public Date getUscDate() {
		return uscDate;
	}
	public int getUscBolla() {
		return uscBolla;
	}
	public Articolo getUscArticoli() {
		return uscArticoli;
	}
	public Negozio getNegozio() {
		return negozio;
	}
	public String getSpedizioniere() {
		return spedizioniere;
	}
	
}
