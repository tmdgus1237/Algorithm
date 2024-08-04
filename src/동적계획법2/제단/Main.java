package 동적계획법2.제단;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dp[i][j] : i번째 열이 j 높이일 때, 제단의 경우의 수
// dp[N-1][0] 에 최종 제단의 경우의 수를 뽑을 수 있음
public class Main {
    static final int MOD_VAL = 1000000007;
    static int N;
    static int[] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        dp = new int[N][(N/2)+2]; // [N][N] 10,000 * 10,000 메모리 초과 발생
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());

            if(arr[i] > Math.min(i,N-1-i)){
                System.out.println(0);
                return;
            }
        }

        dp[0][0] = 1;
        for(int i = 1; i < N; i++){
            if(arr[i] == -1){
                int h = Math.min(i,(N-1)-i);
                for(int j = 0; j <= h; j++){
                    for(int k = -1; k <= 1; k++){ // 1만큼 차이날 수 있음
                        int hh = j + k;
                        if(hh < 0) continue;

                        dp[i][j] += dp[i-1][hh];
                        dp[i][j] %= MOD_VAL;
                    }
                }
            }else{
                for(int k = -1; k <= 1; k++){
                    int hh = arr[i] + k;
                    if(hh < 0) continue;

                    dp[i][arr[i]] += dp[i-1][hh];
                    dp[i][arr[i]] %= MOD_VAL;
                }
            }

        }

        System.out.println(dp[N-1][0] % MOD_VAL);

    }
}
