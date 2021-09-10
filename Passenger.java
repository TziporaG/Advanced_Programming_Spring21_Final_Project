package project;

public class Passenger extends Person {
	
	private int passportID;
	private int luggagePeices;
	
	public Passenger(String f, String l, Gender g, Address add, String birthdate, int passportID, int luggagePeices) {
		
		super(f, l, g, add, birthdate);
		
		//input validation to make sure passport ID is 9 numbers long
		String passIDString = String.valueOf(passportID);
		if(passIDString.length() < 9 || passIDString.length() > 9) {
			
			throw new InvalidPassportID();
		}
		
		this.passportID = passportID;
		
		//cannot have negative amount of luggage pieces
		if(luggagePeices < 0) {
			
			throw new IllegalArgumentException("Luggage Peices amount can not be less than zero");
		}
		this.luggagePeices = luggagePeices;
		
	}
	
	public Passenger(String f, String l, Gender g, String s, String c, String state, String zip, String birthdate, int passportID, int luggagePeices) {
		
		super(f, l, g, s, c, state, zip, birthdate);
		
		//input validation to make sure passport ID is 9 numbers long
		String passIDString = String.valueOf(passportID);
		if(passIDString.length() < 9 || passIDString.length() > 9) {
			
			throw new InvalidPassportID();
		}
		
		this.passportID = passportID;
		
		//cannot have negative amount of luggage pieces
		if(luggagePeices < 0) {
			
			throw new IllegalArgumentException("Luggage Peices amount can not be less than zero");
		}
		
		this.luggagePeices = luggagePeices;
		
	}
	
	
	public Passenger(Passenger p) {
		
		super(p.getFirstName(), p.getLastName(), p.getGender(), p.getAddress(), p.getBirthDate());
		
		this.passportID = p.passportID;
		this.luggagePeices = p.luggagePeices;	
	}
	
	public Passenger() {
		
	}
	
	@Override
	public String toString() {
		
		StringBuilder toString = new StringBuilder();
		
		toString.append(super.toString());
		toString.append("\nPassport ID: " + passportID);
		toString.append("\nLuggage Peices: " + luggagePeices);
		
		return toString.toString();
	}
	
	//compare only based on passport ID
	@Override
	public boolean equals(Object o) {
		
		if(this == o) {
			return true;
		}
		
		if(o == null) {
			return false;
		}
		
		if(this.getClass() != o.getClass()) {
			return false;
		}
		
		Passenger otherPassengerObject = (Passenger) o;
		
		if(this.passportID != otherPassengerObject.passportID) {
			return false;
		}
		
		return true;
	}
}