import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Cart {
	private Double total;
	private String userID;
	private int invalidAttempts;
	private Date[] attemptTimes;
	private boolean isRejectingCodes;
	private Address address;
	private ArrayList<DiscountCode> appliedCodes;
	private HashMap<Item, Integer> items;
	
	public Cart(String userID, Address address) {
		this.total = 0.0;
		this.userID = userID;
		this.invalidAttempts = 0;
		this.attemptTimes = new Date[5];
		this.isRejectingCodes = false;
		this.address = address;
		this.appliedCodes = new ArrayList<DiscountCode>();
		this.items = new HashMap<Item, Integer>();
	}
	
	/**
	 * TODO: Add tax calculation and discount code support.
	 * @return
	 */
	public Double calculateTotal() {
		this.total = 0.0;
		for(Item i: this.items.keySet()) {
			this.total += i.calculatePrice(this.items.get(i));
		}
		return total;
	}
	
	public Response display() {
		return null;
	}
}
