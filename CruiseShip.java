package project;
import java.util.*;

public class CruiseShip extends Ship {
	
	private int maxPassengers;
	private ArrayList<Passenger> passengers;
	
	public CruiseShip(String shipName, int yearBuilt, int maxPassengers, ArrayList<Passenger> passengers) {
		
		super(shipName, yearBuilt);
		
		//can not have a negative value
		if (maxPassengers < 0) {
			
			throw new IllegalArgumentException("Invalid value for max passengers");
		}
		
		this.maxPassengers = maxPassengers;
		
		//fill the array field with the user's array of passengers
		this.passengers = new ArrayList <Passenger> ();
			
			for (int i = 0; i < passengers.size(); i++) {
				
				this.passengers.add(passengers.get(i));
			}
		
	}
	
	public CruiseShip() {
		
	}
	
	public ArrayList<Passenger> getPassengers() {
		
		ArrayList<Passenger> passengersCopy = new ArrayList<Passenger>();
		
		for (int i = 0; i < passengers.size(); i++) {
			
			passengersCopy.add(this.passengers.get(i));
		}
		
		return passengersCopy;
	}
	
	public int getMaxPassengers() {
		
		return this.maxPassengers;
	}
	
	public void addPassenger(Passenger newPassenger) {
		
		for(int i = 0; i < this.passengers.size(); i++ ) {
			
			if(this.passengers.get(i).equals(newPassenger)) {
				
				throw new PassengerExistsException();
			}
			
		}
		
		passengers.add(newPassenger);
		
	}
	
	public void removePassenger(int indexToRemove) {
		
		if(indexToRemove < 0 || (indexToRemove+1) > passengers.size()) {
			
			throw new ArrayIndexOutOfBoundsException ();
			
		}
		passengers.remove(indexToRemove);
		
	}
	
	public void setMaxPassengers(int maxPassengers) {
		
		if (maxPassengers < 0) {
			
			throw new IllegalArgumentException("Invalid value for max passengers");
		}
		
		this.maxPassengers = maxPassengers;
	}
	
	public void setPassengers(ArrayList<Passenger> passengers) {
		
		for (int i = 0; i < passengers.size(); i++) {
			
			this.passengers.add(i, passengers.get(i));
		}
		
	}
	
	
	
	@Override
	public String toString() {
		
		StringBuilder toString = new StringBuilder();
		
		toString.append(super.toString());
		toString.append("\nMax Passengers: " + this.maxPassengers);
		toString.append("\nPassengers: \n");
		for (int i = 0; i < this.passengers.size(); i++) {
			
			toString.append("\n" + this.passengers.get(i).getFirstName() + " " +this.passengers.get(i).getLastName());
			
		}
		
		return toString.toString();
		
	}
	
	
}