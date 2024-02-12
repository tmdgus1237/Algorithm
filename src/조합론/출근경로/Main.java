package 조합론.출근경로;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5569
// 경로 탐색이지만 방향 전환 시 최소 2이상이라는 전제 조건이 문제
// DP 로 접근하되 2차 배열로 해결되지 않음
// 방향 정보를 4차 배열을 이용해 정보 기입
// 방향 정보까지 취합하여 최종 경우의 수 계산
public class Main {
    static int W;
    static int H;
    static int[][][][] route;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // route[i][j][0][0] : 동
        // route[i][j][0][1] : 북 -> 동
        // route[i][j][1][0] : 북
        // route[i][j][1][1] : 동 -> 북
        route = new int[W][H][2][2];

        for(int i = 0; i < W; i++){
            route[i][0][0][0] = 1;
        }

        for(int j = 0; j < H; j++){
            route[0][j][1][0] = 1;
        }

        for(int i = 1; i < W; i++){
            for(int j = 1; j < H; j++){
                route[i][j][0][0] += (route[i-1][j][0][0] + route[i-1][j][0][1]) % 100000;
                route[i][j][0][1] += route[i-1][j][1][0] % 100000;
                route[i][j][1][0] += (route[i][j-1][1][0] + route[i][j-1][1][1]) % 100000;
                route[i][j][1][1] += route[i][j-1][0][0] % 100000;
            }
        }

        System.out.println((route[W-1][H-1][0][0]+route[W-1][H-1][0][1]+route[W-1][H-1][1][0]+route[W-1][H-1][1][1]) % 100000);
    }
}
