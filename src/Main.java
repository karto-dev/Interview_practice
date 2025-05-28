
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        System.out.println(map.get("A")); // Output: 1
        System.out.println(map.get("B")); // Output: 2
        map.remove("A");
        System.out.println(map.get("A")); // Output: null
    }
}