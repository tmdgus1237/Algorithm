package 동적계획법1.정수삼각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1932
// DP 문제
// dp[i][j] += MAX(dp[i-1][j-1], dp[i-1][j])
public class Main {
    static int N;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= i; j++){
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 2; i <= N; i++){
            for(int j = 1; j <= i; j++){
                dp[i][j] += Math.max(dp[i-1][j-1], dp[i-1][j]);
            }
        }

        int max = 0;
        for(int j = 1; j <= N; j++){
            if(max < dp[N][j]) max = dp[N][j];
        }
        System.out.println(max);
    }
}