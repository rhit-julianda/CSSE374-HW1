
public class ILTax implements Tax {

	@Override
	public Double calculateTax(Double price) {
		return price * 0.0874;
	}

}
