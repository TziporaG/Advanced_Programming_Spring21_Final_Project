package project;

public class PassengerExistsException extends RuntimeException {
	
	public PassengerExistsException() {
		
		super("Passenger is already on the list.");
	}
	
	public PassengerExistsException(String message) {
		
		super(message);
	}
}