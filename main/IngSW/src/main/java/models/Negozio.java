package main.java.models;

public class Negozio {

	private String codFiscale;
	private String nome;
	private String indirizzo;
	private String citta;
	
	public Negozio(String codFiscale, String nome, String indirizzo, String citta) {
        this.codFiscale = codFiscale;
		this.nome = nome;
        this.indirizzo = indirizzo;
        this.citta = citta;
	}
	
	public String getCodFiscale() {
		return codFiscale;
	}
	public String getNome() {
		return nome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public String getCitta() {
		return citta;
	}
	
	public boolean equals(Object object) {
		if (object== null) {
			return false;
		}
		if (object instanceof Negozio) {
			Negozio other = (Negozio)object;
			return codFiscale.equals(other.codFiscale);
		}
		return false;
	}
	
	public int hashCode() {
		return codFiscale.hashCode();
	}
}
