class Solution {
    public boolean checkValidGrid(int[][] grid) {

        int n = grid.length;

        if(grid[0][0] != 0) return false;

        int[][] pos = new int[n*n][2];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int val = grid[i][j];
                pos[val][0] = i;
                pos[val][1] = j;
            }
        }

        for(int k=1;k<n*n;k++){

            int r1 = pos[k-1][0];
            int c1 = pos[k-1][1];

            int r2 = pos[k][0];
            int c2 = pos[k][1];

            int dr = Math.abs(r1-r2);
            int dc = Math.abs(c1-c2);

            if(!((dr==2 && dc==1) || (dr==1 && dc==2))){
                return false;
            }
        }

        return true;
    }
}
