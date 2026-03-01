import java.util.*;

class Solution {
    public int countDistinct(int[] nums, int k, int p) {
        int n = nums.length;
        Set<String> set = new HashSet<>();
        
        for(int i = 0; i < n; i++) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            
            for(int j = i; j < n; j++) {
                
                if(nums[j] % p == 0) {
                    count++;
                }
                
                if(count > k) break;
                
                sb.append(nums[j]).append(",");
                set.add(sb.toString());
            }
        }
        
        return set.size();
    }
}