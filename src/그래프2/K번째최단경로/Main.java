package 그래프2.K번째최단경로;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1854
// 다익스트라 이용 > K번째를 유의
// 최소 경로를 찾되 K번째 수를 단번에 찾기 위해서 우선 순위 큐 MAX HEAP 사용
// 다익스트라 O(ElogV), 우선순위 큐 O(logK)
// 동일 정점을 여러번 접근할 수 있다는 조건에서 사이클이 돌 수 있지 않을까 잠시 의심했다
// dist 우선순위 큐에 K 개수 제한 및 최대값보다 작은 값에 대해서만 pq에 넣어주므로 사이클 돌지 않을 것
public class Main {
    static int N, M, K;
    static PriorityQueue<Integer>[] dist;
    static ArrayList<Info>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new PriorityQueue[N+1];
        graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<Info>();
            dist[i] = new PriorityQueue<>(K,Collections.reverseOrder());
        }

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Info(b, c));
        }

        dijkstra(1);

        StringBuilder sb = new StringBuilder();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 1; i <= N; i++){
            if(dist[i].size() == K){
                sb.append(dist[i].peek()+"\n");
            }else{
                sb.append("-1\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static void dijkstra(int start){
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.offer(new Info(start, 0));
        dist[start].offer(0);

        while(!pq.isEmpty()){
            Info now = pq.poll();
            for(Info next : graph[now.end]){
                if(dist[next.end].size() < K){
                    dist[next.end].add(now.w + next.w);
                    pq.offer(new Info(next.end, now.w+next.w));
                }else if(dist[next.end].peek() > (now.w + next.w)){
                    dist[next.end].poll();
                    dist[next.end].offer(now.w+next.w);
                    pq.add(new Info(next.end, now.w + next.w));
                }
            }
        }
    }
}

class Info implements Comparable<Info>{
    public Info(int end, int w) {
        this.end = end;
        this.w = w;
    }

    int end;
    int w;

    @Override
    public int compareTo(Info o) {
        return this.w-o.w;
    }
}