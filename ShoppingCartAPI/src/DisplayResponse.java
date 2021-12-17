import java.util.HashMap;

public class DisplayResponse extends Response {
	private HashMap<Item, Integer> items;
	private Double totalDiscount;
	private Double totalCost;
	
	public DisplayResponse(HashMap<Item, Integer> items, Double disc, Double total) {
		this.items = items;
		this.totalDiscount = disc;
		this.totalCost = total;
	}
}
