
public class ApplyCodeRequest extends Request {
	private String code;
	
	public ApplyCodeRequest(String c) {
		this.code = c;
	}
	
	public String getCode() {
		return this.code;
	}
}
