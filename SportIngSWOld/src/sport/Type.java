package sport;

public class Type {

	private String uniname;
	private String description;
	private Sport sport;
	private String materials;
	
	public Type(String uniname, String description, Sport sport, String materials) {
        this.uniname = uniname;
		this.description = description;
        this.sport = sport;
        this.materials = materials;  
	}
	 
	public String getUniname() {
		return uniname;
	}
	public String getDescription() {
		return description;
	}
	public Sport getSport() {
		return sport;
	}
	public String getMaterials() {
		return materials;
	}
}
