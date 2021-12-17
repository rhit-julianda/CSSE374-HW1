
public class OutOfStockResponse extends Response {
	private Item item;
	
	public OutOfStockResponse(Item i) {
		this.item = i;
	}
}
