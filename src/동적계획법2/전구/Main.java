package 동적계획법2.전구;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2449
// DP
// dp[1][3] = min(dp[1][2]+(1과 3이 다르면 1 같으면 0), dp[2][3]+(1과 3이 다르면 1 같으면 0))
// 1 2 2 라고 하면 dp[1][3] = min(2,1) = 1
// dp[
public class Main {
    static int N, M;
    static int[] num;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][N];

        divide(0, N-1);

        System.out.println(dp[0][N-1]);

    }

    static int divide(int start, int end){
        if(start == end) return 0;
        if(dp[start][end] > 0) return dp[start][end];

        dp[start][end] = Integer.MAX_VALUE;
        for(int i = start; i < end; i++){
            int left = divide(start, i);
            int right = divide(i+1, end);

            if(num[start] == num[i+1]) dp[start][end]=Math.min(dp[start][end], left+right);
            else{
                dp[start][end] = Math.min(dp[start][end], left+right+1);
            }
        }

        return dp[start][end];
    }
}
