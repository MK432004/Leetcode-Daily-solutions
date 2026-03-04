import java.util.*;

class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        
        // Step 1: Frequency Map
        Map<String, Integer> map = new HashMap<>();
        for(String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        // Step 2: Convert to list and sort
        List<String> list = new ArrayList<>(map.keySet());
        
        Collections.sort(list, (a, b) -> {
            if(map.get(a).equals(map.get(b))) {
                return a.compareTo(b);   // lexicographical
            }
            return map.get(b) - map.get(a);  // frequency descending
        });
        
        // Step 3: Return first k elements
        return list.subList(0, k);
    }
}