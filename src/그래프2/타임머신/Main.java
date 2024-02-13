package 그래프2.타임머신;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11657
// 벨만포드 알고리즘
// 다익스트라는 음수에서 무한 사이클로 인해 음수 가중치가 있을 경우 사용할 수 없음.
// 다익스트라는 각 노드의 간선들만을 최소한으로 접근한다
// 벨반포드는 간선을 매번 접근한다
// 1. 시작노드 선정
// 2. V-1 만큼 반복
// - 전체 간선 순회
// - 각 간선을 거쳐 다른 노드로 진행 시 최단 거리 테이블 셋업
// 3. 2번 동일 작업 1회 더 했을 때 값이 변경된다면 무한 음수 발생
// long 타입으로 dist 선언 필요
public class Main {
    static int N, M;
    static int INF = 999999999;
    static Info[] graph;
    static long[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new long[N+1];
        for(int i = 1; i <= N; i++){
            dist[i] = INF;
        }

        graph = new Info[M+1];
        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[i] = new Info(a,b,c);
        }
        if(BellmanFord(1)){
            System.out.println("-1");
        }else{
            for(int i = 2; i <= N; i++){
                if(dist[i] == INF) System.out.println("-1");
                else System.out.println(dist[i]);
            }
        }


    }

    static boolean BellmanFord(int start){
        dist[start] = 0L;

        for(int i = 1; i < N; i++){ // N - 1 회 반복
            for(int j = 1; j <= M; j++){ // 전체 간선 반복
                Info bus = graph[j];
                if(dist[bus.start] == INF) continue;
                if(dist[bus.end] > dist[bus.start] + bus.w) dist[bus.end] = dist[bus.start] + bus.w;
            }
        }

        for(int j = 1; j <= M; j++){ // 무한 확인
            Info bus = graph[j];
            if(dist[bus.start] == INF) continue;
            if(dist[bus.end] > dist[bus.start] + bus.w) return true;
        }
        return false;
    }
}

class Info{
    int start, end;
    int w;

    public Info(int start, int end, int w) {
        this.start = start;
        this.end = end;
        this.w = w;
    }
}