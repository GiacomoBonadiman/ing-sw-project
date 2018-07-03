package main.java.models;

import java.time.LocalDate;

public class Articolo {
	
	private Tipo type;
	private String unicode;
	private int qty;
	private double price;
	private LocalDate date;
	
	public Articolo(Tipo type, String unicode, int qty, double price, LocalDate date) {
		this.unicode = unicode;
		this.qty = qty;
		this.price = price;
		this.date = date;
		this.type = type;
	}

	public Tipo getType() {
		return type;
	}

	public String getUnicode() {
		return unicode;
	}

	public int getQty() {
		return qty;
	}
	
	public double getPrice() {
		return price;
	}

	public LocalDate getDate() {
		return date;
	}
	
	public void setType(Tipo type) {
		this.type = type;
	}

	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean equals(Object object) {
		if (object== null) {
			return false;
		}
		if (object instanceof Articolo) {
			Articolo other = (Articolo)object;
			return unicode.equals(other.unicode);
		}
		return false;
	}
	
	public int hashCode() {
		return unicode.hashCode();
	}
	
	public Articolo clone() {
		return new Articolo(type, unicode, qty, price, date);
	}
}
