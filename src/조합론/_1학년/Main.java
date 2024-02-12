package 조합론._1학년;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5557
/* 재귀로 하면 2의 N승이 되므로 시간 초과
*  DP로 접근 (BOTTOM UP)
*  0~20까지 값이 나오기 위한 조합의 수를 확인 하자
*  N-1 번째의 수 +/- 연산 시 나오는 결과 값을 바로 확인 가능
* */
public class Main {
    static int N;
    static long[][] dp = new long[100][21]; // 조합의 수, 2^63 - 1 이므로 long 타입으로 선언 필요
    static int[] number;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        number = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }

        // 초기값. 첫번째 값은 +
        dp[0][number[0]] = 1;

        for(int i = 1; i < N-1; i++){
            int num = number[i];
            for(int j = 0; j < 21; j++){
                int plus = j+num;
                if(plus < 21){
                    dp[i][plus] += dp[i-1][j];
                }

                int minus = j - num;
                if(minus >= 0){
                    dp[i][minus] += dp[i-1][j];
                }
            }
        }

        System.out.println(dp[N-2][number[N-1]]);

    }


}
