
public class INTax implements Tax {

	@Override
	public Double calculateTax(Double price) {
		return price * 0.07;
	}

}
