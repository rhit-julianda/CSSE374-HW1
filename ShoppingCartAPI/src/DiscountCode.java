import java.util.Date;
import java.util.HashMap;

public class DiscountCode {
	private String code;
	private Double percentage;
	private Date expirationDate;
	private HashMap<Item, Integer> requirements;
	
	DiscountCode(String code, Double percent, Date exp, HashMap<Item, Integer> reqs) {
		this.code = code;
		this.percentage = percent;
		this.expirationDate = exp;
		this.requirements = reqs;
	}
	
	public boolean checkValid(HashMap<Item, Integer> cartContents) {
		if(new Date().compareTo(expirationDate) > 0) {
			return false;
		} else {
			for (Item i : this.requirements.keySet()) {
				if ((cartContents.get(i) == null) ||
					(cartContents.get(i) < requirements.get(i))) {
					return false;
				}
			}
			return true;
		}
	}
	
	public String getCode() {
		return this.code;
	}
	
	public Double getPercentage() {
		return this.percentage;
	}
}
