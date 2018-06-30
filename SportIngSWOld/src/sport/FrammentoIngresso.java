package sport;

public class FrammentoIngresso {

	private Articolo articolo;
	private int numMensola;
	
	public FrammentoIngresso(Articolo articolo, int numMensola){
        this.articolo=articolo;
        this.numMensola=numMensola;
        
	}
	public Articolo getArticolo() {
		return articolo;
	}
	public int getNumMensola() {
		return numMensola;
	}
}

