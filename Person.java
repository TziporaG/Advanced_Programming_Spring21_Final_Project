package project;
import java.time.*;

	public class Person {
		
		private String firstName;
		private String lastName;
		private Gender gender;
		private LocalDate birthdate;
		private Address address;
		
		
		public Person(String f, String l, Gender g, Address add, String birthdate) {
			
			this.firstName = f;
			this.lastName = l;
			this.gender = g;
			this.birthdate = LocalDate.parse(birthdate);
			this.address = new Address(add);
			
		}
	
		public Person(String f, String l, Gender g, String s, String c, String state, String zip, String birthdate) {
			
			this(f, l, g, new Address(s, c, state, zip), birthdate);
		}
	
		public Person(String f, String l, Gender g, Address add, LocalDate birthdate) {
		
			this.firstName = f;
			this.lastName = l;
			this.gender = g;
			this.birthdate = birthdate;
			this.address = add;
		
		}
	
		public Person(Person p) {
		
			this(p.firstName, p.lastName, p.gender, p.address, p.birthdate);
	
		}
		public Person () {
			
		}
		
		public String getFirstName() {
		
			return this.firstName;
		}
	
		public String getLastName() {
		
			return this.lastName;
		}
	
		public Gender getGender() {
		
			return this.gender;
		}
	
		public LocalDate getBirthDate() {
		
			return this.birthdate;	
		}
	
		public Address getAddress() {
		
			return new Address(this.address);
		}
	
		public void setLastName(String last) {
		
			this.lastName = last;
		}
	
		//overloaded methods
		public void setAddress(Address addr) {
		
			this.address = new Address(addr);
		
		}
	
		public void setAddress(String s, String c, String state, String zip) {
		
			new Address(s, c, state, zip);
		}
	
		@Override
		public String toString() {
		
			StringBuilder toString = new StringBuilder();
			toString.append("First Name: " + this.firstName);
			toString.append("\nLast Name: " + this.lastName);
			toString.append("\nGender: " + this.gender);
			toString.append("\nBirthdate: " + this.birthdate);
			toString.append(this.address.toString());
		
			return toString.toString();
		}
	
		//compare only based on first name, last name and birth date
		@Override
		public boolean equals(Object o) {
		
			if (this == o) {
				return true;
			}
			
			if(o == null) {
				return false;
			}
			
			if (this.getClass() != o.getClass()) {
				return false;
			}
			
			Person compareToPersonObject = (Person) o;
		
			if(this.lastName == null) {
				if(compareToPersonObject.lastName == null) {
					return false;
					}
				}
			else if(!this.lastName.equals(compareToPersonObject.lastName)) {
				return false;		
			}
		
			if(this.firstName == null) {
				if(compareToPersonObject.firstName == null) {
					return false;
					}
				}
			else if(!this.firstName.equals(compareToPersonObject.firstName)) {
				return false;			
			}
		
			if(!this.birthdate.isEqual(compareToPersonObject.birthdate)) {
					return false;
			}
		
			return true;
					
		}
		
	
}
