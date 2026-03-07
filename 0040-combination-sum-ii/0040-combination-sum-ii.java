class Solution {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);

        backtrack(candidates, target, 0, new ArrayList<>(), ans);

        return ans;
    }

    void backtrack(int[] arr, int target, int index, List<Integer> temp, List<List<Integer>> ans){

        if(target == 0){
            ans.add(new ArrayList<>(temp));
            return;
        }

        for(int i = index; i < arr.length; i++){

            if(i > index && arr[i] == arr[i-1])
                continue;

            if(arr[i] > target)
                break;

            temp.add(arr[i]);              // (1) CHOOSE

            backtrack(arr, target - arr[i], i + 1, temp, ans);   // (2) EXPLORE

            temp.remove(temp.size() - 1);  // (3) BACKTRACK
        }
    }
}