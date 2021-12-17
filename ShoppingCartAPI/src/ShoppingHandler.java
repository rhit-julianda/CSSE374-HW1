import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ShoppingHandler {
	private HashMap<String, Cart> carts;
	private HashMap<Item, Integer> itemInv;
	private HashMap<String, DiscountCode> codes;

	ShoppingHandler() {
		this.pullCarts();
		this.pullInventory();
		this.pullCodes();
	}
	
	public Response handleRequest(Request req) {
		this.pullInventory();
		this.pullCodes();
		Cart targetCart = this.carts.get(req.getID());
		if (req instanceof DisplayRequest) {
			Response res = targetCart.display();
			this.pushCarts();
			return res;
		} else if (req instanceof AddItemRequest) {
			Item targetItem = ((AddItemRequest) req).getItem();
			int stock = this.itemInv.get(targetItem);
			if (stock > 0) {
				Response res = targetCart.addItem(targetItem);
				this.pushCarts();
				return res;
			} else {
				return new MessageResponse("This item is currently out of stock.");
			}
		} else if (req instanceof ApplyCodeRequest) {
			String codestring = ((ApplyCodeRequest) req).getCode();
			if (this.codes.containsKey(codestring)) {
				Response res = targetCart.applyDiscount(this.codes.get(codestring));
				this.pushCarts();
				return res;
			} else {
				return new MessageResponse("This is not an existing code.");
			}
		} else if (req instanceof ModifyQuantityRequest) {
			Item targetItem = ((ModifyQuantityRequest) req).getItem();
			Integer quantity = ((ModifyQuantityRequest) req).getQuantity();
			int stock = this.itemInv.get(targetItem);
			if (stock - quantity >= 0) {
				Response res = targetCart.updateQuantity(targetItem, quantity);
				this.pushCarts();
				return res;
			} else if (stock == 0){
				targetCart.updateQuantity(targetItem, 0);
				this.pushCarts();
				return new OutOfStockResponse(targetItem);
			}
		}
		return null;
	}
		
	public void pullCarts() {
		String line = "";
		try {
			Scanner s = new Scanner(new File("carts.csv"));
			s.nextLine();
			while (s.hasNext()) {
				line = s.nextLine();
				String[] cString = line.split(",");
				Cart cart = new Cart(cString[0], new Address(cString[1], 
						cString[2], Enum.valueOf(State.class, cString[3]), Integer.parseInt(cString[4])));
				this.carts.put(cString[0], cart);
			}
			s.close();
		} catch (IOException e) {
			System.out.println("Error occurred reading database");
			System.exit(1);
		}
	}
	
	public void pushCarts() {
		return;
	}
		
	public void pullInventory() {
		String line = "";
		try {
			Scanner s = new Scanner(new File("inventory.csv"));
			s.nextLine();
			while (s.hasNext()) {
				line = s.nextLine();
				String[] iString = line.split(",");
				Integer quantity = Integer.parseInt(iString[5]);
				Item item = new Item(iString[0], iString[1], Double.parseDouble(iString[2]),
						iString[3], Double.parseDouble(iString[6]));
				this.itemInv.put(item, quantity);
			}
			s.close();
		} catch (IOException e) {
			System.out.println("Error occurred reading database");
			System.exit(1);
		}
	}
	
	public void pullCodes() {
		return;
	}
}
