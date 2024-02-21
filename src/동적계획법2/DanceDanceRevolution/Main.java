package 동적계획법2.DanceDanceRevolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2342
// 오른쪽, 왼쪽의 현 위치를 줬을때 다음은 어떻게 할지 재귀로 표핸해보자
// 그 중 최솟값을 계산한다
public class Main {
    static int[] order;
    static int cnt;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        order = new int[100001];
        cnt = 0;
        for(int i = 1; i <= 100000; i++){
            int tmp = Integer.parseInt(st.nextToken());
            if(tmp == 0) break;
            order[i] = tmp;
            cnt++;
        }

        dp = new int[order.length+1][5][5];

        System.out.println(ddr(1,0,0));
    }

    static int ddr(int step, int a, int b){
        if(step == cnt+1) return 0;
        if(dp[step][a][b] != 0) return dp[step][a][b];

        int l = getPoint(a, order[step]) + ddr(step+1, order[step], b);
        int r = getPoint(b, order[step]) + ddr(step+1, a, order[step]);

        return dp[step][a][b] = Math.min(l,r);
    }

    static int getPoint(int pre, int now){
        if(pre == now) return 1;
        else if(pre == 0) return 2;
        else if(Math.abs(pre-now) == 2) return 4;
        return 3;
    }
}
