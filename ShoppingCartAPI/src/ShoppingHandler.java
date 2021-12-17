import java.util.ArrayList;
import java.util.HashMap;

public class ShoppingHandler {
	private HashMap<String, Cart> carts;
	private HashMap<Item, Integer> itemInv;
	private ArrayList<DiscountCode> codes;

	ShoppingHandler() {
		this.pullCarts();
		this.pullInventory();
		this.pullCodes();
	}
	
	public Response handleRequest(Request req) {
		this.pullInventory();
		Cart targetCart = this.carts.get(req.getID());
		if (req instanceof DisplayRequest) {
			return targetCart.display();
		} else if (req instanceof AddItemRequest) {
			if
			return targetCart.addItem();
		} else
			return null;
		}
	}
		
	public void pullCarts() {
		return;
	}
		
	public void pullInventory() {
		return;
	}
	
	public void pullCodes() {
		return;
	}
}
