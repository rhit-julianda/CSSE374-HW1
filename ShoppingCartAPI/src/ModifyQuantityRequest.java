
public class ModifyQuantityRequest extends Request {
	private Item item;
	private Integer quantity;
	
	public ModifyQuantityRequest(Item i, Integer q) {
		this.item = i;
		this.quantity = q;
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public Integer getQuantity() {
		return this.quantity;
	}
}
