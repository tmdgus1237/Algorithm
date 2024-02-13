package 그래프2.거의최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://acmicpc.net/problem/5719
// 다익스트라 응용
// 다익스트라, 백트래킹
public class Main {
    static int N, M, S, D;
    static int INF = 9999999;
    static ArrayList<Info>[] graph;
    static ArrayList<Integer>[] backGraph;
    static int[] dist;
    static boolean[] visited;
    static boolean[][] isShortest;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0) break;

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N];
            backGraph = new ArrayList[N];
            isShortest = new boolean[N][N];
            dist = new int[N];
            for(int i = 0; i < N; i++){
                graph[i] = new ArrayList<>();
                backGraph[i] = new ArrayList<>();
            }


            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                graph[a].add(new Info(b,c));
            }
            dijkstra(S);
            backTracking(S, D);
            dijkstra(S);
            if(dist[D] == INF) System.out.println(-1);
            else System.out.println(dist[D]);
        }

    }

    static void backTracking(int start, int end){
        if(start == end) return;

        for(int pre : backGraph[end]){
            if(!isShortest[pre][end]){
                isShortest[pre][end] = true;
                backTracking(start, pre);
            }
        }
    }

    static void dijkstra(int start){
        int result = 0;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(start,0));


        visited = new boolean[N];
        dist = new int[N];
        for(int i = 0; i < N; i++){
            dist[i] = INF;
        }
        dist[start] = 0;

        while(!pq.isEmpty()){
            Info now = pq.poll();
            visited[now.end] = true;
            if(now.w > dist[now.end]) continue;
            for(Info next : graph[now.end]){
                if(isShortest[now.end][next.end]) continue;

                if(dist[next.end] == now.w + next.w){
                    backGraph[next.end].add(now.end);
                } else if(dist[next.end] > now.w + next.w) {
                    dist[next.end] = now.w + next.w;
                    backGraph[next.end].clear();
                    backGraph[next.end].add(now.end);
                    if(!visited[next.end]) pq.add(new Info(next.end, dist[next.end]));
                }
            }
        }
    }
}

class Info implements Comparable<Info>{
    int end, w;

    public Info(int end, int w) {
        this.end = end;
        this.w = w;
    }

    @Override
    public int compareTo(Info o) {
        return this.w - o.w;
    }
}