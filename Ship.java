package project;
import java.time.*;

public class Ship {
	
	private String shipName;
	private int yearBuilt;
	
	public Ship(String shipName, int yearBuilt) {
		
		this.shipName = shipName;
		
		//we assume we are not going into BC years
		if (yearBuilt <= 0 || yearBuilt > LocalDate.now().getYear() ) {
			throw new IllegalArgumentException("Invalid year");
		}
		
		this.yearBuilt = yearBuilt;
	}
	
	public Ship(Ship ship) {
		
		this(ship.shipName, ship.yearBuilt);
	}
	
	public Ship() {
		
	}
	
	public void setShipName(String shipName) {
		
		this.shipName = shipName;
	}
	
	public void setYearBuilt(int yearBuilt) {
		
		//we assume we are not going into BC years
		if (yearBuilt <= 0 || yearBuilt > LocalDate.now().getYear() ) {
			throw new IllegalArgumentException("Invalid year");
			}
		this.yearBuilt = yearBuilt;
	}
	
	
	public String getShipName() {
		
		return this.shipName;
	}
	
	public int getYearBuilt() {
		
		return this.yearBuilt;
	}
	
	@Override
	public String toString() {
		
		StringBuilder toString = new StringBuilder();
		toString.append("\nShip Name: " + this.shipName);
		toString.append("\nYear Built: " + this.yearBuilt);
		
		return toString.toString();
	}
	
	@Override
	public boolean equals(Object otherObject) {
		
		if(this == otherObject) {
			return true;
		}
		
		if(otherObject == null) {
			return false;
		}
		
		if(this.getClass() != otherObject.getClass()) {
			return false;
		}
		
		Ship otherShipObject = (Ship) otherObject ;
		
		if(this.shipName == null) {
			if(otherShipObject.shipName == null) {
				return false;
			}
		}
		
		else if(!this.shipName.equals(otherShipObject.shipName)) {
			return false;
		}
		
		if(this.yearBuilt != otherShipObject.yearBuilt) {
			return false;
		}
		
		return true;
		
	}
	
	
	
	
}