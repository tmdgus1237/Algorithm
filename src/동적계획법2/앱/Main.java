package 동적계획법2.앱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7579
// DP - 배낭 알고리즘
// 1. 현재 가능한 비용보다 크다면 가방에 넣지 않는다.
// 2-1. 현재 가능한 비용보다 작을 때, 가방에 넣는다
// 2-2. 현재 가능한 비용보다 작을 때, 가방에 넣지 않는다.
// 2번 작업을 이전 메모리 최대값을 반영하여 모바일 앱 순서대로 체크한다.
public class Main {
    static int N, M;
    static int totalCost = 0;
    static int[] memories;
    static int[] cost;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memories = new int[N+1];
        cost = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            memories[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }

        dp = new int[N+1][totalCost+1];
        for(int i = 1; i <= N; i++){
            for(int j = 0; j <= totalCost; j++){
                if(j >= cost[i]){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-cost[i]] + memories[i]);
                }
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
            }
        }

        for(int i = 0; i <= totalCost; i++){
            if(dp[N][i] >= M){
                System.out.println(i);
                return;
            }
        }
    }
}
