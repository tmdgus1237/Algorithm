package 동적계획법1.구간합구하기5;

import java.io.*;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11660
// DP
// 시간초과 안나도록 조심
// 1. M 100,000 * N 1024 = 대략 1억 > 1초 내에 수행 가능할듯
// dp[i][j] = dp[i][j] + dp[i][j-1]
// row 별로 구간합 dp에 저장
// 2. N 1024 * N 1024 = N^2
// map[1][1]~map[i][j] 의 구간합을 dp[i][j]에 저장
// 1번보다 2번이 2배 가량 빠름
public class Main {
    static int N, M;
    static long[][] dp;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new long[N+1][N+1];
        map = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= N; i++){
            for (int j = 1; j <= N; j++){
                dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1] + map[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            long result = dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1];
            sb.append(result+"\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
