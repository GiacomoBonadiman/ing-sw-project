package sport;

import java.util.*;

public class Ingresso {

	private int ingrUnicode;
	private Date ingrDate;
	private FrammentoIngresso ingrFragments;
	
	public Ingresso (int ingrUnicode, Date ingrDate, FrammentoIngresso ingrFragments){
        this.ingrUnicode = ingrUnicode;
        this.ingrDate = ingrDate;
        this.ingrFragments = ingrFragments;        
	}
	
	public int getIngrUnicode() {
		return ingrUnicode;
	}
	public Date getIngrDate() {
		return ingrDate;
	}
	public FrammentoIngresso getIngrFragments() {
		return ingrFragments;
	}
	

}


