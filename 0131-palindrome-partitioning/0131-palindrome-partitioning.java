import java.util.*;

class Solution {

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();

        backtrack(0, s, path, ans);
        return ans;
    }

    public void backtrack(int start, String s, List<String> path, List<List<String>> ans) {

        if(start == s.length()){
            ans.add(new ArrayList<>(path));
            return;
        }

        for(int end = start; end < s.length(); end++){

            if(isPalindrome(s, start, end)){

                path.add(s.substring(start, end+1));

                backtrack(end+1, s, path, ans);

                path.remove(path.size()-1);
            }
        }
    }

    public boolean isPalindrome(String s, int l, int r){

        while(l < r){
            if(s.charAt(l) != s.charAt(r))
                return false;

            l++;
            r--;
        }

        return true;
    }
}