package 동적계획법1.계단오르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2579
// DP
// 3칸 연속으로 오르지 못한다는 것을 점화식으로
// dp[i] = Math.max(dp[i-3]+score[i-1]+score[i], dp[i-2]+score[i])
public class Main {
    static int N;
    static int[] score;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        score = new int[N+1];
        dp = new int[N+1];
        for(int i = 1; i <= N; i++){
            score[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = score[1];
        if(N == 1){
            System.out.println(dp[N]);
            return;
        }
        dp[2] = score[2] + dp[1];
        for(int i = 3; i <= N; i++){
            dp[i] = Math.max(dp[i-3]+score[i-1]+score[i], dp[i-2]+score[i]);
        }

        System.out.println(dp[N]);
    }
}
