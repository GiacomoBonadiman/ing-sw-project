package sport;

import java.util.*;

public class Ordine {

	private Negozio negozio;
	private int ordUnicode;
	private Date ordDate;
	private FrammentoOrdine ordFragments;
	private boolean isDispatched;
	
	public Ordine(Negozio negozio, int ordUnicode, Date ordDate, FrammentoOrdine ordFragments, boolean isDispatched) {
        this.negozio = negozio;
		this.ordUnicode = ordUnicode;
        this.ordDate = ordDate;
        this.ordFragments = ordFragments;
        this.isDispatched = isDispatched;
	}
	
	public Negozio getNegozio() {
		return negozio;
	}
	public int getOrdUnicode() {
		return ordUnicode;
	}
	public Date getOrdDate() {
		return ordDate;
	}
	public FrammentoOrdine getOrdFragments() {
		return ordFragments;
	}
	public boolean isDispatched() {
		return isDispatched;
	}
}
