package 동적계획법2.카드게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11062
// DP
// 근우가 이길 수 있도록 최선의 전략으로 게임
// 명우도 최선의 전략
// 분할 정복
// 근우의 턴일 경우, MAX가 되도록
// 명우의 턴일 경우, MIN이 되도록
public class Main {
    static int T, N;
    static int[] cards;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            N = Integer.parseInt(br.readLine());
            cards = new int[N];
            dp = new int[N+1][N+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                cards[i] = Integer.parseInt(st.nextToken());
            }
            cardGame(0,N-1, 0);

            System.out.println(dp[0][N-1]);

        }


    }

    static int cardGame(int start, int end, int turn){
        if(start > end){
            return 0;
        }
        if(dp[start][end] != 0) return dp[start][end];

        if(turn % 2 == 0){
            dp[start][end] = Math.max(cardGame(start+1, end, 1) + cards[start], cardGame(start,end-1,1) + cards[end]);
        }else{
            dp[start][end] = Math.min(cardGame(start+1, end, 0), cardGame(start,end-1,0));
        }

        return dp[start][end];
    }
}
