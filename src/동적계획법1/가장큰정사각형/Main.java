package 동적계획법1.가장큰정사각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1915
// DP
// 2*2 정사각형 여부 확인 > 정사각형 변의 길이 저장
// 2*2 정사각형인 경우
// dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]),dp[i-1][j-1]) + 1;
// 2*2 정사각형이 아닌 경우
// dp[i][j] = 1
public class Main {
    static int N, M;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        for(int i = 1;i <= N; i++){
            String row = br.readLine();
            for(int j = 1; j <= M; j++){
                map[i][j] = row.charAt(j-1)-'0';
            }
        }

        dp = new int[N+1][M+1];
        int max = 0;
        for(int i = 1; i <=N; i++){
            for(int j = 1; j <= M; j++){
                if(map[i][j] == 1){
                    if(dp[i-1][j] != 0 && dp[i][j-1] != 0 && dp[i-1][j-1] != 0){
                        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]),dp[i-1][j-1]) + 1;
                    }else{
                        dp[i][j] = 1;
                    }
                    if(max < dp[i][j]) max = dp[i][j];
                }
            }
        }

        System.out.println(max*max);
    }
}
