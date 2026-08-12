class Solution {

    public int helper(ArrayList<ArrayList<Integer>> mat, int n, int p, int dp[][]) {

        if (n == 0) return 1;

        if (dp[p][n] != 0) return dp[p][n];

        int k = 0;

        for (int el : mat.get(p)) {
            k = (k + helper(mat, n - 1, el, dp)) % 1000000007;
        }

        return dp[p][n] = k;
    }

    public int knightDialer(int n) {

        ArrayList<ArrayList<Integer>> mat = new ArrayList<>();

        mat.add(new ArrayList<>(List.of(5, 7)));
        mat.add(new ArrayList<>(List.of(6, 8)));
        mat.add(new ArrayList<>(List.of(3, 7)));
        mat.add(new ArrayList<>(List.of(2, 8, 9)));
        mat.add(new ArrayList<>(List.of()));
        mat.add(new ArrayList<>(List.of(0, 6, 9)));
        mat.add(new ArrayList<>(List.of(1, 5)));
        mat.add(new ArrayList<>(List.of(0, 2)));
        mat.add(new ArrayList<>(List.of(3, 1)));
        mat.add(new ArrayList<>(List.of(3, 5)));

        int dp[][] = new int[10][n];

        int ret = 0;

        for (int i = 0; i < 10; i++) {
            ret = (ret + helper(mat, n - 1, i, dp)) % 1000000007;
        }

        return ret;
    }
}

