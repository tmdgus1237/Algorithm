package 동적계획법2.외판원순회;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2098
// 완전 탐색은 O(N!) > 시간 초과
// 기본적으로 비트마스킹을 사용하면 시간복잡도를 O(2^N)으로 줄일 수 있고,
// DP로 가지치기
public class Main {
    static int N;
    static int[][] map;
    static int[][] dp;

    private static int IMPOSSIBLE = 50_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int max = 1 << N;

        dp = new int[N][max];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(TSP(0,1));
    }

    static int TSP(int id, int visited){
        // ** 탈출조건 - 1. 모든 지점을 방문한 경우
        if(visited == (1<<N)-1) {
            // 1-1. 가는 경로가 없으면
            if (map[id][0] == 0) {
                return IMPOSSIBLE;
            }
            // 1-2. 가는 경로가 있으면
            return map[id][0];
        }

        // ** 탈출조건 - 2. 이미 계산한 경우
        if (dp[id][visited] != 0) {
            return dp[id][visited];
        }

        // 3. 반복문 돌면서 재귀탐색
        // 불가능하다고 가정하고 루트를 재귀적으로 탐색  ( 최솟값으로 갱신하기 위하여 )
        dp[id][visited] = IMPOSSIBLE;
        for (int i = 0; i<N; i++) {
            int next = visited | (1<<i);  // 다음 방문할 녀석
            // 3-1. 방문한 경우 continue
            if((visited & (1 << i)) != 0 ) continue;
            // 3-2. 길이 없는 경우 continue
            if(map[id][i]==0) continue;
            // 3-3. TSP 진행
            dp[id][visited] = Math.min(
                    dp[id][visited], TSP(i, next) + map[id][i]);
        }
        return dp[id][visited];
    }
}