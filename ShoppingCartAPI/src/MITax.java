
public class MITax implements Tax {

	@Override
	public Double calculateTax(Double price) {
		return price * 0.06;
	}

}
