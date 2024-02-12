package 그래프1.키순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2458
// 플루이드 워셜 알고리즘
// O(N^3) 시간복잡도를 가지므로 시간 제한과 N 최대 값을 확인하여 가능 여부 확인 필요
// 해당 문제는 N이 500으로 크지 않아 1초 내에 수행 가능
// 최단 거리를 구해 모두 연결 되어져 있는지 확인하여 숫자 count
// k, i, j 순으로 돌아야한다
public class Main {
    static int N, M;
    static final int INF = 999999999;
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];
        for(int i = 0; i <= N; i++){
            for(int j = 0; j <= N; j++){
                graph[i][j] = INF;
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A][B] = 1;
        }

        System.out.println(FloydWarshall());
    }

    static int FloydWarshall(){
        for(int k = 1; k <= N; k++){ // 경유지
            for(int i = 1; i <=N; i++){ // 출발지
                for(int j = 1; j <= N; j++){ // 도착지
                    int route = graph[i][k] + graph[k][j];
                    if(graph[i][j] > route){
                        graph[i][j] = route;
                    }
                }
            }
        }

        int result = 0;
        for(int i = 1; i <= N; i++){
            int check = 0;
            for(int j = 1; j <= N; j++){
                if(graph[i][j] != INF || graph[j][i] != INF) check++;
            }
            if(check == N-1) result++;
        }

        return result;
    }
}
