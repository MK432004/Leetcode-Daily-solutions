class Solution {
   public List<List<Integer>> combinationSum(int[] nums, int target) {
       Arrays.sort(nums); // Enables pruning
       List<List<Integer>> result = new ArrayList<>();
       List<Integer> curr = new ArrayList<>();
       backtrack(nums, target, 0, curr, result);
       return result;
   }


   private void backtrack(int[] nums, int target, int start, List<Integer> curr, List<List<Integer>> result) {
       if (target == 0) {
           result.add(new ArrayList<>(curr)); // copy the current combination
           return;
       }


       for (int i = start; i < nums.length; i++) {
           if (nums[i] > target) break; // Prune the path


           curr.add(nums[i]);
           backtrack(nums, target - nums[i], i, curr, result); // not i+1 because we can reuse the same element
           curr.remove(curr.size() - 1); // Backtrack
       }
   }
}










