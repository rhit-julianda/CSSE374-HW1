
public class AddItemRequest extends Request {
	private Item item;
	
	public AddItemRequest(String id, Item item) {
		this.userID = id;
		this.item = item;
	}
}
