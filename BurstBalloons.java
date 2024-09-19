// Time Complexity: O(n^3) 
// Space Complexity: O(n^2)

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;

        int[][] dp = new int[n][n];

        // sub problems of diff length
        for (int len = 1; len <= n; len++) {
            // subarray of a particular len what will the start(i) and end index of an arr'
            // start as i and end as j
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                int max = Integer.MIN_VALUE;
                for (int k = i; k <= j; k++) {
                    // __before__ + _left * kth * right__ + __after__
                    int before = 0;
                    if (k > i)
                        before = dp[i][k - 1];

                    int after = 0;
                    if (k < j)
                        after = dp[k + 1][j];

                    int left = 1;
                    int right = 1;

                    if (i > 0)
                        left = nums[i - 1];

                    if (j < n - 1)
                        right = nums[j + 1];

                    max = Math.max(max, before + left * nums[k] * right + after);
                }
                dp[i][j] = max;
            }
        }

        return dp[0][n - 1];
    }
}