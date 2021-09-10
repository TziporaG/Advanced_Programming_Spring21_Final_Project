package project;

public class InvalidPassportID extends RuntimeException {
	
	public InvalidPassportID() {
		
		super("Invalid Passport ID (Must be 9 digits).");
	}
	
	public InvalidPassportID(String message) {
		
		super(message);
	}
}