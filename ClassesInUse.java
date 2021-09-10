package project;

import java.util.*;
import java.time.*;

public class ClassesInUse {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Welcome! Here you can keep track of your information about your Cruise and Cargo Ships.");
		
		//get array of cruise ships
		System.out.println("\nEnter the details about each cruise ship you currently have.");
		ArrayList<CruiseShip> cruiseShips = getCruiseShipsArrayList(input);
		
		//get array of cargo ships
		System.out.println("\nGreat! Now enter the details about each cargo ship you currently have.");
		ArrayList<CargoShip> cargoShips = getCargoShipsArrayList(input);
		
		//print the menu until user decides to quit the program
		menu(input, cruiseShips, cargoShips);
		
		System.out.println("Thank you for using!");
		input.close();
	}
	
	/** menu Prints menu of actions and goes to corresponding method
	 * @param input Scanner object
	 * @param cruiseShips Array of Cruise Ships to send to methods
	 * @param cargoShips Array of Cargo ships to send to methods
	 */
	public static void menu(Scanner input, ArrayList<CruiseShip> cruiseShips, ArrayList<CargoShip> cargoShips) {
		
		
		System.out.println("\nWhat would you like to do next?");
	
		System.out.println("1. Display details about your cruise ships and add passengers.");
		System.out.println("2. Display details about your cruise ships and remove passengers.");
		System.out.println("3. Display details about your cargo ships.");
		System.out.println("4. Add a cruise ship to the list.");
		System.out.println("5. Add a cargo ship to the list.");
		System.out.println("6. End the program");
		System.out.print("---------");
		System.out.print("\nChoice: ");
		int choice = input.nextInt();
		
		//input validation for choice
		while (choice < 0 || choice > 6) {
			System.out.println("Invalid choice. Please pick again: ");
			choice = input.nextInt();
		}
		
		switch(choice) {
		
		//go to appropriate method call
		case 1:
			input.nextLine();
			addPassenger(cruiseShips, input);
			menu(input, cruiseShips, cargoShips);
			break;
		case 2:
			input.nextLine();
			removePassenger(cruiseShips, input);
			menu(input, cruiseShips, cargoShips);
			break;
		case 3:
			cargoShipDisplay(cargoShips);
			input.nextLine();
			menu(input, cruiseShips, cargoShips);
			break;
		case 4:
			input.nextLine();
			addCruise(cruiseShips, input);
			input.nextLine();
			menu(input, cruiseShips, cargoShips);
			break;
		case 5:
			input.nextLine();
			addCargo(cargoShips, input);
			input.nextLine();
			menu(input, cruiseShips, cargoShips);
			break;
		case 6:
			break;
		}
	
	}
	
	/**
	 * getCruiseShipsArrayList gets the array of cruise ship objects
	 * @param input Scanner object
	 * @return An ArrayList of cruise ship objects
	 */
	public static ArrayList<CruiseShip> getCruiseShipsArrayList(Scanner input) {
		
		System.out.print("\nHow many cruise ships do you have? ");
		int amountCruiseShips = input.nextInt();
		
		while (amountCruiseShips < 0 ) {
			
			System.out.print("Please enter an amount greater than or equal to zero. Please re-enter amount of cruise ships: ");
			amountCruiseShips = input.nextInt();
		}
		
		//create ArrayList of cruise ship objects
		ArrayList<CruiseShip> cruiseShips = new ArrayList<CruiseShip> ();
		
		for(int i = 0; i < amountCruiseShips; i ++) { 
			
			System.out.print("\nEnter the details for cruise ship " + (i+1));
			input.nextLine();
			
			System.out.print("\nShip Name: ");
			String shipName = input.nextLine();
			
			System.out.print("Year built: ");
			int yearBuilt = input.nextInt();
			
			//do not accept years prior to 1900
			while (yearBuilt <= 1900 || yearBuilt > LocalDate.now().getYear() ) {
				System.out.print("Invalid Year, must be from 1900 - now. Please re-enter: ");
				yearBuilt = input.nextInt();
			}
			
			System.out.print("Maximum passenger amount: ");
			int maxPassengers = input.nextInt();
			
			while (maxPassengers < 0 ) {
				
				System.out.print("Max Passenger amount can not be less than zero. Please re-enter: ");
				maxPassengers = input.nextInt();
			}
			
			//fills the array of passengers
			ArrayList<Passenger> passengerList =  getPassengerArrayList(input, maxPassengers);
			
			try {
			cruiseShips.add(new CruiseShip(shipName, yearBuilt, maxPassengers, passengerList));
			}
			
			catch (IllegalArgumentException exp) {
				System.out.println(exp.getMessage() + "\nPlease input a valid year:");
				yearBuilt = input.nextInt();
				
			}
			
		}
			
		return cruiseShips;	
	}	
	
	
	/**
	 * getPassengerArrayList Gets the ArrayList of passengers for every cruise ship object
	 * @param input Scanner object
	 * @param maxPassengers The maximum passenger amount for the cruise ship
	 * @return The ArrayList of passengers
	 */
	public static ArrayList<Passenger> getPassengerArrayList(Scanner input, int maxPassengers) {
		
		ArrayList<Passenger> passengerList = new ArrayList<Passenger> ();
		
		System.out.print("Current Passenger Amount: ");
		int amountPassengers = input.nextInt();
		
		//input validation
		while(amountPassengers > maxPassengers || amountPassengers < 0) {
			
			System.out.print("Passenger amount must be greater than zero and less than the max passenger amount!");
			
			System.out.print("Current Passenger Amount: ");
			amountPassengers = input.nextInt();
		}
		
		//call the getPassenger method to add the passenger to the list
		for(int i = 0; i < amountPassengers; i ++) { 	
			
			System.out.println("\nEnter the passenger details for passenger " + (i+1) + ":");
			
			Passenger tempPassenger = getPassenger(input);
			
			//check passenger is not already on the list
			if(!passengerList.contains(tempPassenger ))
			{
				try {
					passengerList.add(tempPassenger);
				}
			
				catch(PassengerExistsException exc) {
					System.out.print(exc.getMessage());
				}
			}
			
			else {
				System.out.println("\nThat Passenger already is on the list!");
			}
		}
		return passengerList;
	}
	

	/**
	 * getPassenger Gets and returns a passenger object
	 * @param input Scanner object
	 * @return A passenger object
	 */
	public static Passenger getPassenger(Scanner input) {
		
		input.nextLine();
		System.out.print("\nFirst name: ");
		String fName = input.nextLine();
		
		System.out.print("Last name: ");
		String lName = input.nextLine();
		
		//input validation for the gender enum
		System.out.print("Gender (Male/Female): ");
		String gender = input.nextLine();
		
		while(!gender.equalsIgnoreCase("FEMALE") && !gender.equalsIgnoreCase("MALE") ) {
			
			System.out.print("Please type in either 'Male' or 'Female' for the passenger gender. Please re-enter: ");
			gender = input.nextLine();
		}
		
		Gender genderEnum = Gender.valueOf(gender.toUpperCase());
		
		System.out.print("Address: ");
		System.out.print("\n     Street Address: ");
		String street = input.nextLine();
		
		System.out.print("     City: ");
		String city = input.nextLine();
		
		System.out.print("     State: ");
		String state = input.nextLine();
		
		System.out.print("     Zip Code: ");
		String zipCode = input.nextLine();
		
		//input validation for zipcode
		while (zipCode.length() < 5 || zipCode.length() > 5) {
			
			System.out.print("Zip code must be 5 digits. Please re-enter: ");
			zipCode = input.nextLine();
			
		}
		
		System.out.print("Birth Date(YYYY-MM-DD): ");
		String birthdate = input.nextLine();
		
		System.out.print("Passport ID: ");
		int passportID = input.nextInt();
		
		//convert to a string to check if 9 digits
		String passIDString = String.valueOf(passportID);
		while (passIDString.length() < 9 || passIDString.length() > 9) {
			
			System.out.print("Passport ID must be 9 digits. Please re-enter: ");
			passportID = input.nextInt();
			passIDString = String.valueOf(passportID);
		}
		
		
		System.out.print("Amount of luggage: ");
		int luggagePeices = input.nextInt();
		
		//input validation for luggage
		while (luggagePeices < 0) {
			
			System.out.print("Luggage Peices can not be less than zero. Please re-enter: ");
			luggagePeices = input.nextInt();
		}
		
		//tries to add the passenger. If an exception is thrown, will add a null passenger to the list
		try {
			return new Passenger(fName, lName, genderEnum, street, city, state, zipCode, birthdate, passportID, luggagePeices);
		}
		
		catch (InvalidStateException exc) {
			System.out.print("\n" + exc.getMessage());
			System.out.print("\nPassenger details are incorrect. This passenger will be added without their information.");
		}
		
		catch (InvalidZipCode exc ) {
			System.out.print("\n" + exc.getMessage());
			System.out.print("\nPassenger details are incorrect. This passenger will be added without their information.");

		}
		
		catch(InvalidPassportID exc) {
			System.out.print("\n" + exc.getMessage());
			System.out.print("\nPassenger details are incorrect. This passenger will be added without their information.");
		}
		
		catch (java.time.format.DateTimeParseException exc) {
			System.out.print("\nInvalid date.");
			System.out.print("\nPassenger details are incorrect. This passenger will be added without their information.");
		}
		return new Passenger();
		
	}
	
	
	/**
	 * getCargoShipsArrayList Returns an array of cargo ship objects
	 * @param input Scanner object
	 * @return array of Cargo ship objects
	 */
	public static ArrayList<CargoShip> getCargoShipsArrayList(Scanner input) {
		
		ArrayList<CargoShip> cargoShips = new ArrayList<CargoShip> ();
		
		System.out.print("\nHow many cargo ships do you have? ");
		int amountCargoShips = input.nextInt();
		
		
		for(int i = 0; i < amountCargoShips; i ++) { 
			input.nextLine();
			
			System.out.print("\nEnter the details for ship " + (i+1));
			
			System.out.print("\nEnter the Ship Name: ");
			String shipName = input.nextLine();
		
			System.out.print("Enter the year built: ");
			int yearBuilt = input.nextInt();
			
			//do not accept years prior to 1900
			while (yearBuilt <= 1900 || yearBuilt > LocalDate.now().getYear() ) {
				System.out.print("Invalid Year, must be from 1900 - now. Please re-enter: ");
				yearBuilt = input.nextInt();
			}
		
			System.out.print("Enter the cargo capacity (in tons): ");
			int cargoCapacity = input.nextInt();
			
			while (cargoCapacity <= 0) {
				
				System.out.print("Cargo Capacity can not be less than or equal to 0. Please re-enter: ");
				cargoCapacity = input.nextInt();
			}
			
			try {
			cargoShips.add(new CargoShip(shipName, yearBuilt, cargoCapacity));
			
			}
			
			catch(IllegalArgumentException exc) {
				System.out.print(exc.getMessage());
			}
		}
			
		
		return cargoShips;
		
		
	}


		/** addPassenger Allows the user to add a passenger to any cruise ship in the array
		 * @param cruiseShips Array of cruise ship objects
		 * @param input Scanner object
		 */
		public static void addPassenger(ArrayList<CruiseShip> cruiseShips, Scanner input) {
			
			//if there are no cruise ships, allow user to add
			if(cruiseShips.size() == 0) {
				getCruiseShipsArrayList(input);
			}
			
			//print the cruise ship and the array of passengers
			for(int i = 0; i < cruiseShips.size(); i ++) {
				System.out.print("--------------\n");
				System.out.println("    Ship " + (i+1));
				System.out.print("--------------");
				System.out.print("");
				
				System.out.print(cruiseShips.get(i).toString());
				
				//only ask to add if there is room on the ship
				if (cruiseShips.get(i).getPassengers().size() < cruiseShips.get(i).getMaxPassengers()) {
					
				System.out.println("\n\nWould you like to add a passenger to this ship? (y/n) ");
				String addPass = input.nextLine();
				
				
				//if passenger amount is less than max passenger amount, allow user to add a passenger
				while(addPass.equalsIgnoreCase("y") && (cruiseShips.get(i).getPassengers().size()) < (cruiseShips.get(i).getMaxPassengers())) {
					
					System.out.println("\nEnter the passenger details for the additional passenger (Press enter when ready):");
					
					Passenger p = getPassenger(input);
					
					//throw passenger exists exception if passenger is already in the list
					try { 
						if(!cruiseShips.get(i).getPassengers().contains(p)) {
						cruiseShips.get(i).addPassenger(p);
						}
						else {
							System.out.println("\nThat Passenger already is on the list! They will not be added.");
						}
					}
					
					catch (PassengerExistsException e) {
						
						e.getMessage();
					}
					
					input.nextLine();
					
					//only ask to add again if there is room on the ship
					if (cruiseShips.get(i).getPassengers().size() < cruiseShips.get(i).getMaxPassengers()) {
						
					System.out.println("\n\nWould you like to add a passenger to this ship? (y/n) ");
					addPass = input.nextLine();;
					}
					
					else {
						System.out.print("\nThis cruise ship is full.");
						}
					
					}
					}	
				else {
					System.out.print("\nThis cruise ship is full.");
				}
				
				}
			}
		
		
		/**
		 * removePassenger Allows user to remove a passenger from the list
		 * @param cruiseShips Array of cruiseShip objects
		 * @param input Scanner object
		 */
		public static void removePassenger(ArrayList<CruiseShip> cruiseShips, Scanner input) {
			
			if(cruiseShips.size() == 0) {
				System.out.print("There are no ships on the list currently");
				return;
			}
			
			//print the cruise ship and the array of passengers
			for(int i = 0; i < cruiseShips.size(); i ++) {
				System.out.print("--------------\n");
				System.out.print("      Ship " + (i+1));
				System.out.print("\n--------------");
				System.out.print("");
				
				System.out.print(cruiseShips.get(i).toString());
				
				//don't give the option to remove if there are no passengers on the ship
				if (cruiseShips.get(i).getPassengers().size() > 0) {
				System.out.print("\nWould you like to remove a passenger from this ship? (y/n) ");
				String removePass = input.nextLine();
				
				while(removePass.equalsIgnoreCase("y") && (cruiseShips.get(i).getPassengers().size()) >= 1) {
					
					//get the index of the passenger to be removed
					System.out.println("Which number passenger in the list would you like to remove? (Enter 1 for the first, 2 for the second, etc.)");
					
					int toRemove = input.nextInt();
					
					//if the index is out of bounds, user must choose a different index
					while (toRemove < 0 || toRemove > cruiseShips.get(i).getPassengers().size()) {
						System.out.println("Invalid choice. Please pick again.");
						toRemove = input.nextInt();
					}
					
					try {
					//minus one from user's choice to get correct index
					cruiseShips.get(i).removePassenger(toRemove-1);
					}
					
					catch(ArrayIndexOutOfBoundsException exc) {
						System.out.print("Passenger not found.");
					}
					input.nextLine();
					
					if (cruiseShips.get(i).getPassengers().size() > 0) {
						System.out.print("Would you like to remove another passenger to this ship? (y/n) ");
						removePass = input.nextLine();
					}
					else {
						System.out.print("There are no more passengers on the ship.\n");
					}
					
					}
				}
				else {
					System.out.print("There are no more passengers on the ship.\n");
				}
			}
			
			}
		
		/**
		 * cargoShipDisplay Displays the cargoShip objects from the array
		 * @param cargoShips Array of cargo ship objects
		 */
		public static void cargoShipDisplay(ArrayList<CargoShip> cargoShips) {
			
			if(cargoShips.size() == 0 ) {
				System.out.println("There are no ships to display.");
				return;
			}
			
			for(int i = 0; i < cargoShips.size(); i ++) {
				System.out.println("Ship " + (i+1));
				System.out.println("----------");
				System.out.println(cargoShips.get(i).toString());
			}
		}
	
		/**
		 * addCargo Adds a cargo ship object to the array
		 * @param cargoShips Array of cargo ships
		 * @param input Scanner object
		 */
	public static void addCargo(ArrayList<CargoShip> cargoShips, Scanner input) {
		
		System.out.print("\nEnter the Ship Name: ");
		String shipName = input.nextLine();
		
		System.out.print("Enter the year built: ");
		int yearBuilt = input.nextInt();
		
		//do not accept years prior to 1900
		while (yearBuilt <= 1900 || yearBuilt > LocalDate.now().getYear() ) {
			System.out.print("Invalid Year, must be from 1900 - now. Please re-enter: ");
			yearBuilt = input.nextInt();
		}
	
		System.out.print("Enter the cargo capacity (in tons): ");
		int cargoCapacity = input.nextInt();
		
		while (cargoCapacity <= 0) {
			
			System.out.print("Cargo Capacity can not be less than or equal to 0. Please re-enter: ");
			cargoCapacity = input.nextInt();
		}
		
		try {
		cargoShips.add(new CargoShip(shipName, yearBuilt, cargoCapacity));
		}
		
		catch (IllegalArgumentException exp) {
			System.out.println(exp.getMessage());
			
		}	
			
	}
	
	/**
	 * addCruise Adds a cruise ship to the list
	 * @param cruiseShips Array of cruise ships
	 * @param input Scanner object
	 */
	public static void addCruise(ArrayList<CruiseShip> cruiseShips, Scanner input) {
		
		System.out.print("\nEnter the details for cruise ship");
		
		System.out.print("\nShip Name: ");
		String shipName = input.nextLine();
		
		System.out.print("Year built: ");
		int yearBuilt = input.nextInt();
		
		//do not accept years prior to 1900
		while (yearBuilt <= 1900 || yearBuilt > LocalDate.now().getYear() ) {
			System.out.print("Invalid Year. Please re-enter: ");
			yearBuilt = input.nextInt();
		}
			
		System.out.print("Maximum passenger amount: ");
		int maxPassengers = input.nextInt();
		
		while (maxPassengers < 0 ) {
			
			System.out.print("Max Passenger amount can not be less than zero. Please re-enter: ");
			maxPassengers = input.nextInt();
		}
		
		ArrayList<Passenger> passengerList =  getPassengerArrayList(input, maxPassengers);
		
		try {
		cruiseShips.add(new CruiseShip(shipName, yearBuilt, maxPassengers, passengerList));
		}
		
		catch (IllegalArgumentException exp) {
			System.out.println(exp.getMessage());
			
		}
	}
}
	