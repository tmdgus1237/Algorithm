package 동적계획법1.행렬곱셈순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11049
// https://velog.io/@turtle601/%EB%B0%B1%EC%A4%80-%ED%96%89%EB%A0%AC-%EA%B3%B1%EC%85%88-%EC%88%9C%EC%84%9C-11049%EB%B2%88
public class Main {
    static int N;
    static int[][] mat;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        mat = new int[N][2];
        dp = new int[N][N];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            mat[i][0] = Integer.parseInt(st.nextToken());
            mat[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < N; i++){
            for(int j = 0; j < N-i; j++){
                int n = i + j;
                dp[j][n] = Integer.MAX_VALUE;
                for(int k = j; k < n; k++){
                    dp[j][n] = Math.min(dp[j][n],dp[j][k] + dp[k+1][n] + mat[j][0]*mat[k][1]*mat[n][1]);
                }
            }
        }
        System.out.println(dp[0][N-1]);
    }
}
