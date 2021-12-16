
public class Item {
	private String name;
	private String desc;
	private Double price;
	private String pic;
	private Double discountRate;
	
	public Item(String na, String de, Double pr, String pi, Double disc) {
		this.name = na;
		this.desc = de;
		this.price = pr;
		this.pic = pi;
		this.discountRate = disc;
	}
	
	/*
	 * DiscountRate is set to 0.0 by default. The number corresponds to
	 * a percentage off, all the way up to 1.0, which is 100% off.
	 */
	public Double calculatePrice(int quantity) {
		return (this.price - (this.price * discountRate)) * quantity;
	}
}
