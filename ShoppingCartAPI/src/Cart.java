import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class Cart {
	private Double total;
	private String userID;
	private int invalidAttempts;
	private PriorityQueue<Date> attemptTimes;
	private Address address;
	private Tax tax;
	private ArrayList<DiscountCode> appliedCodes;
	private HashMap<Item, Integer> items;
	
	public Cart(String userID, Address addr) {
		this.total = 0.0;
		this.userID = userID;
		this.invalidAttempts = 0;
		this.attemptTimes = new PriorityQueue<Date>();
		this.address = addr;
		this.appliedCodes = new ArrayList<DiscountCode>();
		this.items = new HashMap<Item, Integer>();
		switch (this.address.getState()) {
		case IL:
			this.tax = new ILTax();
			break;
		case IN:
			this.tax = new INTax();
			break;
		case MI:
			this.tax = new MITax();
			break;
		case OH:
			this.tax = new OHTax();
			break;
		default:
			break;
		}
	}
	
	/**
	 * TODO: Add tax calculation and discount code support.
	 * @return
	 */
	public Double calculateTotal() {
		this.total = 0.0;
		for (Item i: this.items.keySet()) {
			this.total += i.calculatePrice(this.items.get(i));
		}
		Double totalDiscount = 0.0;
		for (DiscountCode c: this.appliedCodes) {
			this.total -= (this.total * c.getPercentage());
			totalDiscount += c.getPercentage();
		}
		Double tax = this.tax.calculateTax(this.total);
		this.total += tax;
		return totalDiscount;
	}
	
	public Response display() {
		Double totalDiscount = this.calculateTotal();
		return new DisplayResponse(this.items, totalDiscount, this.total);
	}
	
	public Response addItem(Item i) {
		this.items.put(i, 1);
		this.calculateTotal();
		return new MessageResponse("Item successfully added to cart.");
	}
	
	public Response applyDiscount(DiscountCode code) {
		Response res = code.checkValid(this.items);
		if (res == null) {
			this.appliedCodes.add(code);
			this.calculateTotal();
			return new TotalResponse(this.total);
		} else {
			int nowHours = (int) (((new Date().getTime() / 1000) / 3600));
			if (this.attemptTimes.peek() != null) {
				while((nowHours - ((this.attemptTimes.peek().getTime() / 1000) / 3600)) > 24) {
					this.attemptTimes.poll();
					this.invalidAttempts--;
					if (this.attemptTimes.peek() == null) {
						break;
					}
				}
			}
			if(this.invalidAttempts == 5) {
				return null;
			} else {
				this.invalidAttempts++;
				this.attemptTimes.offer(new Date());
				return res;
			}
		}
	}
	
	public Response updateQuantity(Item i, Integer quantity) {
		if(quantity == 0) {
			this.items.remove(i);
		} else {
			this.items.put(i, quantity);
		}
		ArrayList<DiscountCode> codesToRemove = new ArrayList<DiscountCode>();
		for(DiscountCode code : this.appliedCodes) {
			if (code.checkValid(this.items) != null) {
				codesToRemove.add(code);
			}
		}
		for (DiscountCode code : codesToRemove) {
			this.appliedCodes.remove(code);
		}
		return this.display();
	}
}
