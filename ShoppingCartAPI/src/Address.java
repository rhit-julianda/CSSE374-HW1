
public class Address {
	private String street;
	private String city;
	private State state;
	private int ZIP;
	
	public Address(String str, String cit, State sta, int zip) {
		this.street = str;
		this.city = cit;
		this.state = sta;
		this.ZIP = zip;
	}
	
	public State getState() {
		return this.state;
	}
}
