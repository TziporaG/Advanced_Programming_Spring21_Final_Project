package project;

public class CargoShip extends Ship {
	
	private int cargoCapacity;
	
	public CargoShip(String shipName, int yearBuilt, int cargoCapacity) {
		
		super(shipName, yearBuilt);
		
		if(cargoCapacity < 0) {
			
			throw new IllegalArgumentException("Invalid value for cargo capacity");
		}
		
		this.cargoCapacity = cargoCapacity;
	}
	
	public CargoShip(Ship cargoShip, int cargoCapacity) {
		
		super(cargoShip.getShipName(), cargoShip.getYearBuilt());
		
		if(cargoCapacity < 0) {
			
			throw new IllegalArgumentException("Invalid value for cargo capacity");
		}
		
		this.cargoCapacity = cargoCapacity;
	}
	
	public CargoShip(CargoShip cargoShip) {
		
		super(cargoShip.getShipName(), cargoShip.getYearBuilt());
		
		this.cargoCapacity = cargoShip.cargoCapacity;
		
	}
	
	public CargoShip()
	{
		
	}
	
	public void setCargoCapacity(int cargoCapacity) {
		
		if(cargoCapacity <= 0) {
			
			throw new IllegalArgumentException("Invalid value for cargo capacity");
		}
		
		this.cargoCapacity = cargoCapacity;
	}
	
	public int getCargoCapacity() {
		
		return this.cargoCapacity;
	}
	
	@Override
	public String toString() {
		
		StringBuilder toString = new StringBuilder();
		
		toString.append("Ship name: " + this.getShipName());
		toString.append("\nCargo Capacity: " + this.cargoCapacity);
		
		return toString.toString();
	}
	
	
	
}