package at.jku.roadmaintenance;
import java.util.HashMap;

public class Resources {

	private static HashMap<String,Integer> resources = new HashMap();
	
	public static void createResource(String name, int quantity) {
		
		resources.put(name.toLowerCase(), quantity);
	}
	
	public static void deleteVehicle(String name) {
		
		resources.remove(name.toLowerCase());
	}
	
	public static int getQuantity(String name) {
		return resources.get(name.toLowerCase());
	}
	
	public static boolean withdrawResources(String name, int quantity) {
		
		if (resources.containsKey(name.toLowerCase())) {
			
			int curquan = resources.get(name.toLowerCase());
			
			if (curquan >= quantity) {
				resources.put(name.toLowerCase(), curquan-quantity);
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean returnResources(String name, int quantity) {
		
		if (resources.containsKey(name.toLowerCase())) {
			
			int curquan = resources.get(name.toLowerCase());
			
			resources.put(name.toLowerCase(), curquan+quantity);
			return true;
		}
	
		return false;
	}
}
