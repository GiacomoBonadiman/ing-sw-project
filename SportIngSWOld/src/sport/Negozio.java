package sport;

public class Negozio {

	private int codFiscale;
	private String nome;
	private String indirizzo;
	private String citta;
	
	public Negozio(int codFiscale, String nome, String indirizzo, String citta) {
        this.codFiscale = codFiscale;
		this.nome = nome;
        this.indirizzo = indirizzo;
        this.citta = citta;  
	}
	
	public int getCodFiscale() {
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
}
