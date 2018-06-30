package sport;

import java.util.*;

public class Articolo {
	private Type type;
	private int unicode;
	private double price;
	private Date prodDate;
	
	public Articolo (Type type, int unicode, double price, Date prodDate){
	        this.type = type;
	        this.unicode = unicode;
	        this.price = price;
	        this.prodDate = prodDate;
	}
	
	public Type getType() {
		return type;
	}
	public int getUnicode() {
		return unicode;
	}
	public double getPrice() {
		return price;
	}
	public Date getProdDate() {
		return prodDate;
	}
}
