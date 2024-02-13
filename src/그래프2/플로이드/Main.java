package 그래프2.플로이드;

import java.io.*;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/11404
// 플루이드 워셜 알고리즘
// O(N^3) 시간복잡도를 가지므로 시간 제한과 N 최대 값을 확인하여 가능 여부 확인 필요
// 해당 문제는 N이 500으로 크지 않아 1초 내에 수행 가능
// 최단 거리를 구해 모두 연결 되어져 있는지 확인하여 숫자 count
// k, i, j 순으로 돌아야한다
public class Main {
    static int N, M;
    static int INF = 9999999;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N+1][N+1];
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                dist[i][j] = INF;
            }
        }

        for(int i = 1; i <= M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dist[a][b] = Math.min(c, dist[a][b]);
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(FloydWarshall());
        bw.flush();
    }

    static String FloydWarshall(){
        for(int k = 1; k <=N; k++){
            for(int i = 1; i <=N; i++){
                for(int j = 1; j <= N; j++){
                    int route = dist[i][k] + dist[k][j];
                    dist[i][j] = Math.min(dist[i][j], route);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(i == j || dist[i][j] == INF) sb.append("0 ");
                else sb.append(dist[i][j]+" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
