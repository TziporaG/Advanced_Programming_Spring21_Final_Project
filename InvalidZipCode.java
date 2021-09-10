package project;

public class InvalidZipCode extends RuntimeException {
	
	public InvalidZipCode() {
		
		super("Invalid zip code (Must be 5 digits).");
	}
	
	public InvalidZipCode(String message) {
		
		super(message);
	}
}