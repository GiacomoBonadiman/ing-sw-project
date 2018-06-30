package models;

public class Type {
	
	private String uniname;
	private String description;
	private String sport;
	private String materials;
	
	public Type(String uniname, String description, String sport, String materials) {
		this.uniname = uniname;
		this.description = description;
		this.materials = materials;
		/*
		if (!SportContainer.getInstance().sports.contains(sport)) {
			throw new IllegalArgumentException();
		}
		*/
		this.sport = sport;
	}

	public String getUniname() {
		return uniname;
	}

	public String getDescription() {
		return description;
	}

	public String getSport() {
		return sport;
	}

	public String getMaterials() {
		return materials;
	}
	
	public void setUniname(String uniname) {
		this.uniname = uniname;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
	}

	public boolean equals(Object object) {
		if (object== null) {
			return false;
		}
		if (object instanceof Type) {
			Type other = (Type)object;
			return uniname.equals(other.uniname);
		}
		return false;
	}
	
	public int hashCode() {
		return uniname.hashCode();
	}
}
