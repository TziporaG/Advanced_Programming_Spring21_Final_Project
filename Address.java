package project;

public class Address {
	
	//For input validation in this class, I am assuming the only valid states/ Zip codes are in the U.S.A
	private String street;
	private String city;
	private String state;
	private String zipcode;
	
	static final String[] states = {"Alabama","Alaska","American Samoa","Arizona","Arkansas",
			"California","Colorado","Connecticut","Delaware","District of Columbia", "Federated States of Micronesia",
			"Florida","Georgia","Guam","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas",
			"Kentucky","Louisiana","Maine","Marshall Islands","Maryland","Massachusetts","Michigan",
			"Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire",
			"New Jersey","New Mexico","New York","North Carolina","North Dakota","Northern Mariana Islands",
			"Ohio","Oklahoma","Oregon","Palau","Pennsylvania","Puerto Rico","Rhode Island","South Carolina",
			"South Dakota","Tennessee","Texas","Utah","Vermont","Virgin Island","Virginia","Washington","West Virginia","Wisconsin","Wyoming", 
			"AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FM", "FL", "GA", "GU", "HI", "ID", 
			"IL", "IN", "IA", "KS", "KY", "LA", "ME", "MH", "MD", 
			"MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", 
			"OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI", "VA", "WA", "WV", "WI", "WY"};
	
	public Address(String street, String city, String state, String zipcode) {
		
		this.street = street;
		this.city = city;
		
		//validate the state exists in the U.S.A
		//loop through the array of states until the boolean notFound is set to false
		boolean notFound = true;
		for(int i = 0; i < states.length; i++) {
				if(states[i].equalsIgnoreCase(state)) {
					notFound = false;
				}	
		}
		
		if(notFound) {
			
			throw new InvalidStateException();
		}
		
		this.state = state;
		
		
		//validate that the Zip code is 5 characters long (necessary in the U.S)
		if(zipcode.length() < 5 || zipcode.length() > 5) {
			
			throw new InvalidZipCode();
		}
		
		this.zipcode = zipcode;
	}
	
	public Address(Address address) {
		
		this(address.getStreet(), address.getCity(), address.getState(), address.getZip() );	
	}
	
	
	public String getStreet() {
		
		return this.street;
	}
	
	public String getCity() {
		
		return this.city;
	}
	
	public String getState() {
		
		return this.state;
	}
	
	
	public String getZip() {
		
		return this.zipcode;
	}
	
	@Override
	public String toString() {
		
		StringBuilder toString = new StringBuilder();
		toString.append("\nAddress: "); 
		toString.append("\n" + this.street);
		toString.append("\n" + this.city + ", " + this.state + this.zipcode);
		
		return toString.toString();
		}
	
	
}