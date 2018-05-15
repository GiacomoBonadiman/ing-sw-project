package sport;

public class Magazzino {

	private Articolo articoli;
	private Ingresso ingressi;
	private Uscita uscite;
	
	public Magazzino(Articolo articoli, Ingresso ingressi, Uscita uscite) {
        this.articoli = articoli;
        this.ingressi = ingressi;
        this.uscite = uscite;  
	}
	
	public Articolo getArticoli() {
		return articoli;
	}

	public Ingresso getIngressi() {
		return ingressi;
	}

	public Uscita getUscite() {
		return uscite;
	}
	
	public void registerIngresso(Ingresso ingresso) {
		//Ingresso newIng = new Ingresso();
	}

	public void registerUscita(Uscita uscita) {
		//Uscita newUsc = new Uscita();
	}

	public void addArticoli (Articolo articoli) {
		//Articolo newArt = new Articolo();
	}

	public void delArticoli(Articolo articoli) {
		//Articolo delArt = new Articolo();
	}
	
    public static void main(String[] args) throws Exception   {
    	
    }
}

