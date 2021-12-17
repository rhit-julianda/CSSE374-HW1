
public class OHTax implements Tax {

	@Override
	public Double calculateTax(Double price) {
		return price * 0.0717;
	}

}
