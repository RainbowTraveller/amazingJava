import java.util.Map;
import java.util.TreeMap;
public class TreeMapTest {
	public static void main(String[] args) {
		TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
		tm.put(10, 20);
		tm.put(10, 30);
		//TreeMap does not allow duplicate keys, they are overwritten
		for(Map.Entry<Integer, Integer> e : tm.entrySet()) {
			System.out.println(e.getKey() + " " + e.getValue());
		}
	}
}
