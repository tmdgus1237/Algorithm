package 그래프1.네트워크연결;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Prim 알고리즘
// 1. 정점 하나를 지정한다.
// 2. 가장 적은 비용으로 간선을 연결한다.
// 3. 연결한 간선의 다른 정점에서 2를 반복한다.
// 4. N-1 개의 간선 연결 시 끝
public class MainForPrim {
    static int N, M;
    static boolean[] visited;
    static ArrayList<ArrayList<WeightInfoForPrim>> wifp = new ArrayList<ArrayList<WeightInfoForPrim>>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visited = new boolean[N+1];

        for (int i = 0; i < N+1; i++) {
            wifp.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            wifp.get(start).add(new WeightInfoForPrim(end, weight));
            wifp.get(end).add(new WeightInfoForPrim(start, weight));
        }

        int result = 0;
        int cnt = 0;
        PriorityQueue<WeightInfoForPrim> pq = new PriorityQueue<>();
        for(int i = 0; i < wifp.get(1).size();i++){
            pq.offer(wifp.get(1).get(i));
        }
        visited[1] = true;

        while(!pq.isEmpty()){
            WeightInfoForPrim w = pq.poll();
            if(cnt == N-1) break;
            if(visited[w.end]) continue;

            visited[w.end] = true;
            result += w.weight;
            cnt++;
            for(int i = 0; i < wifp.get(w.end).size();i++){
                pq.offer(wifp.get(w.end).get(i));
            }
        }

        System.out.println(result);
    }
}

class WeightInfoForPrim implements Comparable<WeightInfoForPrim> {
    int  end, weight;

    public WeightInfoForPrim(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(WeightInfoForPrim o) {
        return this.weight - o.weight;
    }
}
