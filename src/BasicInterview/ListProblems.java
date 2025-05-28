package BasicInterview;


import java.util.*;
import java.util.stream.Collectors;

public class ListProblems {

    public static Map<Object, Long> CountOccurrence() {
        List<Integer> integers = List.of(1, 2, 1, 2, 4, 5, 6, 4, 3, 5);
        Map<Integer, Integer> map = new HashMap<>(), map1 = new HashMap<>(), map2 = new HashMap<>();
        for (int i : integers) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        for (int i : integers) {
            map1.putIfAbsent(i, 0);
            map1.put(i, map1.get(i) + 1);
        }
        for (int i : integers) {
            map2.compute(i, (k, v) -> (v != null) ? v + 1 : 1);
        }
        System.out.println(map);
        System.out.println(map1);
        System.out.println(map2);
        return integers.stream().collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }

    public static Map<Integer, List<String>> CountOccurrenceByLength() {
        List<String> Animals = List.of("rat", "hat", "keep", "heat", "battle");
        Map<Integer, List<String>> map = new HashMap<>(), map1 = new HashMap<>();
        for (String animal : Animals) {
            map.computeIfAbsent(animal.length(), k -> new ArrayList<>()).add(animal);
        }
        for (String animal : Animals) {
            int length = animal.length();
            map1.putIfAbsent(length, new ArrayList<>());
            map1.get(length).add(animal);
        }
        System.out.println(map);
        System.out.println(map1);
        return Animals.stream().collect(Collectors.groupingBy(String::length));
    }

    public static Map<Integer, List<Integer>> CountOccurrenceByModulo() {
        List<Integer> numbers = List.of(5, 7, 9, 12, 14, 15, 18, 20);
        Map<Integer, List<Integer>> groupedByModulo = new HashMap<>();
        Map<Integer, List<Integer>> groupedByModulo1 = new HashMap<>();
        for (int num : numbers) {
            int modVal = num % 3;
            groupedByModulo1.putIfAbsent(modVal, new ArrayList<>());
            groupedByModulo1.get(modVal).add(num);
        }

        numbers.forEach(num -> groupedByModulo.computeIfAbsent(num % 3, k -> new ArrayList<>()).add(num));
        System.out.println(groupedByModulo);
        System.out.println(groupedByModulo1);
        return numbers.stream().collect(Collectors.groupingBy(num -> num % 3));
    }

    public static Character CountNonRepeating() {
        String str = "swiss";
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : str.toCharArray()) {
            charCount.compute(c, (r, val) -> (val == null) ? 1 : val + 1);
        }
        for (char c : str.toCharArray()) {
            if (charCount.get(c) == 1) {
                return c;
            }
        }
        return null;
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramGroups = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            anagramGroups.computeIfAbsent(new String(charArray),k->new ArrayList<>()).add(str);
        }
        return new ArrayList<>(anagramGroups.values());
    }


    public static void main(String[] args) {
        System.out.println(CountOccurrence());
        System.out.println(CountOccurrenceByLength());
        System.out.println(CountOccurrenceByModulo());
        System.out.println(CountNonRepeating());
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        ListProblems l = new ListProblems();
    }


}
