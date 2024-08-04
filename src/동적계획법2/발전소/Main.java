package 동적계획법2.발전소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 원판원 순회와 동일
// DFS로 하나하나 해보면서 DP로 가지치기 한다
// 비트마스킹
public class Main {
    static int N, P;
    static int[][] cost;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        cost = new int[N][N];
        dp = new int[N+1][1<<N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
            for(int k = 0; k < (1<<N); k++){
                dp[i][k] = Integer.MAX_VALUE;
            }
        }

        String s = br.readLine();
        int Ycnt = 0;
        int v = 0;
        for(int i = 0; i < N; i++){
            if(s.charAt(i) == 'Y') {
                v = v | (1 << i);
                Ycnt++;
            }
        }

        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());

        int result = dfs(Ycnt, v);

        if(result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
    }

    private static int dfs(int cnt, int v) {
        if(cnt >= P) return 0;

        if(dp[cnt][v] != Integer.MAX_VALUE){
            return dp[cnt][v];
        }

        for(int i = 0; i < N; i++){
            if((v & (1 << i)) != 0){ // 정상 발전소
                for(int j = 0; j < N; j++){
                    if(i == j | (v & (1 << j)) != 0) continue;
                    dp[cnt][v] = Math.min(dp[cnt][v], dfs(cnt+1, (v | (1 << j))) + cost[i][j]);
                }
            }
        }

        return dp[cnt][v];
    }
}
