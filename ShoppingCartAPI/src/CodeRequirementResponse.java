import java.util.HashMap;

public class CodeRequirementResponse extends Response {
	private HashMap<Item, Integer> requirements;
	
	public CodeRequirementResponse(HashMap<Item, Integer> reqs) {
		this.requirements = reqs;
	}
}
