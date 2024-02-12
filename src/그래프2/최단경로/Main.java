package 그래프2.최단경로;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1753
// 다익스트라
// Queue 시간복잡도 O(V^2)
// PQ 시간복잡도 O(ElogV) >> 사용
// 참고 : https://code-lab1.tistory.com/29
public class Main {
    static int V, E;
    static int INF = 99999999;
    static ArrayList<Info>[] graph;
    static int[] dist;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine());

        dist = new int[V+1];
        visited = new boolean[V+1];
        graph = new ArrayList[V+1];
        for(int i = 0; i <= V; i++){
            graph[i] = new ArrayList<Info>();
            dist[i] = INF;
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[a].add(new Info(b, w));
        }

        dijkstra(start);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= V; i++){
            if(dist[i] == INF){
                sb.append("INF\n");
                continue;
            }
            sb.append(dist[i]+"\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static void dijkstra(int start){
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Info target = pq.poll(); // 현재 경유지
            visited[target.end] = true;
            if(target.w > dist[target.end]) continue;
            for(Info next : graph[target.end]){ // 도착지
                if(dist[next.end] > target.w + next.w){
                    dist[next.end] = target.w + next.w;
                    if(!visited[next.end])
                        pq.add(new Info(next.end, dist[next.end])); // 현재의 최소 경로의 가중치로
                }
            }
        }
    }
}

class Info implements Comparable<Info>{
    int end;
    int w;

    public Info(int end, int w) {
        this.end = end;
        this.w = w;
    }

    @Override
    public int compareTo(Info o) {
        return this.w-o.w;
    }
}